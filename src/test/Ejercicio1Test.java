package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import ejercicios.Ejercicio1;
import ejercicios.Ejercicio1.Linea;

public class Ejercicio1Test {

	public static void main(String[] args) throws IOException {
		List<Linea> lineas = Files.lines(Path.of("ficheros/testsAlumnos/PI1Ej1DatosEntrada.txt"))
				.map(linea -> {
					String[] partes = linea.split(",");
					return new Linea(
							Integer.valueOf(partes[0]),
							partes[1],
							Integer.valueOf(partes[2]),
							partes[3],
							Integer.valueOf(partes[4])
					);
				})
				.toList();

		test(lineas, Ejercicio1::funcional);
//		test(lineas, Ejercicio1::iterativa);
//		test(lineas, Ejercicio1::recursivaFinal);
	}

	private static void test(List<Linea> lineas, Function<Linea, Map<Integer, List<String>>> supplier) {
		int i = 1;
		for (Linea linea : lineas) {
			System.out.println("Test " + i++ + ": " + supplier.apply(linea));
		}
	}
}
