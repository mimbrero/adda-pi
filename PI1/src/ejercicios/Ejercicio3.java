package ejercicios;

import static util.ejercicio3.Ejercicio3Util.agregarAListaElMenorPunto;
import static util.ejercicio3.Ejercicio3Util.obtenerMenorPunto;
import static util.ejercicio3.Ejercicio3Util.obtenerSiguientePunto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import us.lsi.geometria.Punto2D;
import us.lsi.iterables.Iterables;
import util.ejercicio3.Tupla;

public class Ejercicio3 {

	public List<Punto2D> iterativa(String ruta1, String ruta2) {
		List<Punto2D> puntos = new ArrayList<>();
		Iterator<String> iterator1 = Iterables.file(ruta1).iterator();
		Iterator<String> iterator2 = Iterables.file(ruta2).iterator();

		// obtenemos los primeros puntos de los iteradores
		Punto2D p1 = obtenerSiguientePunto(iterator1);
		Punto2D p2 = obtenerSiguientePunto(iterator2);

		// comenzamos un bucle que acabará cuando los 2 iteradores hayan llegado al final
		while (iterator1.hasNext() || iterator2.hasNext()) {
			// agregamos a puntos el menor de los 2 puntos, o el que no sea null, si se ha acabado uno 
			// de los iteradores
			agregarAListaElMenorPunto(puntos, p1, p2);

			Integer comp = obtenerMenorPunto(p1, p2);
			
			if (comp == null) // no debería pasar nunca, dentro del while siempre hay uno que no es null
				break;

			// preparamos p1 y p2 para la siguiente iteración
			if (comp < 0)
				p1 = obtenerSiguientePunto(iterator1);
			
			else if (comp == 0)
				p2 = obtenerSiguientePunto(iterator2); // si son iguales, agregamos el segundo punto
			
			else
				p2 = obtenerSiguientePunto(iterator2);
		}

		// queda un punto por añadir, preparado en la última iteración del while
		agregarAListaElMenorPunto(puntos, p1, p2);

		return puntos;
	}

	public List<Punto2D> recursivaFinal(String ruta1, String ruta2) {
		Iterator<String> iterator1 = Iterables.file(ruta1).iterator();
		Iterator<String> iterator2 = Iterables.file(ruta2).iterator();

		// obtenemos los primeros puntos de los iteradores
		Punto2D p1 = obtenerSiguientePunto(iterator1);
		Punto2D p2 = obtenerSiguientePunto(iterator2);

		return recursivaFinal(new ArrayList<>(), iterator1, iterator2, p1, p2);
	}

	public List<Punto2D> recursivaFinal(List<Punto2D> puntos, Iterator<String> iterator1, Iterator<String> iterator2, Punto2D p1, Punto2D p2) {
		if (!iterator1.hasNext() && !iterator2.hasNext()) {
			// queda un punto por añadir, preparado en la última iteración
			agregarAListaElMenorPunto(puntos, p1, p2);
			return puntos;
		}
		
		// agregamos a puntos el menor de los 2 puntos, o el que no sea null, si se ha acabado uno 
		// de los iteradores
		agregarAListaElMenorPunto(puntos, p1, p2);

		Integer comp = obtenerMenorPunto(p1, p2);

		if (comp == null) // no debería pasar nunca, en este punto del método siempre hay uno que no es null
			return puntos;

		// preparamos p1 y p2 para la siguiente iteración
		if (comp < 0)
			p1 = obtenerSiguientePunto(iterator1);
		
		else if (comp == 0)
			p2 = obtenerSiguientePunto(iterator2); // si son iguales, agregamos el segundo punto
		
		else
			p2 = obtenerSiguientePunto(iterator2);

		// llamamos a la siguiente iteración
		return recursivaFinal(puntos, iterator1, iterator2, p1, p2);
	}

	public List<Punto2D> funcional(String ruta1, String ruta2) {
		Iterator<String> iterator1 = Iterables.file(ruta1).iterator();
		Iterator<String> iterator2 = Iterables.file(ruta2).iterator();

		return Stream.iterate(Tupla.of(iterator1, iterator2), Tupla::next)
				.filter(tupla -> !iterator1.hasNext() && !iterator2.hasNext())
				.findFirst()
				.map(Tupla::finish)
				.orElse(null);
	}
}
