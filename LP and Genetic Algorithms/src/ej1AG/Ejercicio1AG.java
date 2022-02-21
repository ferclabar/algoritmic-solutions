package ej1AG;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lectores.Ejercicio1Lector;
import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agchromosomes.ValuesInRangeChromosome;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;

public class Ejercicio1AG implements ValuesInRangeProblemAG<Integer, Ejercicio1AGFormatoEntendible> {

	public Ejercicio1AG(String file) throws IOException {
		Ejercicio1Lector.init(file);
	}

	public ChromosomeType getType() {
		return ChromosomeType.Range;
	}

	public Integer getCellsNumber() {
		return Ejercicio1Lector.getStudents().size();
	}

	public Integer getMax(Integer i) {
		return this.getGroups().size() + 1;
	}

	public Integer getMin(Integer i) {
		return 1;
	}

	public Ejercicio1AGFormatoEntendible getSolution(ValuesInRangeChromosome<Integer> cr) {
		List<Integer> chrom = cr.decode();
		Ejercicio1AGFormatoEntendible s = Ejercicio1AGFormatoEntendible.create(chrom);
		return s;
	}

	public List<Integer> getGroups() {
		return Ejercicio1Lector.getGroups();
	}

	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {
		List<Integer> listaCromosomas = cr.decode();

		Double objetivo = 0.;
		Double error = 0.;

		for (int j = 0; j < getGroups().size(); j++) {
			List<Integer> listaPrueba = new ArrayList<Integer>();

			for (int i = 0; i < getCellsNumber(); i++) {
				int alumnoAGrupo = listaCromosomas.get(i);
				int afinidadAlumno = Ejercicio1Lector.getStudents().get(i).v2.get(alumnoAGrupo - 1);

				objetivo += afinidadAlumno;

				if (j == listaCromosomas.get(i)) {
					listaPrueba.add(alumnoAGrupo);
				}

				if (alumnoAGrupo <= 0 && getGroups().size() < alumnoAGrupo) {
					error += 1;
				}

				if (afinidadAlumno == 0) {
					error += 1;
				}
			}

			if (Ejercicio1Lector.getSPG() != listaPrueba.size()) {
				error += 1;
			}
		}
		return objetivo - AuxiliaryAg.distanceToEqZero(error);
	}

	public static void exec(String file) throws IOException {
		AlgoritmoAG.ELITISM_RATE = 0.3;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		StoppingConditionFactory.NUM_GENERATIONS = 60000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;

		ValuesInRangeProblemAG<Integer, Ejercicio1AGFormatoEntendible> p = new Ejercicio1AG(file);
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> ap = AlgoritmoAG.create(p);
		ap.ejecuta();
		System.out.println(ap.getBestChromosome().decode());
		System.out.println(p.getSolucion(ap.getBestChromosome()));
	}

	public static void main(String[] args) throws IOException {
		exec("ficherosEntrada/PI6Ej1DatosEntrada1.txt");
		exec("ficherosEntrada/PI6Ej1DatosEntrada2.txt");
		exec("ficherosEntrada/PI6Ej1DatosEntrada3.txt");

	}

	public Ejercicio1AGFormatoEntendible getSolucion(ValuesInRangeChromosome<Integer> cr) {
		return Ejercicio1AGFormatoEntendible.create(cr.decode());
	}
}