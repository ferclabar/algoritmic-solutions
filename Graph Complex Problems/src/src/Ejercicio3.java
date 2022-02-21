package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.Attribute;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;

public class Ejercicio3 {
	
	public static  void main(String[] args) {
		
		Graph<String, DefaultEdge> g= new
				SimpleDirectedGraph<>(DefaultEdge.class);
		g.addVertex("Asignatura_01");
		g.addVertex("Asignatura_02");
		g.addVertex("Asignatura_03");
		g.addVertex("Asignatura_04");
		g.addVertex("Asignatura_05");
		g.addVertex("Asignatura_06");
		g.addVertex("Asignatura_07");
		g.addVertex("Asignatura_08");
		g.addVertex("Asignatura_09");
		g.addVertex("Asignatura_10");
		g.addVertex("Asignatura_11");
		g.addVertex("Asignatura_12");
		g.addVertex("Asignatura_13");
		g.addVertex("Asignatura_14");
		g.addVertex("Asignatura_15");
		g.addEdge("Asignatura_01", "Asignatura_06");
		g.addEdge("Asignatura_01", "Asignatura_07");
		g.addEdge("Asignatura_05", "Asignatura_08");
		g.addEdge("Asignatura_06", "Asignatura_09");
		g.addEdge("Asignatura_08", "Asignatura_10");
		g.addEdge("Asignatura_09", "Asignatura_10");
		g.addEdge("Asignatura_02", "Asignatura_11");
		g.addEdge("Asignatura_03", "Asignatura_11");
		g.addEdge("Asignatura_04", "Asignatura_11");
		g.addEdge("Asignatura_05", "Asignatura_12");
		g.addEdge("Asignatura_07", "Asignatura_12");
		g.addEdge("Asignatura_08", "Asignatura_13");
		g.addEdge("Asignatura_09", "Asignatura_13");
		g.addEdge("Asignatura_10", "Asignatura_14");
		g.addEdge("Asignatura_10", "Asignatura_15");
		solveEj3(g);
		


		
	}
	
	public static <E, V> void solveEj3(Graph<V, E> g) {
		//APARTADO A
		System.out.println("EJERCICIO A:\n"+ordenado(g));
		
		//APARTADO B
		System.out.println("EJERCICIO B:\n"+asignaturasSinRequisitos(g));

		Set<V> h = g.vertexSet();
		Set<V> prueba = new HashSet<V>();
		for(V v: h) {
			if(v.toString().equals("Asignatura_01")||v.toString().equals("Asignatura_02")||v.toString().equals("Asignatura_03")||v.toString().equals("Asignatura_04")||v.toString().equals("Asignatura_05")) {
				prueba.add(v);
			}
		}
		System.out.println("EJERCICIO C:\n"+asignaturasPosibles(g, prueba));
		colorEj3b(g, "datosEntrada/Salida3b.cv");
		colorEj3c(g, "datosEntrada/Salida3c.cv", prueba);

		
	}
	
	public static <V, E> Set<V> asignaturasPosibles(Graph<V,E> g, Set<V> aprobadas){
		Set<V> asignaturas = g.vertexSet();
		Set<V> res = new HashSet<V>();
		for(V v: asignaturas) {
			Set<E> requisitos = g.outgoingEdgesOf(v);
			if(requisitos.size()!=0) {
				res.add(v);
			}
		}
		return res;
	}
	
	public static <V,E> List<V> asignaturasSinRequisitos(Graph<V,E> g){
		Set<V> grafo = g.vertexSet();
		List<V> vertices = new ArrayList<V>();
		for(V v: grafo) {
			if(g.incomingEdgesOf(v).size()==0) {
				vertices.add(v);
				
			}
			
		}
		return vertices;

	}
	
	public static <V, E> List<V> asignaturasConSaliente(Graph<V,E> g) {
		
		Set<V> s = g.vert
		
	}
	
	public static <V,E> List<V> ordenado(Graph<V,E> g){
		Set<V> vertices = g.vertexSet();
		List<V> res = new ArrayList<V>();
		for(V v: vertices) {
			Set<E> requisitos =g.incomingEdgesOf(v);
			Set<V> vertex = requisitos.stream().map(x->g.getEdgeSource(x)).collect(Collectors.toSet());
			if(vertex.stream().allMatch(x->res.contains(x))){
				res.add(v);
			}
		}

		return res;
	}
	

	
	public static <V,E> void colorEj3b(Graph<V,E> g, String dir) {
		
		List<V> l1 = asignaturasSinRequisitos(g);
		
		
		Graphs2.toDot(g, dir, (x)->x.toString(),  (y)->"", (x)->{
			if(l1.contains(x)) {
				return GraphColors.getColor(Color.blue);
			}
			
			
			
			return new HashMap<String, Attribute>();
		}
		, (x)->new HashMap<String, Attribute>());
		
	}
public static <V,E> void colorEj3c(Graph<V,E> g, String dir,Set<V> aprobadas) {
		
		Set<V> l1 = asignaturasPosibles(g, aprobadas);
		
		
		Graphs2.toDot(g, dir, (x)->x.toString(),  (y)->"", (x)->{
			if(l1.contains(x)) {
				return GraphColors.getColor(Color.green);
			}
			else if(aprobadas.contains(x)) {
				return GraphColors.getColor(Color.magenta);

			}
			
			
			
			return new HashMap<String, Attribute>();
		}
		, (x)->new HashMap<String, Attribute>());
		
	}

	
	

}
