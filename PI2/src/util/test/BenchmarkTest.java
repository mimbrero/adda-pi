package util.test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import util.benchmark.Benchmark;
import util.curvefitting.GraficosAjuste;
import util.curvefitting.TipoAjuste;

public abstract class BenchmarkTest extends Test {
	
	private final String name;
	private final String pathFormat;

	public BenchmarkTest(String name, String pathFormat) {
		this.name = name;
		this.pathFormat = pathFormat;
	}

	protected void generateBenchmark(Consumer<Integer> implementation, String fileName,
			int minSize, int maxSize, int sizeIncrement, int warmupIterations, int iterations) {
		Benchmark benchmark = new Benchmark(implementation, minSize, maxSize, sizeIncrement, warmupIterations, iterations);
		benchmark.run();

		try {
			benchmark.writeResults(Path.of(pathFormat.formatted(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void generateGraph(String fileName, TipoAjuste tipoAjuste) {
		GraficosAjuste.show(pathFormat.formatted(fileName), tipoAjuste, fileName);
	}

	protected void generateCombinedGraph(List<String> names) {
		GraficosAjuste.showCombined(
				name, 
				names.stream()
					.map(fileName -> pathFormat.formatted(fileName))
					.toList(),
				names
		);
	}
}
