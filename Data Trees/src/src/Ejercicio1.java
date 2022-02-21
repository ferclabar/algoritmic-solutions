package src;

import java.util.List;
import java.util.function.Function;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio1 {
	
	public static Boolean ej1(BinaryTree<Integer> root) {
		
		if (root.isEmpty()){
			return false;
		}
		else if(root.isLeaf()) {
			return true;
		}
		else {
			int s = root.getLeft().getLabel()+root.getRight().getLabel();
			if(s==root.getLabel()) {
				return ej1(root.getLeft())&&ej1(root.getRight());
			}
			else {
				return false;
			}
		}
		
	}
	
	
	
	public static void test1(String dir) {
		List<String> l = Files2.linesFromFile(dir);
		System.out.println("EJERCICIO 1: ");
		for(int i = 0; i<l.size(); i++) {
			BinaryTree<String> t = BinaryTree.parse(l.get(i));
			BinaryTree<Integer> h = t.map(x->Integer.parseInt(x));
			Boolean sol = ej1(h);
			System.out.println(l.get(i)+": "+ sol);
			System.out.println("==================================================");

		}
		
	}
	

	public static void main(String[] args) {
		
		test1("./datos/PI4Ej1DatosEntrada.txt");
		
	}

}
