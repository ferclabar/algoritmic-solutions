package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.Attribute;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.SimpleEdge;

public class Ejercicio2 {
	
	

	public static void main(String[] args) {
		
		Graph<String, DefaultEdge> g=	new SimpleGraph<>(DefaultEdge.class);
		g.addVertex("Grupo 1");
		g.addVertex("Grupo 2");
		g.addVertex("Grupo 3");
		g.addVertex("Grupo 4");
		g.addVertex("Grupo 5");
		g.addVertex("Grupo 6");
		g.addVertex("Grupo 7");
		g.addVertex("Grupo 8");
		g.addVertex("Grupo 9");

		g.addEdge("Grupo 1", "Grupo 2" );
		g.addEdge("Grupo 1", "Grupo 3" );
		g.addEdge("Grupo 1", "Grupo 5" );
		g.addEdge("Grupo 2", "Grupo 3" );
		g.addEdge("Grupo 2", "Grupo 4" );
		g.addEdge("Grupo 2", "Grupo 6" );
		g.addEdge("Grupo 3", "Grupo 5" );
		g.addEdge("Grupo 4", "Grupo 5" );
		g.addEdge("Grupo 4", "Grupo 6" );
		g.addEdge("Grupo 5", "Grupo 6" );
		g.addEdge("Grupo 7", "Grupo 8" );
		g.addEdge("Grupo 7", "Grupo 9" );
		g.addEdge("Grupo 8", "Grupo 9" );
		solveEj2(g);

		

		

	}
	
	public static <E, V> void solveEj2(Graph<V,E>g ) {
		GreedyColoring<V, E> colors = new GreedyColoring<V, E>(g);
		Map<V,Integer> map = colors.getColoring().getColors();
		Integer franjas = colors.getColoring().getNumberColors();
		Map<Integer, List<V>> horario = new HashMap<Integer, List<V>>();
		for(Map.Entry<V, Integer> entry : map.entrySet()) {
			if(horario.containsKey(entry.getValue())) {
				horario.get(entry.getValue()).add(entry.getKey());
			}
			else {
				List<V> l = new ArrayList<V>();
				l.add(entry.getKey());
				horario.put(entry.getValue(), l);
			}
		}
		
		System.out.println("Numero de franjas: "+franjas+"\n");
		System.out.println("Primera hora: "+horario.get(0)+"\n");
		System.out.println("Segunda hora: "+horario.get(1)+"\n");
		System.out.println("Tercera hora: "+horario.get(2)+"\n");

		colorEj2a(g, map, "datosEntrada/Salida2a.cv");
	}
	
	public static <V,E> void colorEj2a(Graph<V,E> g, Map<V,Integer> map , String dir) {
		
		Graphs2.toDot(g, dir, (x)->x.toString(),  (y)->"", (x)->{
			
				if(map.get(x)==0) {
					return GraphColors.getColor(Color.blue);

				}
				else if(map.get(x)==1) {
					return GraphColors.getColor(Color.magenta);

				}
				else if(map.get(x)==2) {
					return GraphColors.getColor(Color.orange);

				}
			
			return new HashMap<String, Attribute>();
		}
		, (x)->new HashMap<String, Attribute>());
		
	}

}
