package ejercicios.ejercicio1;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import util.parse.ParameterLookup;

public record CoffeeVariety(String id, Integer profit, Map<CoffeeType, Double> composition) {

	/**
	 * Parsea una línea de un archivo del ejercicio 1.
	 * 
	 * @param typesByName un mapa vinculando cada tipo de café con su ID
	 * @param line línea a parsear
	 * @return la variedad de café correspondiente a la línea
	 */
	public static CoffeeVariety parse(Map<String, CoffeeType> typesByName, String line) {
		Integer profit = ParameterLookup.lookup(line, "beneficio", Integer::parseInt);
		
		Map<CoffeeType, Double> composition = Arrays.stream(ParameterLookup.lookup(line, "comp").split(","))
			.map(item -> item.replaceAll("[\\(\\)]", ""))
			.map(item -> item.split(":"))
			.collect(Collectors.toMap(item -> typesByName.get(item[0]), item -> Double.parseDouble(item[1])));

		return new CoffeeVariety(line.split(" -> ")[0], profit, composition);
	}
}
