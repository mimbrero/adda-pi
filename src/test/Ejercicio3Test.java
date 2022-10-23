package test;

import java.util.List;
import java.util.function.BiFunction;

import ejercicios.Ejercicio3;
import us.lsi.geometria.Punto2D;
import util.test.Test;

public class Ejercicio3Test extends Test {
	
	private final String filePath;
	private final Ejercicio3 ejercicio3 = new Ejercicio3();
	
	public Ejercicio3Test(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void test() {
		printIterativa();
		this.test(ejercicio3::iterativa);

		printRecursivaFinal();
		this.test(ejercicio3::recursivaFinal);

		printFuncional();
		this.test(ejercicio3::funcional);
	}

	private void test(BiFunction<String, String, List<Punto2D>> function) {
		for (int i = 1; i <= 3; i++) {
			List<Punto2D> resultados = function.apply(this.filePath.formatted(i, "A"), this.filePath.formatted(i, "B"));
			print("Test " + i + ": Los siguientes " + resultados.size() + " puntos: " + resultados);
			print();
		}
	}

	public static void main(String[] args) {
		new Ejercicio3Test("ficheros/testsAlumnos/PI1Ej3DatosEntrada%d%s.txt").test();
	}
}
