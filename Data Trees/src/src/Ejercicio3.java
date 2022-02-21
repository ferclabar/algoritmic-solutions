package src;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import us.lsi.common.Files2;
import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio3 {
	
	
	
	public static <E> List<Boolean> callEj3(Tree<E> t, Predicate<E> p){
		List<Boolean> res = new ArrayList<Boolean>();
		res = ej3(t, p, res);
		return res;
	}
	
	
	public static <E> List<Boolean> ej3(Tree<E> t, Predicate<E> p, List<Boolean> list){
		
		

        switch(t.getType()) {
            case Empty:
            	
                break;
            case Leaf:
            	int i = Ejercicio4.getLevelFer(t);
            	if(list.size()>i) {
                	list.set(i, list.get(i)&&p.test(t.getLabel()));

            	}
            	else {
            		list.add(p.test(t.getLabel()));
            		
            	}
            	break;
            case Nary:
            	int j = Ejercicio4.getLevelFer(t);
            	if(list.size()>j) {
                	list.set(j, list.get(j)&&p.test(t.getLabel()));

            	}
            	else {
            		list.add(p.test(t.getLabel()));
            	}
            	int m =0;
            	while(m<t.getNumOfChildren()) {
            		ej3(t.getChild(m), p , list);
            		m++;
            	}
       
 
                break;
        }
        return list;
    }
	
	public static void test3(String dir) {
		List<String> lines = Files2.linesFromFile(dir);
		System.out.println("EJERCICIO 3: ");
		for(int i = 0; i<lines.size();i++) {
			Tree<Integer> t = Tree.parse(lines.get(i)).copy(x->Integer.parseInt(x));
			List<Boolean> s1 = callEj3(t, x->Math2.esPar(x));
			List<Boolean> s2 = callEj3(t, x->Math2.esPrimo(x));
			System.out.println(lines.get(i)+s1);
			System.out.println(lines.get(i)+s2);
			System.out.println("===================================================================");


			

		}
	}
	
	public static void main(String[] args) {
		test3("./datos/PI4Ej3DatosEntrada.txt");
	}

}
