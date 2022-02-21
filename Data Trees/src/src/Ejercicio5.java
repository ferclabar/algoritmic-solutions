package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio5 {
	
	public static <E> Map<Integer, Set<Tree<E>>> ej5(Tree<E>t, Map<Integer, Set<Tree<E>>> sol){
		
		switch(t.getType()) {
		case Empty:
			int ce = t.getNumOfChildren();
			if(sol.containsKey(ce)) {
				sol.get(ce).add(t);
			}
			else {
				Set<Tree<E>> s = new HashSet<Tree<E>>();
				s.add(t);
				sol.put(ce, s);
				
			}
			for(int i = 0; i<ce; i++) {
				ej5(t.getChild(i), sol);
			}
			break;
		case Leaf:
			if(sol.containsKey(0)) {
				sol.get(0).add(t);
			}
			else {
				Set<Tree<E>> s = new HashSet<Tree<E>>();
				s.add(t);
				sol.put(0, s);
			}
			break;
		case Nary:
			int c = t.getNumOfChildren();
			if(sol.containsKey(c)) {
				sol.get(c).add(t);
			}
			else {
				Set<Tree<E>> s = new HashSet<Tree<E>>();
				s.add(t);
				sol.put(c, s);
			}
			for(int i = 0; i<c; i++) {
				ej5(t.getChild(i), sol);
			}
			
		}
		return sol;
	}
	
	public static  void test5(String dir) {
		List<String> l = Files2.linesFromFile(dir);
		System.out.println("EJERCICIO 4: ");
		for(int i = 0; i<l.size();i++) {
			Tree<Integer> t = Tree.parse(l.get(i)).copy(x->Integer.parseInt(x));
			Map<Integer,Set<Tree<Integer>>> mem = new HashMap<Integer, Set<Tree<Integer>>>();
			Map<Integer,Set<Tree<Integer>>> sol = ej5(t, mem);
			System.out.println(i+": " +l.get(i));
			System.out.println("Resultado : " + sol);
			System.out.println("============================================");


		}
	}
	
	public static void main(String[] args) {
		test5("./datos/PI4Ej5DatosEntrada.txt");
	}

}
