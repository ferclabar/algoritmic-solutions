package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.common.Files2;

public class Ejercicio3 {
	
	public static Long ej3ConM(Integer n, Map<Integer,Long> mem) {
		
		if(mem.containsKey(n)) {
			return mem.get(n);
		}
		else {
			if(n==0) {
			
				mem.put(n, 2L);
				return 2L;
			}
			else if (n==1 || n==2) {
				mem.put(n, 1L);
				return 1L;
			}
			else {
				Long a = 4*ej3ConM(n-1,mem)+ej3ConM(n-2,mem)+ej3ConM(n-3,mem);
				mem.put(n, a);
				return a;
			}
		}
	}
	
	public static Long ej3Iter(int n) {
		Long f =0L;
		Long f0 = 2L;
	    Long f1 = 1L;
		Long f2 = 1L;
		int i =2;
		if(n==0){
			f=2L;
		}
		else if(n<3){
			f =1L;
		}
		while(i<n){
			f = 4*f2+f1+f0;
			f0 = f1;
			f1=f2;
			f2=f;
			i++;
		}
		return f;
	}
	
	public static Long ej3SinM(Integer n) {
		
		if(n==0) {
			return 2L;
			
		}
		else if(n==1||n==2) {
			return 1L;
		}
		else {
			return 4*ej3SinM(n-1)+ej3SinM(n-2)+ej3SinM(n-3);
		}
	}
	
	
		
		
	
	public static List<Integer> test3 (String dir){
		List<String> lineas = Files2.linesFromFile(dir);
		List<Integer> sol = new ArrayList<>();
		int i =0;
		while(i<=lineas.size()-1) {
			String[] trozos = lineas.get(i).split("n=");
			Integer num = Integer.parseInt(trozos[1]);
			sol.add(num);
			i++;
		}
		return sol;
	}
	

	
	
	public static void main(String[] args) {
		
		List<Integer> l = test3("../Practica3/tests/PI3Ej3DatosEntrada.txt");
		System.out.println("EJERCICIO 3 SIN MEMORIA");

		System.out.println("n= " +l.get(0)+"     Resultado: " +ej3SinM(l.get(0)));
		System.out.println("n= " +l.get(1)+"    Resultado: " +ej3SinM(l.get(1)));
		System.out.println("n= " +l.get(2)+"    Resultado: " +ej3SinM(l.get(2)));
		System.out.println("n= " +l.get(3)+"    Resultado: " +ej3SinM(l.get(3)));
		System.out.println("n= " +l.get(4)+"    Resultado: " +ej3SinM(l.get(4)));
		System.out.println("n= " +l.get(5)+"    Resultado: " +ej3SinM(l.get(5)));
		System.out.println("============================================================");
		System.out.println("EJERCICIO 3 CON MEMORIA");

		System.out.println("n= " +l.get(0)+"     Resultado: "  +ej3ConM(l.get(0),new HashMap<Integer, Long>()));
		System.out.println("n= " +l.get(1)+"    Resultado: "  +ej3ConM(l.get(1),new HashMap<Integer, Long>()));
		System.out.println("n= " +l.get(2)+"    Resultado: " +ej3ConM(l.get(2),new HashMap<Integer, Long>()));
		System.out.println("n= " +l.get(3)+"    Resultado: "  +ej3ConM(l.get(3),new HashMap<Integer, Long>()));
		System.out.println("n= " +l.get(4)+"    Resultado: "  +ej3ConM(l.get(4),new HashMap<Integer, Long>()));
		System.out.println("n= " +l.get(5)+"    Resultado: " +ej3ConM(l.get(5),new HashMap<Integer, Long>()));
		System.out.println("===============================================================");
		System.out.println("EJERCICIO 3 ITERATIVO");

		System.out.println("n= " +l.get(0)+ "     Resultado      " + ej3Iter(l.get(0)));		
		System.out.println("n= " +l.get(1)+ "    Resultado      " + ej3Iter(l.get(1)));
		System.out.println("n= " +l.get(2)+ "    Resultado      " + ej3Iter(l.get(2)));
		System.out.println("n= " +l.get(3)+ "    Resultado      " + ej3Iter(l.get(3)));
		System.out.println("n= " +l.get(4)+ "    Resultado      " + ej3Iter(l.get(4)));
		System.out.println("n= " +l.get(5)+ "    Resultado      " + ej3Iter(l.get(5)));

		






	}

}
