package src;

import java.util.List;

import org.apache.commons.math3.fraction.Fraction;

import us.lsi.common.Files2;
import us.lsi.common.Matrix;
import us.lsi.common.View4;

public class Ejercicio1 {
	
	public static boolean esquinasDiferentes(Matrix<Fraction> matriz) {
		
		int n = matriz.nc();
		boolean r;
		Fraction e1 = matriz.get(0, 0);
		Fraction e2 = matriz.get(0, n-1);
		Fraction e3 = matriz.get(n-1, 0);
		Fraction e4 = matriz.get(n-1, n-1);
		
		r = (e1.equals(e2))||(e1.equals(e3))||(e1.equals(e4))||(e2.equals(e3))||(e2.equals(e4))||(e3.equals(e4));
		if(r == true) {
			return false;
		}
		else {
			return true;
		}

		
	}
	
	public static boolean ej1(Matrix<Fraction> matriz) {
		boolean r;
		if (matriz.nc()>=2) {
			if(esquinasDiferentes(matriz)) {
				View4<Matrix<Fraction>> trozos = matriz.views();
				Matrix<Fraction> esquina1 = trozos.a;
				Matrix<Fraction> esquina2 = trozos.b;
				Matrix<Fraction> esquina3 = trozos.c;
				Matrix<Fraction> esquina4 = trozos.d;
				
				r= ej1(esquina1)&&ej1(esquina2)
						&&ej1(esquina3)&&ej1(esquina4);

			}
			else {
				r = false;
			}
			
		}
		else {
			r= true;
		}
		
		return r;
	}
	
	public static Matrix<Fraction> test1(String dir) {
		
		
		List<String> a = Files2.linesFromFile(dir);
		Integer[][] array = new Integer[a.size()][a.size()];
		int i = 0;
		while(i<=a.size()-1) {
			
			String[] trozos = a.get(i).split(" ");
			int j = 0;
			while(j<=trozos.length-1) {
				int num = Integer.parseInt(trozos[j]);
				array[i][j]=num;
				j++;
			}
			i++;
		}
		Matrix<Fraction> m = Matrix.of(array);
		return m;
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("EJERCICIO1");

		System.out.println(ej1(test1("../Practica3/tests/PI3Ej1DatosEntrada1.txt")));
		System.out.println(ej1(test1("../Practica3/tests/PI3Ej1DatosEntrada2.txt")));
		System.out.println(ej1(test1("../Practica3/tests/PI3Ej1DatosEntrada3.txt")));	
		System.out.println(ej1(test1("../Practica3/tests/PI3Ej1DatosEntrada4.txt")));
		System.out.println(ej1(test1("../Practica3/tests/PI3Ej1DatosEntrada5.txt")));

		System.out.println(ej1(test1("../Practica3/tests/PI3Ej1DatosEntrada6.txt")));



		
	}

}
