package src;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio2 {
	
	
	public static Boolean callEj2(BinaryTree<String> t, List<String> l, int i) {
		if(t.isEmpty()) {
			return false;
		}
		else if(t.isBinary()) {
			if(t.getLabel().equals(l.get(i))) {
				return callEj2(t.getLeft(), l, i+1)||callEj2(t.getRight(), l, i+1);
			}else {
				return false;

			}
		}
		else {
			if(t.getLabel().equals(l.get(l.size()-1))) {
				if(t.getFather().getLabel().equals(l.get(l.size()-2))) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
	}
	
	
	public static Boolean ej2(BinaryTree<String> t, List<String> l) {
		return callEj2(t, l, 0);
	}
	
	
	public static void test2(String dir) {
		List<String> a = Files2.linesFromFile(dir);
		System.out.println("EJERCICIO 2:");
		for(int i = 0; i<a.size(); i++) {
			String[] trozos = a.get(i).split("#");
			BinaryTree<String> t = BinaryTree.parse(trozos[0]);
			String[] subString = trozos[1].substring(1, trozos[1].length()-1).split(",");
			List<String> c = new ArrayList<String>();
			for(int j =0; j<subString.length; j++) {
				c.add(subString[j]);
			}
			Boolean sol = ej2(t, c);
			System.out.println(a.get(i)+": "+ sol);
			System.out.println(t);
			System.out.println(c);
			System.out.println("=============================================");			
			
		}
	}
	
	public static void main(String[] args) {
		test2("./datos/PI4Ej2DatosEntrada.txt");
	}

}
