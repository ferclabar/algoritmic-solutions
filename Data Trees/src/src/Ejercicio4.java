package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.common.Files2;
import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio4 {
	
	
	public static <E> Integer getLevelFer(Tree<E> t) {
		return getLevelAux(t, 0);
	}
	
	public static <E> Integer getLevelAux(Tree<E> t, int i) {
		
		if(t.isRoot()) {
			return i;
		}
		else {
			i = i+1;
			return getLevelAux(t.getFather(), i);
		}
		
		
		
		
	}
	
	public static <E> Map<Integer,List<E>> callEj4(Tree<E> t){
		
		 Map<Integer,List<E>> sol = new HashMap<Integer, List<E>>();
		 return ej4(t, sol);
	}
	
	
	public static <E> Map<Integer,List<E>> ej4(Tree<E> t, Map<Integer, List<E>> sol){
		
		
		switch(t.getType()) {
		
		case Empty:
			
			break;
		case Leaf:
			Integer a = Ejercicio4.getLevelFer(t);

			if(sol.containsKey(a)!=true) {
				List<E> l = new ArrayList<E>();
				sol.put(a, l);
			}
			break;
		case Nary:
			if(Math2.esPar(t.getNumOfChildren())) {
				Integer b = Ejercicio4.getLevelFer(t);
				if (sol.containsKey(b)) {
					sol.get(b).add(t.getLabel());
					
				}
				else {
					List<E> l = new ArrayList<E>();
					sol.put(b, l);
					sol.get(b).add(t.getLabel());
				}
				
				
				
			}
			else {
				Integer h = Ejercicio4.getLevelFer(t);

				if(sol.containsKey(h)!=true) {
					List<E> l = new ArrayList<E>();
					sol.put(h, l);
				}
				
			}
			for(int j =0; j<t.getNumOfChildren(); j++) {
				ej4(t.getChild(j),sol);
			}
			
			
		
		
		
		}
		return sol;
			
			
		
	}
	
	public static  void test4(String dir) {
		List<String> l = Files2.linesFromFile(dir);
		System.out.println("EJERCICIO 4: ");
		for(int i = 0; i<l.size();i++) {
			Tree<Integer> t = Tree.parse(l.get(i)).copy(x->Integer.parseInt(x));
			Map<Integer,List<Integer>>  m = callEj4(t);
			System.out.println(i+": " +l.get(i));
			System.out.println("Resultado : " + m);
			System.out.println("============================================");


		}
	}
	
	public static void main(String[] args) {
		test4("./datos/PI4Ej4DatosEntrada.txt");
	}

}
