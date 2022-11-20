package test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

import ejercicios.Ejercicio1;
import util.benchmark.Benchmark;
import util.curvefitting.GraficosAjuste;
import util.curvefitting.TipoAjuste;
import util.test.Test;

public class TestEjercicio1 extends Test {

	private static final String PATH_FORMAT = "ficheros/TiemposFactorial%s.csv";

	private static final String RECURSIVA_BIG_INTEGER = "Recursiva_BigInteger";
	private static final String ITERATIVA_BIG_INTEGER = "Iterativa_BigInteger";
	private static final String RECURSIVA_DOUBLE = "Recursiva_Double";
	private static final String ITERATIVA_DOUBLE = "Iterativa_Double";

	@Override
	public void test() {
		this.generateBenchmarks();
		this.generateGraphs();
	}

	private void generateBenchmarks() {
		this.generateBenchmark(t -> Ejercicio1.recursivaBigInteger(t), RECURSIVA_BIG_INTEGER);
		this.generateBenchmark(t -> Ejercicio1.iterativaBigInteger(t), ITERATIVA_BIG_INTEGER);
		this.generateBenchmark(t -> Ejercicio1.recursivaDouble(t), RECURSIVA_DOUBLE);
		this.generateBenchmark(t -> Ejercicio1.iterativaDouble(t), ITERATIVA_DOUBLE);
	}

	private void generateGraphs() {
		this.generateGraph(RECURSIVA_BIG_INTEGER, TipoAjuste.POLYNOMIALLOG);
		this.generateGraph(ITERATIVA_BIG_INTEGER, TipoAjuste.POLYNOMIALLOG);
		this.generateGraph(RECURSIVA_DOUBLE, TipoAjuste.POLYNOMIALLOG);
		this.generateGraph(ITERATIVA_DOUBLE, TipoAjuste.POLYNOMIALLOG);
	}

	private void generateBenchmark(Consumer<Integer> implementation, String fileName) {
		Benchmark benchmark = new Benchmark(implementation, 2, 5000, 200, 50, 100);
		benchmark.run();

		try {
			benchmark.writeResults(Path.of(PATH_FORMAT.formatted(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateGraph(String fileName, TipoAjuste tipoAjuste) {
		GraficosAjuste.show(PATH_FORMAT.formatted(fileName), tipoAjuste, fileName);
	}

	public static void main(String[] args) {
		new TestEjercicio1().test();
	}
}
