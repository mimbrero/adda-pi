package ejercicios.ejercicio1;

import java.util.Map;
import java.util.stream.Collectors;

public class CoffeeSolution {

	private final Double profit;
	private final Map<CoffeeVariety, Integer> soluciones;
	
	public CoffeeSolution(Double profit, Map<CoffeeVariety, Integer> soluciones) {
		this.profit = profit;
		this.soluciones = soluciones;
	}
	
	@Override
	public String toString() {
		String varieties = this.soluciones.entrySet().stream()
				.map(entry -> entry.getKey().id() + ": " + entry.getValue() + " kgs")
				.collect(Collectors.joining("\n"));
		
		return """
				Variedades de café seleccionadas:
				%s
				Beneficio: %f
				""".formatted(varieties, this.profit);
	}
}
