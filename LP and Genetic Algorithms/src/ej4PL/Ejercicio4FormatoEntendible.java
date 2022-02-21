package ej4PL;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import us.lsi.common.Lists2;
import us.lsi.common.Maps2;

public class Ejercicio4FormatoEntendible {

	//formato
	private static Map<Integer,List<Integer>> datos;
	private static Integer longitud;
	
	public static Ejercicio4FormatoEntendible create(Double longitudMinSet,Map<String,Double>values) {
		return new Ejercicio4FormatoEntendible(longitudMinSet,values);
}


private Ejercicio4FormatoEntendible(Double longitudMinSet, Map<String,Double> values) {
	datos=Maps2.newHashMap();
	longitud=longitudMinSet.intValue();
	for(Entry<String,Double>val:values.entrySet()) {
		if(val.getValue()>0 && val.getKey().startsWith("x")) {
			String[] fields=val.getKey().split("_");
			Integer atribute= Ejercicio4Gurobi.getValue(Integer.valueOf(fields[1].trim()));
			Integer group=Integer.valueOf(fields[2].trim())+1;
			if(datos.containsKey(group)) {
				List<Integer>vals=datos.get(group);
				vals.add(atribute);
				
			}else {
				List<Integer>vals=Lists2.empty();
				vals.add(atribute);
				datos.put(group, vals);
			}
		}
	}
		
	}
public String toString() {
	String toString="Suma obetivo: "+Ejercicio4Gurobi.getGoalSum()+"\n";
	toString+="=======================\n";
	toString+="El menor conjunto contiene "+longitud+"elemento\n";
	for(Entry<Integer,List<Integer>>dato:datos.entrySet()) {
		toString+= "Elementos del conjunto "+ dato.getKey()+":"+dato.getValue()+"\n";
	}
	toString+="================";
	return toString;
}
}