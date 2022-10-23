package util.ejercicio3;

import static util.ejercicio3.Ejercicio3Util.agregarAListaElMenorPunto;
import static util.ejercicio3.Ejercicio3Util.obtenerMenorPunto;
import static util.ejercicio3.Ejercicio3Util.obtenerSiguientePunto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import us.lsi.geometria.Punto2D;

public record Tupla(List<Punto2D> puntos, Iterator<String> iterator1, Iterator<String> iterator2, Punto2D p1, Punto2D p2) {

	public static Tupla of(Iterator<String> iterator1, Iterator<String> iterator2) {
		return new Tupla(
				new ArrayList<>(), 
				iterator1,
				iterator2,
				// obtenemos los primeros puntos de los iteradores
				obtenerSiguientePunto(iterator1), 
				obtenerSiguientePunto(iterator2)
		);
	}

	public Tupla next() {
		Punto2D p1 = this.p1;
		Punto2D p2 = this.p2;

		// agregamos a puntos el menor de los 2 puntos, o el que no sea null, si se ha acabado uno 
		// de los iteradores
		agregarAListaElMenorPunto(puntos, p1, p2);

		Integer comp = obtenerMenorPunto(p1, p2);

		if (comp == null) // no debería pasar nunca, en este punto del método siempre hay uno que no es null
			return new Tupla(puntos, iterator1, iterator2, p1, p2);

		// preparamos p1 y p2 para la siguiente iteración
		if (comp < 0)
			p1 = obtenerSiguientePunto(iterator1);

		else if (comp == 0)
			p2 = obtenerSiguientePunto(iterator2); // si son iguales, agregamos el segundo punto

		else
			p2 = obtenerSiguientePunto(iterator2);

		return new Tupla(puntos, iterator1, iterator2, p1, p2);
	}

	public List<Punto2D> finish() {
		// queda un punto por añadir, preparado en la última iteración
		agregarAListaElMenorPunto(puntos, p1, p2);
		return puntos;
	}
}
