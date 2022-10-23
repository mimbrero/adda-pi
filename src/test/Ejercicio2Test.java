package test;

import java.nio.file.Path;
import java.util.List;

import ejercicios.Ejercicio2;
import test.Ejercicio2Test.Linea;
import util.TriFunction;
import util.test.ParseableFileTest;

public class Ejercicio2Test extends ParseableFileTest<Linea> {

	public record Linea(Integer a, Integer b, String s) {
		public static Linea parse(String line) {
			String[] partes = line.split(",");
			return new Linea(Integer.valueOf(partes[0]), Integer.valueOf(partes[1]), partes[2]);
		}
	}

	private final Ejercicio2 ejercicio2 = new Ejercicio2();

	public Ejercicio2Test(Path filePath) {
		super(filePath, Linea::parse);
	}

	@Override
	public void test(List<Linea> lineas) {
		printIterativa();
		this.test(lineas, ejercicio2::iterativa);

		printRecursivaNoFinal();
		this.test(lineas, ejercicio2::recursivaNoFinal);

		printRecursivaFinal();
		this.test(lineas, ejercicio2::recursivaFinal);
	}

	private void test(List<Linea> lineas, TriFunction<Integer, Integer, String, Integer> function) {
		int i = 1;
		for (Linea linea : lineas) {
			print("Test " + i++ + ": " + function.apply(linea.a, linea.b, linea.s));
		}
	}

	public static void main(String[] args) {
		new Ejercicio2Test(Path.of("ficheros/testsAlumnos/PI1Ej2DatosEntrada.txt")).test();
	}
}
