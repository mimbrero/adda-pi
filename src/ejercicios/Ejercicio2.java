package ejercicios;

public class Ejercicio2 {

	public int iterativa(int a, int b, String s) {
		int ac = 0;

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

		return ac + (s.length() == 0 ? a * a + b * b : s.length() + a + b);
	}

	public int recursivaNoFinal(int a, int b, String s) {
		if (s.length() == 0)
			return a * a + b * b;

		if (a < 2 || b < 2)
			return s.length() + a + b;

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

		if (a % s.length() < b % s.length())
			return recursivaFinal(a + b + ac, a - 1, b / 2, s.substring(a % s.length(), b % s.length()));

		return recursivaFinal(a * b + ac, a / 2, b - 1, s.substring(b % s.length(), a % s.length()));
	}


	public int funcional(int a, int b, String s) {
		return 0;
	}
}
