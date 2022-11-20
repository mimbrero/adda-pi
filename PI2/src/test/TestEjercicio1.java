package test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import ejercicios.Ejercicio1;
import util.benchmark.Benchmark;
import util.curvefitting.GraficosAjuste;
import util.curvefitting.TipoAjuste;
import util.test.Test;

public class TestEjercicio1 extends Test {

	private static final String PATH_FORMAT = "ficheros/tiempos-factorial-%s.csv";

	private static final String RECURSIVA_BIG_INTEGER = "recursiva-biginteger";
	private static final String ITERATIVA_BIG_INTEGER = "iterativa-biginteger";
	private static final String RECURSIVA_DOUBLE = "recursiva-double";
	private static final String ITERATIVA_DOUBLE = "iterativa-double";

	@Override
	public void test() {
		this.generateBenchmarks();
		this.generateGraphs();
	}

	private void generateBenchmarks() {
		this.generateBenchmark(size -> Ejercicio1.recursivaBigInteger(size), RECURSIVA_BIG_INTEGER, 1000, 50);
		this.generateBenchmark(size -> Ejercicio1.iterativaBigInteger(size), ITERATIVA_BIG_INTEGER, 1000, 50);
		this.generateBenchmark(size -> Ejercicio1.recursivaDouble(size), RECURSIVA_DOUBLE, 10000, 10000);
		this.generateBenchmark(size -> Ejercicio1.iterativaDouble(size), ITERATIVA_DOUBLE, 10000, 10000);
	}

	private void generateGraphs() {
		this.generateGraph(RECURSIVA_BIG_INTEGER, TipoAjuste.POLYNOMIALLOG);
		this.generateGraph(ITERATIVA_BIG_INTEGER, TipoAjuste.POLYNOMIALLOG);
		this.generateGraph(RECURSIVA_DOUBLE, TipoAjuste.LINEAL);
		this.generateGraph(ITERATIVA_DOUBLE, TipoAjuste.LINEAL);
		this.generateCombinedGraph();
	}

	private void generateBenchmark(Consumer<Integer> implementation, String fileName, int warmupIterations, int iterations) {
		Benchmark benchmark = new Benchmark(implementation, 2, 5000, 333, warmupIterations, iterations);
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

	private void generateCombinedGraph() {
		List<String> names = List.of(RECURSIVA_BIG_INTEGER, ITERATIVA_BIG_INTEGER, RECURSIVA_DOUBLE, ITERATIVA_DOUBLE);

		GraficosAjuste.showCombined(
				"Factorial", 
				names.stream()
					.map(fileName -> PATH_FORMAT.formatted(fileName))
					.toList(),
				names
		);
	}

	public static void main(String[] args) {
		new TestEjercicio1().test();
	}
}
