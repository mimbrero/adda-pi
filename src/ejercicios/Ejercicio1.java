package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.EnteroCadena;

public class Ejercicio1 {

	public record Linea(Integer varA, String varB, Integer varC, String varD, Integer varE) {
	}
	
	public static Map<Integer, List<String>> funcional(Linea linea) {
		return funcional(linea.varA(), linea.varB(), linea.varC(), linea.varD(), linea.varE());
	}

	public static Map<Integer, List<String>> funcional(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		UnaryOperator<EnteroCadena> nx = elem -> EnteroCadena.of(
				elem.a()+2, 
				elem.a()%3==0 
						? elem.s() + elem.a().toString() 
						: elem.s().substring(elem.a() % elem.s().length())
		);

		return Stream.iterate(EnteroCadena.of(varA,varB), elem -> elem.a() < varC, nx)
				.map(elem -> elem.s() + varD)
				.filter(nom -> nom.length() < varE)
				.collect(Collectors.groupingBy(String::length));
	}
	
	public static Map<Integer, List<String>> iterativa(Linea linea) {
		return iterativa(linea.varA(), linea.varB(), linea.varC(), linea.varD(), linea.varE());
	}
	
	public static Map<Integer, List<String>> iterativa(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		Map<Integer, List<String>> map = new HashMap<>();
		EnteroCadena elem = EnteroCadena.of(varA, varB);
		
		while(elem.a() < varC) {
			String nom = elem.s() + varD;
			int length = nom.length();
			
			if (length < varE)
				map.computeIfAbsent(length, l -> new ArrayList<>()).add(nom);
			
			elem = EnteroCadena.of(
					elem.a()+2, 
					elem.a()%3==0 
							? elem.s() + elem.a().toString() 
							: elem.s().substring(elem.a()%elem.s().length())
			);
		}
		
		return map;
	}
	
	public static Map<Integer, List<String>> recursivaFinal(Linea linea) {
		return recursivaFinal(linea.varA(), linea.varB(), linea.varC(), linea.varD(), linea.varE());
	}
	
	public static Map<Integer, List<String>> recursivaFinal(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		return ejercicioC(new HashMap<>(), EnteroCadena.of(varA, varB), varC, varD, varE);
	}
	
	public static Map<Integer, List<String>> ejercicioC(Map<Integer, List<String>> map, EnteroCadena elem, Integer varC, String varD, Integer varE) {
		if (elem.a() >= varC)
			return map;

		String nom = elem.s() + varD;
		int length = nom.length();
		
		if (length < varE) {
			if (!map.containsKey(length))
				map.put(length, new ArrayList<>());
			
			map.get(length).add(nom);
		}
		
		elem = EnteroCadena.of(
				elem.a()+2, 
				elem.a()%3==0 
						? elem.s() + elem.a().toString() 
						: elem.s().substring(elem.a()%elem.s().length())
		);
		return ejercicioC(map, elem, varC, varD, varE);
	}
}
