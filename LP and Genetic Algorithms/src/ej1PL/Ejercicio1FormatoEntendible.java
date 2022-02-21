package ej1PL;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import us.lsi.common.Lists2;
import us.lsi.common.Maps2;
import us.lsi.common.Sets2;

public class Ejercicio1FormatoEntendible {
	private Map<String, List<String>> dict;
	private Double averageValue;
	
	public static Ejercicio1FormatoEntendible create(Double TotalValue, Map<String, Double> Solutions) {
		return new Ejercicio1FormatoEntendible(TotalValue, Solutions);
	}
	
	private Ejercicio1FormatoEntendible(Double TotalValue, Map<String, Double> Solutions) {
		dict = Maps2.newHashMap();
		Set<String> students = Sets2.empty();
		for(Entry<String, Double> p:Solutions.entrySet()) {
			if(p.getValue()>0 && p.getKey().startsWith("x")) {
				String[] fields = p.getKey().split("_");
				int key = Integer.valueOf(fields[2].trim());
				key++;
				int value = Integer.valueOf(fields[1].trim());
				value++;
				if(dict.containsKey(String.valueOf(key))) {
					List<String> list = dict.get(String.valueOf(key));
					list.add(String.valueOf(value));
					students.add(String.valueOf(value));
				}else {
					List<String> list = Lists2.empty();
					list.add(String.valueOf(value));
					dict.put(String.valueOf(key), list);
					students.add(String.valueOf(value));
				}
			}
		}
		averageValue = TotalValue/students.size();
	}
	
	public String toString() {
		String toString = "Reparto obtenido:\n";
		for(Map.Entry<String, List<String>> par: dict.entrySet()) {
			toString += "Grupo "+ par.getKey() +": "+par.getValue()+"\n";
		};
		toString += "Afinidad media: "+averageValue;
		return toString;
	}
}