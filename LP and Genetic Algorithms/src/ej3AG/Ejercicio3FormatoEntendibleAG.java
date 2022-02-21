package ej3AG;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lectores.Ejercicio3Lector;
import us.lsi.common.Lists2;
import us.lsi.common.Tuple3;

public class Ejercicio3FormatoEntendibleAG {
	private List<Tuple3<String, Double, List<String>>> seleccion;
	
	public static Ejercicio3FormatoEntendibleAG create(List<Integer> chrom) {
		return new Ejercicio3FormatoEntendibleAG(chrom);
	}
	
	private  Ejercicio3FormatoEntendibleAG(List<Integer> chrom) {
		this.seleccion=Lists2.empty();
		for(int i=0; i<chrom.size(); i++) {
			if(chrom.get(i)==1) {
				Tuple3<String, Double, List<String>> objeto = Ejercicio3Lector.getProductos().get(i);
				seleccion.add(objeto);
			}
		}
	}
	
	public String toString() {
		String toString="Composicion del lote seleccionado:\n";
		for(Tuple3<String, Double, List<String>> objeto:seleccion) {
			toString += "   "+objeto.v1+" ( "+objeto.v2+" euros)"+" => "+objeto.v3+"\n";
		}
		toString += "Funcionalidades de la seleccion: "+getFuncionalidades()+"\n";
		toString += "Precio total del lote seleccionado: "+getPrecioTotal()+"\n";
		return toString;
	}
	
	private Double getPrecioTotal() {
		// TODO Auto-generated method stub
		return seleccion.stream().map(x->x.v2).reduce(0., (accum, x)->{return accum+x;});
	}
	
	private Set<String> getFuncionalidades(){
		// TODO Auto-generated method stub
		return seleccion.stream().map(x->x.v3).flatMap(string->string.stream()).collect(Collectors.toSet());
	}
}