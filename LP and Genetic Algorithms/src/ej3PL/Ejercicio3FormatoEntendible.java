package ej3PL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import lectores.Ejercicio3Lector;
import us.lsi.common.Tuple3;

public class Ejercicio3FormatoEntendible {
	private static List<Tuple3<String, Double, List<String>>> productos;
	private Double total;

	public static Ejercicio3FormatoEntendible create(Double totalVar, Map<String, Double> values) {
		return new Ejercicio3FormatoEntendible(totalVar, values);
	}

	private Ejercicio3FormatoEntendible(Double totalVar, Map<String, Double> values) {
		productos = new ArrayList<>();
		total = totalVar;
		for (Entry<String, Double> val : values.entrySet()) {
			if (val.getValue() > 0 && val.getKey().startsWith("x")) {
				String[] campos = val.getKey().split("_");
				int key = Integer.valueOf(campos[1].trim());
				key++;
				String nombre = key >= 10 ? "P" + key : "P0" + key;
				Tuple3<String, Double, List<String>> producto = Ejercicio3Lector.getProductos().stream()
						.filter(x -> x.v1.equals(nombre)).findFirst().get();
				productos.add(producto);
			}
		}
	}

	public static Set<String> getAllFuncionalidades() {
		return productos.stream().map(x -> x.v3).flatMap(list -> list.stream()).collect(Collectors.toSet());
	}

	public String toString() {
		String toString = "Funcionalidades a Cubrir: " + Ejercicio3Lector.getFuncionalidades()
				+ "\nComposición del lote seleccionado:\n";
		for (Tuple3<String, Double, List<String>> producto : productos) {
			toString += String.format("%s (%f euros) => %s\n", producto.v1, producto.v2, producto.v3);
		}
		toString += "Funcionalidades de la seleccion: " + getAllFuncionalidades() + "\n";
		toString += "Precio total del lote seleccionado: " + total;
		return toString;
	}

}
