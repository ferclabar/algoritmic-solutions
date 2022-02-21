package ej2PL;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lectores.Ejercicio2Lector;
import us.lsi.common.Lists2;
import us.lsi.common.Maps2;

public class Ejercicio2FormatoEntendible {
	private Map<Integer, List<Integer>> dict;
	private Map<Integer, Integer> abogadosHoras;
	private Double hours;
	
	public static Ejercicio2FormatoEntendible create(Double totalHours, Map<String,Double> values) {
		return new Ejercicio2FormatoEntendible(totalHours, values);
	}
	
	private Ejercicio2FormatoEntendible(Double totalHours, Map<String, Double> values) {
		this.hours = totalHours;
		this.dict = Maps2.newHashMap();
		this.abogadosHoras = Maps2.newHashMap();
		for(Entry<String, Double> val:values.entrySet()) {
			if(val.getValue()>0&&val.getKey().startsWith("x")) {
				String[] fields = val.getKey().split("_");
				int key = Integer.valueOf(fields[1].trim()); key++;
				int value = Integer.valueOf(fields[2].trim()); value++;
				if(dict.containsKey(key)) {
					List<Integer> vals = dict.get(key);
					vals.add(value);
				} else {
					List<Integer> vals = Lists2.empty();
					vals.add(value);
					dict.put(key,vals);
				}if(abogadosHoras.containsKey(key)) {
					Integer horas = abogadosHoras.get(key);
					horas += Ejercicio2Lector.getAbogados().get(key-1).v2.get(value -1);
					abogadosHoras.replace(key, horas);
				}else {
					abogadosHoras.put(key, Ejercicio2Lector.getAbogados().get(key-1).v2.get(value -1));
				}
			}
		}
	}
	
	public String toString() {
		String toString = "";
		for(Entry<Integer, List<Integer>> e:dict.entrySet()) {
			toString += "Abogado "+e.getKey()+"\n";
			toString += "	Horas Empleadas: " + abogadosHoras.get(e.getKey())+"\n";
			toString += "	Casos estudiados: " +e.getValue()+"\n";
			toString += "	Media (horas/caso): " + (double)((double)abogadosHoras.get(e.getKey())/e.getValue().size())+"\n";
			toString += "===================================================================\n";
		}
		toString += "El estudio de todos los casos ha supuesto un total de "+getTotalHoras()+" horas de trabajo \r\n" + "para el bufete, que al trabajar en paralelo se ha podido llevar a cabo en " + hours + " horas.";
		return toString;
	}
	
	private Double getTotalHoras() {
		// TODO Auto-generated method stub
		return abogadosHoras.entrySet().stream().map(x->x.getValue()).reduce(0, (accum,x)->{return accum+x;}).doubleValue();
	}
}