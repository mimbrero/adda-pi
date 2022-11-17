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

	public String recursivaConMemoria(Map<Tupla, String> cache, int a, int b, int c) {
		Tupla tupla = new Tupla(a, b, c);
		
		// si ya la hemos calculado, la obtenemos de la caché
		if (cache.containsKey(tupla))
			return cache.get(tupla);

		String s;

		if (a < 2 && b <= 2 || c < 2)
			s = "(" + Integer.toString(a) + "+" + Integer.toString(b) + "+" + Integer.toString(c) + ")";

		else if (a < 3 || b < 3 && c <= 3)
			s = "(" + Integer.toString(c) + "-" + Integer.toString(b) + "-" + Integer.toString(a) + ")";

		else if (b % a == 0 && (a % 2 == 0 || b % 3 == 0))
			s = "(" + recursivaConMemoria(cache, a - 1, b / a, c - 1) + "*" + recursivaConMemoria(cache, a - 2, b / 2, c / 2) + ")";

		else 
			s = "(" + recursivaConMemoria(cache, a / 2, b - 2, c / 2) + "/" + recursivaConMemoria(cache, a / 3, b - 1, c / 3) + ")";

		// guardamos lo calculado en el caché
		cache.put(tupla, s);
		return s;
	}

	public String iterativa(int a, int b, int c) {
		Map<Tupla, String> map = new HashMap<>();

		String r = null;
		
		// iteramos tres variables desde 0 hasta los valores pasados como argumento para rellenar el mapa
		// en este caso, lo rellenamos ascendentemente, para usar en los valores superiores lo que ya hemos
		// rellenado.
		for (int i = 0; i <= a; i++)
			for (int j = 0; j <= b; j++)
				for (int k = 0; k <= c; k++) {
					if (i < 2 && j <= 2 || k < 2)
						r = "(" + Integer.toString(i) + "+" + Integer.toString(j) + "+" + Integer.toString(k) + ")";

					else if (i < 3 || j < 3 && k <= 3)
						r = "(" + Integer.toString(k) + "-" + Integer.toString(j) + "-" + Integer.toString(i) + ")";

					else if (j % i == 0 && (i % 2 == 0 || j % 3 == 0))
						r = "(" + map.get(new Tupla(i - 1, j / i, k - 1)) + "*" + map.get(new Tupla(i - 2, j / 2, k / 2)) + ")";

					else
						r = "(" + map.get(new Tupla(i / 2, j - 2, k / 2)) + "/" + map.get(new Tupla(i / 3, j - 1, k / 3)) + ")";

					map.put(new Tupla(i, j, k), r);
				}

		return r;
	}
}
