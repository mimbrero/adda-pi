package ejercicios.ejercicio4;

public record Path(Integer id, Double distance) {
	
	public static int TOTAL_COUNT;
	
	public static Path build(Double distance) {
		return new Path(TOTAL_COUNT++, distance);
	}
	
	public static Path parse(String[] line) {
		return build(Double.valueOf(line[2].trim()));
	}
}
