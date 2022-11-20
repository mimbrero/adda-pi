package test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import ejercicios.Ejercicio2;
import util.benchmark.Benchmark;
import util.benchmark.RandomListPopulator;
import util.curvefitting.GraficosAjuste;
import util.curvefitting.TipoAjuste;
import util.test.Test;

public class TestEjercicio2 extends Test {

	private static final String PATH_FORMAT = "ficheros/tiempos-quicksort-umbral-%d.csv";

	@Override
	public void test() {
		this.generateBenchmarks();
		this.generateGraphs();
	}

	private void generateBenchmarks() {
		RandomListPopulator populator = new RandomListPopulator();

		this.generateBenchmark(size -> Ejercicio2.quickSort(populator.generate(size), 4), 4);
		this.generateBenchmark(size -> Ejercicio2.quickSort(populator.generate(size), 25), 25);
		this.generateBenchmark(size -> Ejercicio2.quickSort(populator.generate(size), 100), 100);
		this.generateBenchmark(size -> Ejercicio2.quickSort(populator.generate(size), 500), 500);
	}

	private void generateGraphs() {
		this.generateGraph(4, TipoAjuste.NLOGN_0);
		this.generateGraph(25, TipoAjuste.NLOGN_0);
		this.generateGraph(100, TipoAjuste.NLOGN_0);
		this.generateGraph(500, TipoAjuste.NLOGN_0);
		this.generateCombinedGraph();
	}

	private void generateBenchmark(Consumer<Integer> implementation, int fileName) {
		Benchmark benchmark = new Benchmark(implementation, 50, 50000, 500, 5, 30);
		benchmark.run();

		try {
			benchmark.writeResults(Path.of(PATH_FORMAT.formatted(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateGraph(Integer fileName, TipoAjuste tipoAjuste) {
		GraficosAjuste.show(PATH_FORMAT.formatted(fileName), tipoAjuste, fileName.toString());
	}

	private void generateCombinedGraph() {
		List<Integer> names = List.of(4, 25, 100, 500);

		GraficosAjuste.showCombined(
				"QuickSort", 
				names.stream()
					.map(fileName -> PATH_FORMAT.formatted(fileName))
					.toList(),
				names.stream().map(i -> i.toString()).toList()
		);
	}

	public static void main(String[] args) {
		new TestEjercicio2().test();
	}
}
