package ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.List;

import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class PermutationClients implements SeqNormalData<ClientsSolution> {

	private final ClientsData clientsData;
	
	public PermutationClients(ClientsData clientsData) {
		this.clientsData = clientsData;
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}

	@Override
	public Double fitnessFunction(List<Integer> chromosome) {
		double goal = 0, error = 0;
		
		for (int i = 0; i < chromosome.size(); i++) {
			double sum = 0;
			int last = i == 0 ? 0 : chromosome.get(i - 1);

			if (this.clientsData.existsEdge(last, chromosome.get(i))) {
				sum += this.clientsData.getWeight(last, chromosome.get(i));
				goal += this.clientsData.getClient(chromosome.get(i)).profit() - sum;
			} else
				error++;
		}

		if (chromosome.get(chromosome.size() - 1) != 0) {
			if (error == 0)
				error += 1;
			else
				error *= 2;
		}

		return goal - 10000 * error;
	}
	
	@Override
	public ClientsSolution solucion(List<Integer> chromosome) {
		double distance = 0., profit = 0.;
		
		List<Client> clients = new ArrayList<>();
		clients.add(this.clientsData.getClient(0));
		
		for (int i = 0; i < chromosome.size(); i++) {
			clients.add(this.clientsData.getClient(chromosome.get(i)));
			int last = i == 0 ? 0 : chromosome.get(i - 1);

			if (this.clientsData.existsEdge(last, chromosome.get(i))) {
				distance += this.clientsData.getWeight(last, chromosome.get(i));
				profit += this.clientsData.getClient(chromosome.get(i)).profit() - distance;
			}
		}
		
		return new ClientsSolution(clients, distance, profit);
	}
	
	@Override
	public Integer itemsNumber() {
		return this.clientsData.getNumVertices();
	}
}
