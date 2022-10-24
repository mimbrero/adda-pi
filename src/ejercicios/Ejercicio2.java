package ejercicios;

import java.util.stream.Stream;

import util.ejercicio2.Tupla;

public class Ejercicio2 {

	public int iterativa(int a, int b, String s) {
		int ac = 0;

		// mientras no se cumpla una de las 2 condiciones que hacen que el algoritmo termine
		while (!(s.length() == 0 || a < 2 || b < 2)) {
			if (a % s.length() < b % s.length()) {
				ac += a + b;
				s = s.substring(a % s.length(), b % s.length());
				a -= 1;
				b /= 2;
			} else {
				ac += a * b;
				s = s.substring(b % s.length(), a % s.length());
				a /= 2;
				b -= 1;
			}
		}

		// si s.length() == 0, devolvemos ac + a * a + b * b
		// si no (es decir, a < 2 || b < 2), devolvemos ac + s.length() + a + b
		return ac + (s.length() == 0 ? a * a + b * b : (s.length() + a + b));
	}

	public int recursivaNoFinal(int a, int b, String s) {
		if (s.length() == 0)
			return a * a + b * b;

		if (a < 2 || b < 2)
			return s.length() + a + b;
		
		// mientras no se cumpla una de las 2 condiciones que hacen que el algoritmo termine:
		
		if (a % s.length() < b % s.length())
			return a + b + recursivaNoFinal(a - 1, b / 2, s.substring(a % s.length(), b % s.length()));

		return a * b + recursivaNoFinal(a / 2, b - 1, s.substring(b % s.length(), a % s.length()));
	}

	public int recursivaFinal(int a, int b, String s) {
		return this.recursivaFinal(0, a, b, s);
	}

	public int recursivaFinal(int ac, int a, int b, String s) {
		if (s.length() == 0)
			return ac + a * a + b * b;

		if (a < 2 || b < 2)
			return ac + s.length() + a + b;
		
		// mientras no se cumpla una de las 2 condiciones que hacen que el algoritmo termine:

		if (a % s.length() < b % s.length())
			return recursivaFinal(a + b + ac, a - 1, b / 2, s.substring(a % s.length(), b % s.length()));

		return recursivaFinal(a * b + ac, a / 2, b - 1, s.substring(b % s.length(), a % s.length()));
	}

	public Integer funcional(int a, int b, String s) {
		// iteramos desde el primer elemento, una tupla con a, b y s (y 0 en ac)
		// pasando a la siguiente iteración con Tupla#next()
		return Stream.iterate(Tupla.of(a, b, s), Tupla::next)
				.filter(Tupla::finished) // cuando haya una tupla cuyo método #finished() retorne true,
				.findFirst() // elegimos la primera que cumpla la condición
				.map(Tupla::finish) // la mapeamos al resultado final
				.orElse(null); // para salir del Optional. Esto nunca se va a dar, ya que el iterador es finito y #finished() siempre se dará en algún caso.
	}
}
