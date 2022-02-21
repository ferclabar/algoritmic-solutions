package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.Pair;

public class Ejercicio4 {
	
	public static Integer ej4SinM(Integer a, Integer b) {
		if(a<2&&b<2) {
			return a+b*b;
		}
		else if(a<2||b<2) {
			return a*a+b;
		}
		else {
			return ej4SinM(a/2,b-1) + ej4SinM(a/3, b-2) + ej4SinM(a-2,b/4);
		}
	}
	
	public static Integer ej4ConM(Integer a, Integer b, HashMap<Pair<Integer,Integer>, Integer> mem) {
		
		if(mem.containsKey(Pair.of(a, b))) {
			return mem.get(Pair.of(a, b));
		}
		else {
			if(a<2&&b<2) {
				Integer sol = a+b*b;
				mem.put(Pair.of(a, b), sol);
				return sol;
			}
			else if(a<2||b<2) {
				Integer sol2 = a*a+b;
				mem.put(Pair.of(a, b), sol2);
				return sol2;
			}
			else {
				Integer sol3 = ej4SinM(a/2,b-1) + ej4SinM(a/3, b-2) + ej4SinM(a-2,b/4);
				mem.put(Pair.of(a, b), sol3);
				return sol3;
			}
			
		}
		
	}
	
	public static Integer ej4Iter(Integer a, Integer b) {
		int[][] array = new int[a+1][b+1];
		for(int i = 0; i<=a; i++) {
			for(int j = 0; j<=b; j++) {
				if(i<2&j<2) array[i][j] = i+ j*j;
				else if(i<2||j<2) array[i][j]= i*i+j;
				else array[i][j] = array[i/2][j-1]+array[i/3][j-2]+array[i-2][j/4];
			}
		}
		return array[a][b];
	}
	
	public static List<Pair<Integer,Integer>> test4 (String dir){
		List<String> l = Files2.linesFromFile(dir);
		List<Pair<Integer,Integer>> sol = new ArrayList<Pair<Integer, Integer>>();
		int i = 0;
		while(i<= l.size()-1) {
			String[] trozos = l.get(i).split(",");
			Integer a = Integer.parseInt(trozos[0]);
			Integer b = Integer.parseInt(trozos[1]);
			Pair<Integer, Integer> p = Pair.of(a, b);
			sol.add(p);
			i++;
		}
		return sol;
	}
	
	
	public static void main(String[] args) {
		
		List<Pair<Integer,Integer>> datos = test4("../Practica3/tests/PI3Ej4DatosEntrada.txt");
		System.out.println("EJERCICIO 4 SIN MEMEORIA");
		System.out.println(ej4SinM(datos.get(0).first, datos.get(0).second ));
		System.out.println(ej4SinM(datos.get(1).first, datos.get(1).second ));
		System.out.println(ej4SinM(datos.get(2).first, datos.get(2).second ));
		System.out.println(ej4SinM(datos.get(3).first, datos.get(3).second ));
		System.out.println(ej4SinM(datos.get(4).first, datos.get(4).second ));
		System.out.println(ej4SinM(datos.get(5).first, datos.get(5).second ));
		System.out.println("=============================================================================");
		System.out.println("EJERCICIO 4 CON MEMEORIA");

		System.out.println(ej4ConM(datos.get(0).first, datos.get(0).second, new HashMap<Pair<Integer,Integer>, Integer>()));
		System.out.println(ej4ConM(datos.get(1).first, datos.get(1).second, new HashMap<Pair<Integer,Integer>, Integer>()));
		System.out.println(ej4ConM(datos.get(2).first, datos.get(2).second, new HashMap<Pair<Integer,Integer>, Integer>()));
		System.out.println(ej4ConM(datos.get(3).first, datos.get(3).second, new HashMap<Pair<Integer,Integer>, Integer>()));
		System.out.println(ej4ConM(datos.get(4).first, datos.get(4).second, new HashMap<Pair<Integer,Integer>, Integer>()));
		System.out.println(ej4ConM(datos.get(5).first, datos.get(5).second, new HashMap<Pair<Integer,Integer>, Integer>()));
		System.out.println("=============================================================================");
		System.out.println("EJERCICIO 4 ITERATIVO");
		System.out.println(ej4Iter(datos.get(0).first, datos.get(0).second));
		System.out.println(ej4Iter(datos.get(1).first, datos.get(1).second));
		System.out.println(ej4Iter(datos.get(2).first, datos.get(2).second));
		System.out.println(ej4Iter(datos.get(3).first, datos.get(3).second));
		System.out.println(ej4Iter(datos.get(4).first, datos.get(4).second));
		System.out.println(ej4Iter(datos.get(5).first, datos.get(5).second));


		
	}

}
