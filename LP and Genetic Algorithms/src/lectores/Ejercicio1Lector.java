package lectores;

import java.io.IOException;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.Lists2;
import us.lsi.common.Tuple;
import us.lsi.common.Tuple2;

public class Ejercicio1Lector {
	
	private static List<Tuple2<String, List<Integer>>> students;
	private static List<Integer> groups;
	private static Integer Student_PER_Group;

	public static List<Integer> getGroups(){
		return Ejercicio1Lector.groups;
	}

	public static List<Tuple2<String, List<Integer>>> getStudents(){
		return Ejercicio1Lector.students;
	}

	private static void setSPG() throws ArithmeticException {
		Ejercicio1Lector.Student_PER_Group = getStudents().size()/getGroups().size();
	}

	public static Integer getSPG() {
		return Ejercicio1Lector.Student_PER_Group;
	}
	
	public static void init(String file) throws IOException{
		try {
			List<String> lines = Files2.linesFromFile(file);
			Ejercicio1Lector.students = Lists2.empty();
			Ejercicio1Lector.groups = Lists2.empty();
			for(String line: lines) {
				String[] args = line.split(":");
				String alum = args[0];
				String[] groupValues = args[1].split(",");
				List<Integer> alumValueG = Lists2.empty();
				for(String gValue:groupValues) {
					int avG = Integer.valueOf(gValue.trim());
					alumValueG.add(avG);
				}
				Ejercicio1Lector.students.add(Tuple.create(alum, alumValueG));
			}
			int GNumbers = getStudents().get(0).getV2().size();
			for(int i=1;i<=GNumbers;i++) {
				getGroups().add(i);
			}
			setSPG();
		}catch(ArithmeticException e) {
			Ejercicio1Lector.groups.add(null);
			setSPG();
		}
	}
}