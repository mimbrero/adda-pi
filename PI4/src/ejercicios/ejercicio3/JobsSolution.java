package ejercicios.ejercicio3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JobsSolution {

	private final Integer quality;
	private final Map<Researcher, List<Integer>> days;

	public JobsSolution(Integer quality, Map<Researcher, List<Integer>> days) {
		this.quality = quality;
		this.days = days;
	}

	@Override
	public String toString() {
		String days = this.days.entrySet().stream()
				.map(entry -> entry.getKey().id() + ": " + entry.getValue())
				.sorted()
				.collect(Collectors.joining("\n"));

		return """
				Reparto obtenido (días trabajados por cada investigador en cada trabajo):
				%s

				SUMA DE LAS CALIDADES DE LOS TRABAJOS REALIZADOS: %d
				"""
				.formatted(days, this.quality);
	}
}
