package util.benchmark;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

public class Benchmark implements Runnable {

	private final Consumer<Integer> algorithm;

	private final int minSize;
	private final int maxSize;
	private final int sizeIncrement;

	private final int measures;
	private final int warmupIterations;
	private final int iterations;

	private final Map<Integer, Double> times = new HashMap<>();

	public Benchmark(Consumer<Integer> algorithm, int minSize, int maxSize, int sizeIncrement,
			int measures, int warmupIterations, int iterations) {
		this.algorithm = algorithm;
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.sizeIncrement = sizeIncrement;
		this.measures = measures;
		this.warmupIterations = warmupIterations;
		this.iterations = iterations;
	}

	@Override
	public void run() {
		for (int i = 0; i < measures; i++)
			for (int size = minSize; size < maxSize; size += sizeIncrement) {
				System.out.println("Running algorithm with size " + size + "...");
				double time = this.run(size);
				
				double lastTime = this.times.getOrDefault(size, time);
				this.times.put(size, Math.min(time, lastTime));
			}
	}

	private double run(int size) {
		// warmup iterations
		for (int i = 0; i < warmupIterations; i++)
			algorithm.accept(size);

		long[] times = new long[iterations];

		for (int i = 0; i < iterations; i++) {
			long startTime = System.nanoTime();
			algorithm.accept(size);
			times[i] = System.nanoTime() - startTime;
		}

		return Arrays.stream(times).average().getAsDouble();
	}

	public Map<Integer, Double> getResults() {
		if (this.times.isEmpty()) {
			throw new IllegalStateException("benchmark hasn't been already run");
		}
		return Collections.unmodifiableMap(this.times);
	}

	public void writeResults(Path path) throws IOException {
		if (this.times.isEmpty()) {
			throw new IllegalStateException("benchmark hasn't been already run");
		}

		Locale.setDefault(Locale.US);
		
		List<String> lines = this.times.entrySet().stream()
				.map(entry -> "%d,%f".formatted(entry.getKey(), entry.getValue()))
				.toList();

		Files.write(path, lines, Charset.defaultCharset());
	}
}
