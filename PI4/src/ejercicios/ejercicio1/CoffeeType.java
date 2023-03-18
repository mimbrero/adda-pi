package ejercicios.ejercicio1;

import util.parse.ParameterLookup;

public record CoffeeType(Integer quantity) {
	
	public static CoffeeType parse(String line) {
		return new CoffeeType(ParameterLookup.lookup(line, "kgdisponibles", Integer::parseInt));
	}
}
