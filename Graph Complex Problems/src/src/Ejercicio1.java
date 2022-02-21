package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.nio.Attribute;
import org.jgrapht.traverse.BreadthFirstIterator;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.SimpleEdge;


public class Ejercicio1 {

	public static void main(String[] args) {

		Graph<String, SimpleEdge<String>> g1 = GraphsReader.newGraph("datosEntrada/PI5Ej1DatosEntrada.txt",
				(x) -> new String(x[0]), (x,y,tokens)->SimpleEdge.of(x, y), Graphs2::simpleGraph, null);
		System.out.println(g1+"\n\n");
		solveEj1(g1);

	}
	
	public static <V, E> Map<Integer, List<V>> countVertexNeighbours(Graph<V, E> g){
		Map<Integer, List<V>> m = new HashMap<Integer, List<V>>();
		Set<V> vertices = g.vertexSet();
		for(V vertice: vertices) {
			Integer nb= g.degreeOf(vertice);
			if(m.containsKey(nb)) {
				m.get(nb).add(vertice);
			}
			else {
				List<V> l = new ArrayList<V>();
				l.add(vertice);
				m.put(nb, l);
			}
			
			
			
		}
		return m;
	}
	
	public static <V, E> void solveEj1(Graph<V,E>g) {
		//APARTADO A
		Map<Integer,List<V>> m = countVertexNeighbours(g);
		System.out.println("gente sin amigos:\n"+ m.getOrDefault(0, new ArrayList<V>())+"\n");
		Integer maxNb= Collections.max(m.keySet());
		System.out.println("gente mas popular:\n"+m.get(maxNb)+"\n\n");
		//APARTADO B

		List<V> l = new ArrayList<V>();
		for(V vertice: g.vertexSet()) {
			if(vertice.toString().equals("Juan")) {
				V v1 = vertice;
				l.add(v1);
			}
			else if( vertice.toString().equals("Ramiro")) {
				V v2= vertice;
				l.add(v2);
			}
		}
		List<V> res= getMasCorto(l.get(1), l.get(0), g);
		res.remove(0);
		res.remove(res.size()-1);
		
	
		List<V> noNb = m.getOrDefault(0, new ArrayList<V>());
		List<V> maxNbL = m.get(maxNb);
		//EJERCICIO 3
		Set<V> s3= g.vertexSet();
		HashSet<V> h= new HashSet<V>();
		s3.iterator().forEachRemaining(x->h.add(x));
		
		List<List<V>> grupos= new ArrayList<List<V>>();
		while(h.size()>0) {
			V v = h.iterator().next();
			BreadthFirstIterator<V, E> b = new BreadthFirstIterator<V, E>(g, v);
			List<V> grupo = new ArrayList<V>();
			b.forEachRemaining((x)->{grupo.add(x);
			h.remove(x);});
			grupos.add(grupo);
		}
		//APARTADO D
		GreedyVCImpl<V, E> cover = new GreedyVCImpl<V, E>(g);
		Set<V> amistades = cover.getVertexCover();
		//COLOREADO
		colorEj1(g, noNb, maxNbL, "datosEntrada/Salida1a.cv");
		colorEj1b(g, res, "datosEntrada/Salida1b.cv");
		colorEj1c(g, grupos, "datosEntrada/Salida1c.cv");
		colorEj1d(g, amistades, "datosEntrada/Salida1d.cv");



		System.out.println("camino mas cortode ramiro a juan:\n"+res+"\n\n");
		System.out.println("grupos de amistades:\n" +grupos+"\n\n");
		System.out.println("mensajes a enviar: "+amistades.size()+"\n"+amistades);
		
	}
	
	public static <V, E> List<V> getMasCorto(V v1, V v2, Graph<V, E> g){
		
		GraphPath<V,E> l = DijkstraShortestPath.findPathBetween(g, v1, v2);
		return l.getVertexList();
		
	}
	
	public static <V,E> void colorEj1(Graph<V,E> g, List<V> noNb, List<V> maxNb, String dir) {
		
		Graphs2.toDot(g, dir, (x)->x.toString(),  (y)->"", (x)->{
			if(noNb.contains(x)) {
				return GraphColors.getColor(Color.magenta);
			}
			else if(maxNb.contains(x)) {
				return GraphColors.getColor(Color.cyan);
			}
			return new HashMap<String, Attribute>();
		}
		, (x)->new HashMap<String, Attribute>());
		
	}
	
public static <V,E> void colorEj1b(Graph<V,E> g, List<V> res, String dir) {
		
		Graphs2.toDot(g, dir, (x)->x.toString(),  (y)->"", (x)->{
			if(res.contains(x)) {
				return GraphColors.getColor(Color.blue);
			}
			return new HashMap<String, Attribute>();
		}
		, (x)->new HashMap<String, Attribute>());
		
	}
public static <V,E> void colorEj1c(Graph<V,E> g, List<List<V>> grupos, String dir) {
	
	Graphs2.toDot(g, dir, (x)->x.toString(),  (y)->"", (x)->{
		if(grupos.get(0).contains(x) ){
			return GraphColors.getColor(Color.blue);
		}
		else if(grupos.get(1).contains(x)) {
			return GraphColors.getColor(Color.orange);
		}else if(grupos.get(2).contains(x)) {
			return GraphColors.getColor(Color.magenta);
		}else if(grupos.get(3).contains(x)) {
			return GraphColors.getColor(Color.cyan);
		}
		return new HashMap<String, Attribute>();
	}
	, (x)->new HashMap<String, Attribute>());
	
}
public static <V,E> void colorEj1d(Graph<V,E> g, Set<V> amistades, String dir) {
	
	Graphs2.toDot(g, dir, (x)->x.toString(),  (y)->"", (x)->{
		if(amistades.contains(x)) {
			return GraphColors.getColor(Color.blue);
		}
		return new HashMap<String, Attribute>();
	}
	, (x)->new HashMap<String, Attribute>());
	
}



}
