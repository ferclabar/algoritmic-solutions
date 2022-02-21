package ej3AG;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import lectores.Ejercicio3Lector;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agchromosomes.ValuesInRangeChromosome;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.Sets2;
import us.lsi.common.Tuple2;
import us.lsi.common.Tuple3;

public class Ejercicio3AG implements ValuesInRangeProblemAG<Integer, Ejercicio3FormatoEntendibleAG> {
	public Ejercicio3AG(String file) throws IOException {
		Ejercicio3Lector.read(file);
	}
	
	public ChromosomeType getType() {
		// TODO Auto.generated method stub
		return ChromosomeType.Binary;
	}
	
	public Integer getCellsNumber() {
		// TODO Auto.generated method stub
		return this.getObjetos().size();
	}
	
	public Integer getMax(Integer i) {
		// TODO Auto.generated method stub
		return 2;
	}
	
	public Integer getMin(Integer i) {
		// TODO Auto.generated method stub
		return 0;
	}
	
	private Double precio, fallos;
	private Double fitness = null;
	
	private void calcula(List<Integer> chrom) {
		this.precio= 0.;
		this.fallos= 0.;
		Set<String> funcionalidadesCubiertas=Sets2.empty();
		for(int i=0; i<chrom.size(); i++) {
			if(chrom.get(i)==1) {
				Tuple3<String, Double, List<String>> objeto = getObjetos().get(i);
				this.precio+=objeto.v2;
				funcionalidadesCubiertas.addAll(objeto.v3);
			}
		}
		this.fallos+= !funcionalidadesCubiertas.containsAll(getFuncionalidades())? 1.:0.;
		this.fallos+= funcionalidadesCubiertas.size()==0? 1.:0.;
	}
	
	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {
		// TODO Auto-generated method stub
		List<Integer> chrom = cr.decode();
		calcula(chrom);
		this.fitness=-precio-1000*fallos;
		return fitness;
	}
	
	public Ejercicio3FormatoEntendibleAG getSolution(ValuesInRangeChromosome<Integer> cr) {
		// TODO Auto-generated method stub
		List<Integer> chrom = cr.decode();
		Ejercicio3FormatoEntendibleAG s = Ejercicio3FormatoEntendibleAG.create(chrom);
		return s;
	}
	
	public List<Tuple3<String, Double, List<String>>> getObjetos(){
		return Ejercicio3Lector.getProductos();
	}
	
	public List<String> getFuncionalidades(){
		return Ejercicio3Lector.getFuncionalidades();
	}
	
	public static void exec(String file) throws IOException {
		AlgoritmoAG.ELITISM_RATE=0.3;
		AlgoritmoAG.CROSSOVER_RATE=0.8;
		AlgoritmoAG.MUTATION_RATE=0.9;
		AlgoritmoAG.POPULATION_SIZE=50;
		StoppingConditionFactory.NUM_GENERATIONS=90000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN=1;
		ValuesInRangeProblemAG<Integer, Ejercicio3FormatoEntendibleAG> p = new Ejercicio3AG(file);
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> ap = AlgoritmoAG.create(p);
		ap.ejecuta();
		System.out.println(ap.getBestChromosome().decode());
		System.out.println("Funcionalidades a cubrir: "+Ejercicio3Lector.getFuncionalidades());
		System.out.println(p.getSolucion(ap.getBestChromosome()));
	}
	
	public static void main(String[] args) {
		try {
			exec("ficherosEntrada/PI6Ej3DatosEntrada1.txt");
			exec("ficherosEntrada/PI6Ej3DatosEntrada2.txt");
			exec("ficherosEntrada/PI6Ej3DatosEntrada3.txt");
		}catch (IOException e) {
			System.err.println("Not the correct format");
		}
	}

	@Override
	public Ejercicio3FormatoEntendibleAG getSolucion(ValuesInRangeChromosome<Integer> cr) {
		// TODO Auto-generated method stub
		List<Integer> chrom = cr.decode();
		Ejercicio3FormatoEntendibleAG s = Ejercicio3FormatoEntendibleAG.create(chrom);
		return s;
	}
}