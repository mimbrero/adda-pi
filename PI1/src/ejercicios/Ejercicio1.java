package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.ejercicio1.EnteroCadena;

public class Ejercicio1 {
	
	private final UnaryOperator<EnteroCadena> NEXT = elem -> EnteroCadena.of(
			elem.a()+2, 
			elem.a()%3==0 
					? elem.s() + elem.a().toString() 
					: elem.s().substring(elem.a() % elem.s().length())
	);

	public Map<Integer, List<String>> funcional(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		return Stream.iterate(EnteroCadena.of(varA,varB), elem -> elem.a() < varC, NEXT)
				.map(elem -> elem.s() + varD)
				.filter(nom -> nom.length() < varE)
				.collect(Collectors.groupingBy(String::length));
	}
	
	public Map<Integer, List<String>> iterativa(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		Map<Integer, List<String>> map = new HashMap<>();
		// obtenemos el primer elemento de la iteración
		EnteroCadena elem = EnteroCadena.of(varA, varB);
		
		while(elem.a() < varC) {
			String nom = elem.s() + varD;
			int length = nom.length();
			
			if (length < varE)
				// añadimos a map, si no existe, una nueva ArrayList a la clave length y, a esa lista (o la ya existente),
				// añadimos nom
				map.computeIfAbsent(length, l -> new ArrayList<>()).add(nom);
			
			// preparamos elem para la siguiente iteración
			elem = NEXT.apply(elem);
		}
		
		return map;
	}
	
	public Map<Integer, List<String>> recursivaFinal(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		EnteroCadena enteroCadena = EnteroCadena.of(varA, varB); // obtenemos el primer elemento de la iteración
		return recursivaFinal(new HashMap<>(), enteroCadena, varC, varD, varE);
	}
	
	public Map<Integer, List<String>> recursivaFinal(Map<Integer, List<String>> map, EnteroCadena elem, Integer varC, String varD, Integer varE) {
		// si se cumple la condición que termina el algoritmo, devolvemos el mapa que hemos estado rellenando
		if (elem.a() >= varC)
			return map;

		String nom = elem.s() + varD;
		int length = nom.length();
		
		if (length < varE)
			// añadimos a map, si no existe, una nueva ArrayList a la clave length y, a esa lista (o la ya existente),
			// añadimos nom
			map.computeIfAbsent(length, l -> new ArrayList<>()).add(nom);

		elem = NEXT.apply(elem); // preparamos elem para la siguiente iteración
		return recursivaFinal(map, elem, varC, varD, varE); // devolvemos lo que retorne la siguiente iteración
	}
}
