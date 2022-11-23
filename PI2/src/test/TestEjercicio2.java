package test;

import java.util.Arrays;
import java.util.List;

import ejercicios.Ejercicio2;
import util.benchmark.RandomListPopulator;
import util.curvefitting.TipoAjuste;
import util.test.BenchmarkTest;

public class TestEjercicio2 extends BenchmarkTest {

	private final RandomListPopulator listPopulator = new RandomListPopulator();
	private final Ejercicio2 ejercicio2 = new Ejercicio2();
	private final Integer[] thresholds = {4, 25, 100, 500};

	public TestEjercicio2() {
		super("QuickSort", "ficheros/tiempos-quicksort-umbral-%s.csv");
	}

	@Override
	public void test() {
		this.generateBenchmarks();
		this.generateGraphs();
	}

	private void generateBenchmarks() {
		printSeparator("BENCHMARKS");

		for (Integer threshold : this.thresholds)
			this.generateBenchmark(
					size -> ejercicio2.quickSort(listPopulator.generate(size), threshold), 
					threshold.toString(), 50, 50000, 500, 1, 5, 30
			);
	}

	private void generateGraphs() {
		printSeparator("RESULTS");
		
		for (Integer threshold : this.thresholds)
			this.generateGraph(threshold.toString(), TipoAjuste.NLOGN_0);

		List<String> fileNames = Arrays.stream(this.thresholds).map(threshold -> threshold.toString()).toList();
		this.generateCombinedGraph(fileNames);
	}

	public static void main(String[] args) {
		new TestEjercicio2().test();
	}
}
