package test;

import java.nio.file.Path;
import java.util.List;

import ejercicios.Ejercicio4;
import test.Ejercicio4Test.Linea;
import util.TriFunction;
import util.test.ParseableFileTest;

public class Ejercicio4Test extends ParseableFileTest<Linea> {

	public record Linea(Integer a, Integer b, Integer c) {
		public static Linea parse(String line) {
			String[] partes = line.split(",");
			return new Linea(Integer.valueOf(partes[0]), Integer.valueOf(partes[1]), Integer.valueOf(partes[2]));
		}
	}

	private final Ejercicio4 ejercicio4 = new Ejercicio4();

	public Ejercicio4Test(Path filePath) {
		super(filePath, Linea::parse);
	}

	@Override
	public void test(List<Linea> lineas) {
		printSeparator("Solución recursiva sin memoria");
		this.test(lineas, ejercicio4::recursiva);

		printSeparator("Solución recursiva con memoria");
		this.test(lineas, ejercicio4::recursivaConMemoria);
		
		printIterativa();
		this.test(lineas, ejercicio4::iterativa);
	}

	private void test(List<Linea> lineas, TriFunction<Integer, Integer, Integer, String> function) {
		int i = 1;
		for (Linea linea : lineas) {
			print("Test " + i++ + ": " + function.apply(linea.a, linea.b, linea.c));
		}
	}

	public static void main(String[] args) {
		new Ejercicio4Test(Path.of("ficheros/testsAlumnos/PI1Ej4DatosEntrada.txt")).test();
	}
}
