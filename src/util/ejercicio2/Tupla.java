package util.ejercicio2;

public record Tupla(int ac, int a, int b, String s) {
	public static Tupla of(int a, int b, String s) {
		return new Tupla(0, a, b, s);
	}
	
	public Tupla next() {
		if (a % s.length() < b % s.length())
			return new Tupla(a + b + ac, a - 1, b / 2, s.substring(a % s.length(), b % s.length()));

		return new Tupla(a * b + ac, a / 2, b - 1, s.substring(b % s.length(), a % s.length()));
	}
	
	public boolean finished() {
		return s.length() == 0 || a < 2 || b < 2; // true si se cumple uno de los dos casos que finalizan el algoritmo
	}
	
	public int finish() {
		// si s.length() == 0, devolvemos ac + a * a + b * b
		// si no (es decir, a < 2 || b < 2), devolvemos ac + s.length() + a + b
		return ac + (s.length() == 0 ? a * a + b * b : (s.length() + a + b));
	}
}
