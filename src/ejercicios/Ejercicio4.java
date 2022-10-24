package ejercicios;

import java.util.HashMap;
import java.util.Map;

import util.ejercicio4.Tupla;

public class Ejercicio4 {

	public String recursiva(int a, int b, int c) {
		if (a < 2 && b <= 2 || c < 2)
			return "(" + Integer.toString(a) + "+" + Integer.toString(b) + "+" + Integer.toString(c) + ")";

		if (a < 3 || b < 3 && c <= 3)
			return "(" + Integer.toString(c) + "-" + Integer.toString(b) + "-" + Integer.toString(a) + ")";

		if (b % a == 0 && (a % 2 == 0 || b % 3 == 0))
			return "(" + recursiva(a - 1, b / a, c - 1) + "*" + recursiva(a - 2, b / 2, c / 2) + ")";

		return "(" + recursiva(a / 2, b - 2, c / 2) + "/" + recursiva(a / 3, b - 1, c / 3) + ")";
	}

	public String recursivaConMemoria(int a, int b, int c) {
		return recursivaConMemoria(new HashMap<>(), a, b, c);
	}

	public String recursivaConMemoria(Map<Tupla, String> memoria, int a, int b, int c) {
		Tupla tupla = new Tupla(a, b, c);
		if (memoria.containsKey(tupla))
			return memoria.get(tupla);

		String s;

		if (a < 2 && b <= 2 || c < 2)
			s = "(" + Integer.toString(a) + "+" + Integer.toString(b) + "+" + Integer.toString(c) + ")";

		else if (a < 3 || b < 3 && c <= 3)
			s = "(" + Integer.toString(c) + "-" + Integer.toString(b) + "-" + Integer.toString(a) + ")";

		else if (b % a == 0 && (a % 2 == 0 || b % 3 == 0))
			s = "(" + recursivaConMemoria(memoria, a - 1, b / a, c - 1) + "*" + recursivaConMemoria(memoria, a - 2, b / 2, c / 2) + ")";

		else 
			s = "(" + recursivaConMemoria(memoria, a / 2, b - 2, c / 2) + "/" + recursivaConMemoria(memoria, a / 3, b - 1, c / 3) + ")";

		memoria.put(tupla, s);
		return s;
	}

	public String iterativa(int a, int b, int c) {
		Map<Tupla, String> memoria = new HashMap<>();

		String r = null;

		for (int i = 0; i <= a; i++)
			for (int j = 0; j <= b; j++)
				for (int v = 0; v <= c; v++) {
					if (i < 2 && j <= 2 || v < 2)
						r = "(" + Integer.toString(i) + "+" + Integer.toString(j) + "+" + Integer.toString(v) + ")";

					else if (i < 3 || j < 3 && v <= 3)
						r = "(" + Integer.toString(v) + "-" + Integer.toString(j) + "-" + Integer.toString(i) + ")";

					else if (j % i == 0 && (i % 2 == 0 || j % 3 == 0))
						r = "(" + memoria.get(new Tupla(i - 1, j / i, v - 1)) + "*" + memoria.get(new Tupla(i - 2, j / 2, v / 2)) + ")";

					else
						r = "(" + memoria.get(new Tupla(i / 2, j - 2, v / 2)) + "/" + memoria.get(new Tupla(i / 3, j - 1, v / 3)) + ")";

					memoria.put(new Tupla(i, j, v), r);
				}

		return r;
	}
}
