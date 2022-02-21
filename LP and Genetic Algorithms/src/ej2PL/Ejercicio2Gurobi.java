package ej2PL;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import lectores.Ejercicio2Lector;
import us.lsi.common.Tuple2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2Gurobi {
	private static List<Tuple2<String,List<Integer>>> abogados;
	private static List<Integer> casos;
	
	public static Integer getHours(Integer i, Integer j) {
		return abogados.get(i).v2.get(j);
	}
	
	public static Integer getNumAbogados() {
		return abogados.size();
	}
	
	public static Integer getNumCasos() {
		return casos.size();
	}
	
	public static void ej2_model(String file) throws IOException{
		Ejercicio2Lector.init(file);
		abogados = Ejercicio2Lector.getAbogados();
		casos = Ejercicio2Lector.getCasos();
		String fileLP = "ficherosEntrada/generado/"+file.replace("ficherosEntrada/", "")+".lp";
		AuxGrammar.generate(Ejercicio2Gurobi.class, "modelo/ej2.lsi", fileLP);
		GurobiSolution solve = GurobiLp.gurobi(fileLP);
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(solve.toString((s,d)->d>0.));
		System.out.println(solve.values);
		System.out.println(Ejercicio2FormatoEntendible.create(solve.objVal, solve.values));
	}
	
	public static void main(String[] args) {
		try {
			ej2_model("ficherosEntrada/PI6Ej2DatosEntrada1.txt");
			ej2_model("ficherosEntrada/PI6Ej2DatosEntrada2.txt");
			ej2_model("ficherosEntrada/PI6Ej2DatosEntrada3.txt");
		}
		catch(IOException e) {
			System.err.println("Not the correct format");
		}
	}
}