package test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import ejercicios.Ejercicio1;
import test.Ejercicio1Test.Linea;
import util.PentaFunction;
import util.test.ParseableFileTest;

public class Ejercicio1Test extends ParseableFileTest<Linea> {

	public record Linea(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		public static Linea parse(String line) {
			String[] partes = line.split(",");
			return new Linea(Integer.valueOf(partes[0]), partes[1], Integer.valueOf(partes[2]), partes[3], Integer.valueOf(partes[4]));
		}
	}
	
	private final Ejercicio1 ejercicio1 = new Ejercicio1();
	
	public Ejercicio1Test(Path filePath) {
		super(filePath, Linea::parse);
	}

	@Override
	public void test(List<Linea> lineas) {
		printSeparator("Solución ya implementada del enunciado");
		this.test(lineas, ejercicio1::funcional);

		printIterativa();
		this.test(lineas, ejercicio1::iterativa);

		printRecursivaFinal();
		this.test(lineas, ejercicio1::recursivaFinal);
	}

	private void test(List<Linea> lineas, PentaFunction<Integer, String, Integer, String, Integer, Map<Integer, List<String>>> function) {
		int i = 1;
		for (Linea linea : lineas) {
			print("Test " + i++ + ": " + function.apply(linea.varA, linea.varB, linea.varC, linea.varD, linea.varE));
		}
	}

	public static void main(String[] args) throws IOException {
		new Ejercicio1Test(Path.of("ficheros/testsAlumnos/PI1Ej1DatosEntrada.txt")).test();
	}
}
