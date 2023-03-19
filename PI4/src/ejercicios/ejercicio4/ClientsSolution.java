package ejercicios.ejercicio4;

import java.util.List;
import java.util.stream.Collectors;

public class ClientsSolution {

	private List<Client> clients;
	private Double distance; 
	private Double profit;

	public ClientsSolution(List<Client> clients, Double distance, Double profit) {
		this.clients = clients;
		this.distance = distance;
		this.profit = profit;
	}

	@Override
	public String toString() {
		String clients = this.clients.stream()
				.map(Client::id)
				.map(i -> i.toString())
				.collect(Collectors.joining(", ", "[", "]"));
		
		return """
				%s
				Kms: %f
				Beneficio: %f"""
				.formatted(clients, this.distance, this.profit);
	}
}
