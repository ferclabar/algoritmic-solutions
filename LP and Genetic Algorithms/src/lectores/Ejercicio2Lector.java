package lectores;

import java.io.IOException;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.Lists2;
import us.lsi.common.Tuple;
import us.lsi.common.Tuple2;

public class Ejercicio2Lector {
	private static List<Tuple2<String, List<Integer>>> abogados;
	private static List<Integer> casos;
	
	public static List<Tuple2<String, List<Integer>>> getAbogados(){
		return Ejercicio2Lector.abogados;
	}
	
	public static List<Integer> getCasos(){
		return Ejercicio2Lector.casos;
	}
	
	public static void init(String file) throws IOException{
		List<String> lines = Files2.linesFromFile(file);
		Ejercicio2Lector.abogados = Lists2.empty();
		Ejercicio2Lector.casos = Lists2.empty();
		for(String line:lines) {
			String[] args = line.split(":");
			String abogado = args[0];
			String[] hours = args[1].split(",");
			List<Integer> casesHours = Lists2.empty();
			for(String h:hours) {
				casesHours.add(Integer.valueOf(h.trim()));
			}Ejercicio2Lector.abogados.add(Tuple.create(abogado, casesHours));
		}
		int totalCases = Ejercicio2Lector.abogados.get(0).v2.size();
		for(int i=1;i<=totalCases;i++) {
			Ejercicio2Lector.casos.add(i);
		}
	}
}