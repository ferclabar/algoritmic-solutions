package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import us.lsi.common.Files2;

public class Ejercicio2 {
	
	public static SubSecuencia getSubSecuenciaMaxima(List<Double> lista){
		Comparator<SubSecuencia> ord = Comparator.naturalOrder();
		return getSubSecuenciaMaxima(lista,0,lista.size(),ord);
	}
	
	private static SubSecuencia getSubSecuenciaMaxima(List<Double> lista, int i, int j, Comparator<SubSecuencia> ord){
		SubSecuencia r = null;	
		if(j-i <= 1){
			r = new SubSecuencia(lista,i,j);
		}else{
			int k = (i+j)/2;
			SubSecuencia s1 = getSubSecuenciaMaxima(lista, i, k, ord);
			SubSecuencia s2 = getSubSecuenciaMaxima(lista, k, j, ord);
			SubSecuencia s3 = getSubSecuenciaMaximaCentrada(lista,i,j,k);
			r = Stream.of(s1, s2, s3).max(ord).get();
		}
		return r;
	}	
	
	private static SubSecuencia getSubSecuenciaMaximaCentrada(List<Double> lista, int a, int b, int k){
		Double sumaMaxima = Double.MIN_VALUE;
		Double suma = 0.;
		int i1 = k;
		int j1 = k;
		int from = i1;
		int to = j1;
		for(i1 = k-1;i1 >= a; i1--){
			suma = suma + lista.get(i1);  
			if(suma > sumaMaxima){
				sumaMaxima = suma;
				from = i1;
			}
		}
		suma = sumaMaxima;
		for(j1=k;j1<b;j1++){
			suma = suma + lista.get(j1);  
			if(suma > sumaMaxima){
				sumaMaxima = suma;
				to = j1+1;
			}
		}
		SubSecuencia sm = new SubSecuencia(lista,from,to);
		return sm;
	}
	
	public static class SubSecuencia implements Comparable<SubSecuencia>{
		private List<Double> lista;
		private Integer from;
		private Integer to;
		
		
		public SubSecuencia(List<Double> lista, Integer from, Integer to) {
			super();
			this.lista = lista;
			this.from = from;
			this.to = to;
		}

		public Integer getFrom() {
			return from;
		}

		public Integer getTo() {
			return to;
		}

		public Double getSuma() {
			Double suma = 0.;
			for(int i= from; i<to;i++){
				suma = suma+lista.get(i);
			}
			return suma;
		}

		@Override
		public int compareTo(SubSecuencia s) {
			// TODO Auto-generated method stub
			return this.getSuma().compareTo(s.getSuma());
		}

		@Override
		public String toString() {
			return "SubSecuencia [from=" + from + ", to=" + to + ", getSuma()="
					+ getSuma() + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((from == null) ? 0 : from.hashCode());
			result = prime * result + ((lista == null) ? 0 : lista.hashCode());
			result = prime * result + ((to == null) ? 0 : to.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SubSecuencia other = (SubSecuencia) obj;
			if (from == null) {
				if (other.from != null)
					return false;
			} else if (!from.equals(other.from))
				return false;
			if (lista == null) {
				if (other.lista != null)
					return false;
			} else if (!lista.equals(other.lista))
				return false;
			if (to == null) {
				if (other.to != null)
					return false;
			} else if (!to.equals(other.to))
				return false;
			return true;
		}
		
	}
	
	public static List<List<Double>> test2(String dir){
		
		List<String> datos = Files2.linesFromFile(dir);
		int i = 0;
		List<List<Double>> sol = new ArrayList<>();
		while(i<=datos.size()-1) {
			List<Double> l = new ArrayList<>();
			String [] trozos = datos.get(i).split(", ");
			int j = 0;
			while(j<=trozos.length-1) {
				Double num = Double.parseDouble(trozos[j]);
				l.add(num);
				j++;
			}
			sol.add(l);
			i++;
		
		}
		return sol;
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("EJERCICIO 2");
		List<List<Double>> test = test2("../Practica3/tests/PI3Ej2DatosEntrada.txt");
		System.out.println(getSubSecuenciaMaxima(test.get(0)));
		System.out.println(getSubSecuenciaMaxima(test.get(1)));
		System.out.println(getSubSecuenciaMaxima(test.get(2)));
		System.out.println(getSubSecuenciaMaxima(test.get(3)));
		System.out.println(getSubSecuenciaMaxima(test.get(4)));
		System.out.println(getSubSecuenciaMaxima(test.get(5)));

	}
	
	
	
	
	



}
