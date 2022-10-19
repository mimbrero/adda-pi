package ejemplos;

import java.util.stream.Stream;

public class Ejemplo2 {
	
	public static String iterativa(int a, int b) {
		String ac = "";
		while (a >= 5 && b >= 5) {
			ac = ac + (a + b);
			a /= 2;
			b -= 2;
		}
		
		return "%s(%d)".formatted(ac, a * b);
	}
	
	public static String recursivaNoFinal(int a, int b) {
		if (a < 5 || b < 5)
			return "(%d)".formatted(a * b);
		
		return String.valueOf(a+b) + recursivaNoFinal(a/2, b - 2);
	}
	
	public static String recursivaFinal(int a, int b) {
		return recursivaFinal(a, b, "");
	}

	public static String recursivaFinal(int a, int b, String ac) {
		if (a < 5 || b < 5)
			return ac + "(%d)".formatted(a * b);
		
		return recursivaFinal(a / 2, b - 2, ac + (a + b));
	}
	
	public static String funcional(int a, int b) {
		return Stream.iterate(new Tupla(a, b, ""), Tupla::next)
				.filter(Tupla::last)
				.findFirst()
				.map(tupla -> "%s(%d)".formatted(tupla.ac(), tupla.a() * tupla.b()))
				.orElse(null);
	}
	
	private record Tupla(int a, int b, String ac) {
		public boolean last() {
			return a < 5 || b < 5;
		}
		
		public Tupla next() {
			return new Tupla(a / 2, b - 2, ac + String.valueOf(a + b));
		}
	}
}
