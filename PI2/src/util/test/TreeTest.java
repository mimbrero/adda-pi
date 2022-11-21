package util.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public abstract class TreeTest<E> extends Test {
	
	private final int number;
	private final Function<String, E> mapper;
	
	public TreeTest(int number, Function<String, E> mapper) {
		this.number = number;
		this.mapper = mapper;
	}

	@Override
	public void test() {
		List<String> binaries;
		List<String> naries;
		
		try {
			binaries = Files.readAllLines(Path.of("ficheros/Ejercicio%dDatosEntradaBinario.txt".formatted(number)));
			naries = Files.readAllLines(Path.of("ficheros/Ejercicio%dDatosEntradaNario.txt".formatted(number)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		printSeparator("Árboles binarios");
		this.testBinaryTrees(binaries);

		printSeparator("Árboles n-arios");
		this.testNaryTrees(naries);
	}
	
	protected void testBinaryTrees(List<String> lines) {
		lines.stream().map(line -> BinaryTree.parse(line, mapper)).forEach(this::testBinaryTree);
	}
	
	protected void testNaryTrees(List<String> lines) {
		lines.stream().map(line -> Tree.parse(line, mapper)).forEach(this::testNaryTree);
	}
	
	public void testBinaryTree(BinaryTree<E> tree) {
	}
	
	public void testNaryTree(Tree<E> tree) {
	}
}
