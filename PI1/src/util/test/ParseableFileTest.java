package util.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public abstract class ParseableFileTest<L> extends Test {
	
	private final List<L> lines;
	
	protected ParseableFileTest(Path filePath, Function<String, L> mapper) {
		this.lines = readAndParse(filePath, mapper);
	}
	
	public abstract void test(List<L> lines);
	
	@Override
	public void test() {
		this.test(this.lines);
	}
	
	private List<L> readAndParse(Path filePath, Function<String, L> mapper) {
		try {
			List<L> lineas = Files.lines(filePath).map(mapper).toList();
			return lineas;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
