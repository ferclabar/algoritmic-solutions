package ej1PL;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import lectores.Ejercicio1Lector;
import us.lsi.common.Tuple2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;


public class Ejercicio1Gurobi {
	private static List<Tuple2<String, List<Integer>>> students;
	private static List<Integer> groups;
	private static Integer SPG;
	
	public static Integer getValue(Integer i, Integer j) {
		return students.get(i).v2.get(j);
	}
	
	public static Integer getNumStudents() {
		return students.size();
	}
	
	public static Integer getNumGroups() {
		return groups.size();
	}
	
	public static Integer getMaxStudentPerGroup() {
		return SPG;
	}
	
	public static Integer getZEROValue(Integer i, Integer j) {
		return students.get(i).v2.get(j)==0? 1:0;
	}
	
	public static void ej1_model(String file) throws IOException{
		Ejercicio1Lector.init(file);
		students = Ejercicio1Lector.getStudents();
		groups = Ejercicio1Lector.getGroups();
		SPG = Ejercicio1Lector.getSPG();
		String fileLP = "ficherosEntrada/generado/"+file.replace("ficherosEntrada/", "")+".lp";
		AuxGrammar.generate(Ejercicio1Gurobi.class, "modelo/ej1.lsi", fileLP);
		GurobiSolution solve = GurobiLp.gurobi(fileLP);
		Locale.setDefault(new Locale("en", "Us"));
		System.out.println(solve.toString((s,d)->d>0.));
		System.out.println(Ejercicio1FormatoEntendible.create(solve.objVal, solve.values));
	}
	
	public static void main(String[] args) {
		try {
			ej1_model("ficherosEntrada/PI6Ej1DatosEntrada1.txt");
			ej1_model("ficherosEntrada/PI6Ej1DatosEntrada2.txt");
			ej1_model("ficherosEntrada/PI6Ej1DatosEntrada3.txt");
		}catch(IOException e) {
			System.err.println("Not the correct format");
		}
	}
}