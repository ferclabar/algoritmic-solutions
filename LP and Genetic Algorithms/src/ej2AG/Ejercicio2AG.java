package ej2AG;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import lectores.Ejercicio2Lector;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agchromosomes.ValuesInRangeChromosome;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.Sets2;
import us.lsi.common.Tuple2;


public class Ejercicio2AG implements ValuesInRangeProblemAG<Integer, Ejercicio2AGFormatoEntendible> {
	public Ejercicio2AG (String file) throws IOException{
		//TODO Auto-generated constructor stub
		Ejercicio2Lector.init(file);
	}
	
	public ChromosomeType getType() {
		//TODO Auto-generated method stub
		return ChromosomeType.Range;
	}
	
	public Integer getCellsNumber() {
		//TODO Auto-generated method stub
		return getCasos().size();
	}
	
	public Integer getMax(Integer i) {
		//TODO Auto-generated method stub
		return getAbogados().size()+1;
	}
	
	public Integer getMin(Integer i) {
		//TODO Auto-generated method stub
		return 1;
	}
	
	private Double hours, fallos;
	private Double fitness = null;
	
	
	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {
		//TODO Auto-generated method stub
		this.hours = 0.;
		List<Integer> chrom = cr.decode();
		Set<Integer> set = Sets2.copy(chrom);
		for(int i=0; i<chrom.size(); i++) {
			int abogado = chrom.get(i);
			this.hours -= getAbogados().get(abogado-1).v2.get(i);
		}
		this.fallos = set.size() != getAbogados().size()? 1.:0.;
		fitness = this.hours-1000*this.fallos;
		return fitness;
	}
	
	public Ejercicio2AGFormatoEntendible getSolucion(ValuesInRangeChromosome<Integer> cr) {
		//TODO Auto-generated method stub
		List<Integer> chrom = cr.decode();
		Ejercicio2AGFormatoEntendible s = Ejercicio2AGFormatoEntendible.create(chrom);
		return s;
	}
	
	public List<Integer> getCasos(){
		return Ejercicio2Lector.getCasos();
	}
	
	public List<Tuple2<String, List<Integer>>> getAbogados(){
		return Ejercicio2Lector.getAbogados();
	}
	
	public static void exec(String file) throws IOException{
		AlgoritmoAG.ELITISM_RATE = 0.3;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		StoppingConditionFactory.NUM_GENERATIONS = 80000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		ValuesInRangeProblemAG<Integer, Ejercicio2AGFormatoEntendible> p = new Ejercicio2AG(file);
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> ap = AlgoritmoAG.create(p);
		ap.ejecuta();
		System.out.println(ap.getBestChromosome().decode());
		System.out.println(p.getSolucion(ap.getBestChromosome()));
	}
	
	public static void main(String[] args) {
		try {
			exec("ficherosEntrada/PI6Ej2DatosEntrada1.txt");
			exec("ficherosEntrada/PI6Ej2DatosEntrada2.txt");
			exec("ficherosEntrada/PI6Ej2DatosEntrada2.txt");
		}catch(IOException e) {
			System.err.println("Not the correct format");
		}
	}
}