package ej3PL;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import lectores.Ejercicio3Lector;
import us.lsi.common.Tuple3;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3Gurobi {

	private static List<Tuple3<String, Double, List<String>>> productos;
	private static List<String> funcionalidades;

	public static Double getPrecio(Integer i) {
		return productos.get(i).v2;
	}

	public static Integer getNumProductos() {
		return productos.size();
	}

	public static Integer getNumFuncionalidades() {
		return funcionalidades.size();
	}

	public static Integer contieneFuncionalidad(Integer i, Integer j) {
		return productos.get(i).v3.stream().anyMatch(p -> p.equals(funcionalidades.get(j))) ? 1 : 0;
	}

	public static void modelo(String ruta) throws IOException {
		Ejercicio3Lector.read(ruta);
		productos = Ejercicio3Lector.getProductos();
		funcionalidades = Ejercicio3Lector.getFuncionalidades();
		String formato = "ficherosEntrada/generado/" + ruta.replace("ficherosEntrada/", "").replace(".txt", "") + ".lp";
		AuxGrammar.generate(Ejercicio3Gurobi.class, "modelo/E3.lsi", formato);
		GurobiSolution solve = GurobiLp.gurobi(formato);
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(solve.toString((s, d) -> d > 0.));
		System.out.println(Ejercicio3FormatoEntendible.create(solve.objVal, solve.values));
	}

	public static void main(String[] args) throws IOException {
		modelo("ficherosEntrada/PI6Ej3DatosEntrada1.txt");
		modelo("ficherosEntrada/PI6Ej3DatosEntrada2.txt");
		modelo("ficherosEntrada/PI6Ej3DatosEntrada3.txt");
	}

}
