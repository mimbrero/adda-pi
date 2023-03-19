package ejercicios.ejercicio4;

import org.jgrapht.Graph;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class ClientsData {

	public Graph<Client, Path> graph;
	
	public ClientsData(Graph<Client, Path> graph) {
		this.graph = graph;
	}

	public static ClientsData fromFile(String fichero) {
		Graph<Client, Path> graph = GraphsReader.newGraph(fichero, Client::parse, Path::parse, Graphs2::simpleWeightedGraph);
		return new ClientsData(graph);
	}
	
	public Integer getNumVertices() {
		return this.graph.vertexSet().size();
	}
	
	public Client getClient(Integer i) {
		for (Client client : this.graph.vertexSet()) {
			if (client.id() == i)
				return client;
		}
		return null;
	}
	
	public Boolean existsEdge(Integer i, Integer j) {
		return this.graph.containsEdge(this.getClient(i), this.getClient(j));
	}
	
	public Double getWeight(Integer i, Integer j) {
		return this.graph.getEdge(this.getClient(i), this.getClient(j)).distance();
	}
}

