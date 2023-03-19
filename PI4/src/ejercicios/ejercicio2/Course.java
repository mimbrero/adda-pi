package ejercicios.ejercicio2;

import java.util.Set;

import us.lsi.common.Set2;

public record Course(String id, Set<Integer> topics, Double price, Integer center) {

	public static Course parse(String id, String line) {
		String[] properties = line.split(":");
		
		Set<Integer> topics = Set2.parse(properties[0].replaceAll("[{}]", ""), ",", Integer::valueOf);
		return new Course(id, topics, Double.valueOf(properties[1]), Integer.valueOf(properties[2]));
	}
}
