package lectores;

import java.io.IOException;
import java.util.List;

import lectores.Ejercicio4Lector;
import us.lsi.common.Files2;
import us.lsi.common.Lists2;

public class Ejercicio4Lector {
	private static List<List<Integer>> data;
	
	public static List<List<Integer>> getData(){
		return Ejercicio4Lector.data;
	}
	
	public static void init(String file) throws IOException{
		List<String> lines = Files2.linesFromFile(file);
		Ejercicio4Lector.data = Lists2.empty();
		for(String line:lines) {
			List<Integer> e =Lists2.empty();
			String[] args = line.split(",");
			for(String arg:args) {
				e.add(Integer.valueOf(arg.trim()));
			}
			data.add(e);
		}
	}
	
}
