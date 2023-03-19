package ejercicios.ejercicio3;

import util.parse.ParameterLookup;

public record Researcher(String id, Integer capacity, Integer speciality) {

	public static Researcher parse(String line) {
		Integer capacity = ParameterLookup.lookup(line, "capacidad", Integer::valueOf);
		Integer speciality = ParameterLookup.lookup(line, "especialidad", Integer::valueOf);
		
		return new Researcher(line.split(":")[0], capacity, speciality);
	}
}
