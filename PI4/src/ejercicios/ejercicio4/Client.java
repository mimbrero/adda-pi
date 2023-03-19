package ejercicios.ejercicio4;

public record Client(Integer id, Double profit) {
	
	public static Client parse(String[] line) {
		return new Client(Integer.valueOf(line[0].trim()), Double.valueOf(line[1].trim()));
	}
}
