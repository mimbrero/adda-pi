package ejercicios.ejercicio3;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import util.parse.ParameterLookup;

public record Job(Integer quality, Map<Integer, Integer> distribution) {

	public static Job parse(String line) {
		Integer quality = ParameterLookup.lookup(line, "calidad", Integer::valueOf);
		Map<Integer, Integer> distribution = Arrays.stream(ParameterLookup.lookup(line, "reparto").split(","))
				.map(item -> item.replaceAll("[()]", ""))
				.map(item -> item.split(":"))
				.collect(Collectors.toMap(pair -> Integer.valueOf(pair[0]), pair -> Integer.valueOf(pair[1])));
		
		return new Job(quality, distribution);
	}
	
	public Integer getDaysNeededForSpeciality(Integer speciality) {
		return this.distribution.get(speciality);
	}
}
