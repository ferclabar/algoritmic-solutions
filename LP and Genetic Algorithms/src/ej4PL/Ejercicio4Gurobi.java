package ej4PL;

import java.io.IOException;

import java.util.List;
import java.util.Locale;

import lectores.Ejercicio4Lector;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio4Gurobi {

	
	private static List<Integer> datos;
	private static Integer numSubSets;
	
	public static Integer getSize() {
		return datos.size();
		}
	public static Integer getValue(Integer i) {
		return datos.get(i);
		}
	public static Integer getNumSubsets() {
		return numSubSets;
		}
	public static Integer getGoalSum() {
		return datos.parallelStream().reduce(0,(acum,x)->{return acum+x;})/getNumSubsets();
		}
	
	public static void ej4_module(String file) throws IOException{
		Ejercicio4Lector.init(file);
		int i=1;
		numSubSets=3;
		for(List<Integer> data : Ejercicio4Lector.getData()) {
			datos=data;
			String fileLP = "ficherosEntrada/generado/"+file.replace("ficherosEntrada/","")+i+".lp";
			AuxGrammar.generate(Ejercicio4Gurobi.class,"modelo/ej4.lsi",fileLP);
			GurobiSolution solve =GurobiLp.gurobi(fileLP);
			Locale.setDefault(new Locale("en","US"));
			System.out.println(solve.toString((s,d)->d>0.));
			System.out.println("Conjunto de entrada: "+datos);
			System.out.println(Ejercicio4FormatoEntendible.create(solve.objVal,solve.values));
		}
	}
	
	
	public static void main(String[] args) {
		try {
			ej4_module("ficherosEntrada/PI6Ej4DatosEntrada.txt");
		}catch(IOException e) {
			System.err.println("Not the correct format");
		}
	}
	
	
	

	
	
	
	
}