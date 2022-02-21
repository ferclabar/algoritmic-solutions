package ej1AG;

import java.util.List;
import java.util.Map;

import lectores.Ejercicio1Lector;
import us.lsi.common.Lists2;
import us.lsi.common.Maps2;


public class Ejercicio1AGFormatoEntendible {
	private Map<Integer, List<Integer>> dict;
	private Double averageValue;
	
	public static Ejercicio1AGFormatoEntendible create(List<Integer> chrom) {
		return new Ejercicio1AGFormatoEntendible(chrom);
	}
	
	private Ejercicio1AGFormatoEntendible(List<Integer> chrom) {
		this.dict = Maps2.newHashMap();
		this.averageValue = 0.;
		for(int i=0; i<chrom.size();i++) {
			int key = chrom.get(i);
			averageValue += Ejercicio1Lector.getStudents().get(i).v2.get(key-1);
			if(dict.containsKey(key)) {
				List<Integer> vals = dict.get(key);
				vals.add(i+1);
			}else {
				List<Integer> vals = Lists2.empty();
				vals.add(i+1);
				dict.put(key, vals);
			}
		}
		averageValue /= Ejercicio1Lector.getStudents().size();
	}
	
	public String toString() {
		String toString = "Reparto obtenido:\n";
		for(Map.Entry<Integer, List<Integer>> par: dict.entrySet()) {
			toString+="Grupo "+par.getKey()+": "+par.getValue()+"\n";
		}
		toString += "Afinidad media: "+averageValue();
		return toString;
	}
	
	private Double averageValue() {
		// TODO Auto-generated method stub
		return this.averageValue;
	}
}