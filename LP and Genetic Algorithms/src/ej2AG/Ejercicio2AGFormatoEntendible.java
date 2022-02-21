package ej2AG;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lectores.Ejercicio2Lector;
import us.lsi.common.Lists2;
import us.lsi.common.Maps2;

public class Ejercicio2AGFormatoEntendible {
	private Map<Integer, List<Integer>> dict;
	private Map<Integer, Integer> abogadosHoras;
	
	public static Ejercicio2AGFormatoEntendible create(List<Integer> chrom) {
		return new Ejercicio2AGFormatoEntendible(chrom);
	}
	
	private Ejercicio2AGFormatoEntendible(List<Integer> chrom) {
		this.dict = Maps2.newHashMap();
		this.abogadosHoras = Maps2.newHashMap();
		for(int i=0;i<chrom.size();i++) {
			int key = chrom.get(i);
			if(dict.containsKey(key)) {
				List<Integer> vals = dict.get(key);
				vals.add(i+1);
			}else {
				List<Integer> vals = Lists2.empty();
				vals.add(i+1);
				dict.put(key,vals);
			}if(abogadosHoras.containsKey(key)) {
					Integer horas = abogadosHoras.get(key);
					horas += Ejercicio2Lector.getAbogados().get(key-1).v2.get(i);
					abogadosHoras.replace(key, horas);
				}else {
					abogadosHoras.put(key, Ejercicio2Lector.getAbogados().get(key-1).v2.get(i));
				}
			}
		}
	public String toString() {
		String toString = "";
		for(Entry<Integer, List<Integer>> abogado:dict.entrySet()) {
			toString += "Abogado "+abogado.getKey()+"\n";
			toString += "	Horas Empleadas: " + abogadosHoras.get(abogado.getKey())+"\n";
			toString += "	Casos estudiados: " +abogado.getValue()+"\n";
			toString += "	Media (horas/caso): " + (double)((double)abogadosHoras.get(abogado.getKey())/abogado.getValue().size())+"\n";
			toString += "===================================================================\n";
		}
		toString += "El estudio de todos los casos ha supuesto un total de "+getTotalHoras()+" horas de trabajo \r\n" + "para el bufete, que al trabajar en paralelo se ha podido llevar a cabo en " + getMaxHoras() + " horas.";
		return toString;
	}
	
	private Double getMaxHoras() {
		// TODO Auto-generated method stub
		return abogadosHoras.entrySet().stream().map(x->x.getValue()).max(Comparator.naturalOrder()).get().doubleValue();
	}
	
	private Double getTotalHoras() {
		// TODO Auto-generated method stub
		return abogadosHoras.entrySet().stream().map(x->x.getValue()).reduce(0, (accum,x)->{return accum+x;}).doubleValue();
	}
}