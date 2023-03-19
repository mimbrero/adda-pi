package ejercicios;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

import ejercicios.ejercicio1.CoffeeData;
import ejercicios.ejercicio1.CoffeeSolution;
import ejercicios.ejercicio1.InRangeCoffee;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class Ejercicio1AG {
	
	public static void run(int dataFileNumber) throws IOException {
		InRangeCoffee inRangeCoffee = new InRangeCoffee(CoffeeData.fromFile(Path.of("ficheros/Ejercicio1DatosEntrada"+ dataFileNumber +".txt")));
		
		AlgoritmoAG<List<Integer>, CoffeeSolution> ap = AlgoritmoAG.of(inRangeCoffee);
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
		
		for (int i = 1; i <= 3; i++)
			run(i);
	}
}
