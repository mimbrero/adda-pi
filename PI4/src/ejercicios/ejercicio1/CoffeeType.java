package ejercicios.ejercicio1;

import util.parse.ParameterLookup;

public record CoffeeType(String id, Integer quantity) {

	public static CoffeeType parse(String line) {
		Integer quantity = ParameterLookup.lookup(line, "kgdisponibles", Integer::parseInt);
		return new CoffeeType(line.split(":")[0], quantity);
	}
}
