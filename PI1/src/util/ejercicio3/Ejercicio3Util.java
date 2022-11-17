package util.ejercicio3;

import java.util.Iterator;
import java.util.List;

import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;

public class Ejercicio3Util {

	private Ejercicio3Util() {
		throw new UnsupportedOperationException(); // static util class
	}

	public static Punto2D parsePunto2D(String line) {
		String[] splitted = line.split(",");
		return Punto2D.of(Double.valueOf(splitted[0]), Double.valueOf(splitted[1]));
	}

	/**
	 * Obtiene el siguiente punto del iterador pasado como parámetro que se encuentre en el primer o
	 * tercer cuadrante. Si no hay más, devolverá null.
	 * 
	 * @param iterator iterador del que obtener el siguiente punto
	 * @return el siguiente punto del iterador que esté en el primer o tercer cuadrante, o null si
	 * no hay más puntos que cumplan esa condición
	 */
	public static Punto2D obtenerSiguientePunto(Iterator<String> iterator) {
		Punto2D punto = null;

		while (punto == null || punto.getCuadrante() != Cuadrante.PRIMER_CUADRANTE && punto.getCuadrante() != Cuadrante.TERCER_CUADRANTE) {
			if (!iterator.hasNext())
				return null;

			punto = parsePunto2D(iterator.next());
		}

		return punto;
	}

	/**
	 * Retornará:
	 * <ul>
	 *  <li>null si p1 y p2 son null</li>
	 * 	<li>-1 si p2 es null ó p1 < p2</li>
	 * 	<li>0 si p1 == p2</li>
	 * 	<li>1 si p1 es null ó p2 < p1</li>
	 * </ul>
	 * 
	 * @param p1 punto 1
	 * @param p2 punto 2
	 * @return -1, 0 o 1 dependiendo de si p1 es menor, igual o mayor que p2, o null si p1 y p2 son null
	 */
	public static Integer obtenerMenorPunto(Punto2D p1, Punto2D p2) {
		// si p1 y p2 son null, devolvemos null
		if (p1 == null && p2 == null)
			return null;

		return p1 == null ? 1 : p2 == null ? -1 : p1.compareTo(p2);
	}

	/**
	 * Agrega a la lista pasada como argumento el menor de los 2 puntos provistos, el que no sea null en caso
	 * de que uno de los dos sea null, el segundo en caso de que sean iguales o no añade nada si los dos 
	 * puntos son null.
	 * 
	 * @param lista lista a la que añadir
	 * @param p1 punto 1
	 * @param p2 punto 2
	 */
	public static void agregarAListaElMenorPunto(List<Punto2D> lista, Punto2D p1, Punto2D p2) {
		Integer comp = obtenerMenorPunto(p1, p2);
		
		if (comp == null)
			return;

		lista.add(comp < 0 ? p1 : p2);
	}
}
