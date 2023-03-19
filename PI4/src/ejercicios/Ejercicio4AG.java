package ejercicios;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ejercicios.ejercicio4.ClientsData;
import ejercicios.ejercicio4.PermutationClients;
import ejercicios.ejercicio4.ClientsSolution;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class Ejercicio4AG {
	
	public static void run(int dataFileNumber) throws IOException {
		PermutationClients permutationClients = new PermutationClients(ClientsData.fromFile("ficheros/Ejercicio4DatosEntrada"+ dataFileNumber +".txt"));
		
		AlgoritmoAG<List<Integer>, ClientsSolution> ap = AlgoritmoAG.of(permutationClients);
		ap.ejecuta();
		
		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================\n");
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(new Locale("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		for (int i = 1; i <= 2; i++)
			run(i);
	}
}
