package test;

import java.util.List;

import ejercicios.Ejercicio3;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;
import util.test.TreeTest;

public class TestEjercicio3 extends TreeTest<Character> {

	private final Ejercicio3 ejercicio3 = new Ejercicio3();
	
	public TestEjercicio3() {
		super(3, string -> string.charAt(0));
	}
	
	@Override
	protected void testBinaryTrees(List<String> lines) {
		lines.forEach(this::testBinaryTree);
	}
	
	@Override
	public void testNaryTrees(List<String> lines) {
		lines.forEach(this::testNaryTree);
	}

	private void testBinaryTree(String line) {
		String[] parts = line.split("#");
		
		BinaryTree<Character> tree = BinaryTree.parse(parts[0], string -> string.charAt(0));
		Character character = parts[1].charAt(0);

		print("Árbol: %s	Caracter: %s	[%s]".formatted(tree, character, ejercicio3.binario(tree, character)));
	}

	private void testNaryTree(String line) {
		String[] parts = line.split("#");
		
		Tree<Character> tree = Tree.parse(parts[0], string -> string.charAt(0));
		Character character = parts[1].charAt(0);

		print("Árbol: %s	Caracter: %s	[%s]".formatted(tree, character, ejercicio3.nario(tree, character)));
	}
	
	public static void main(String[] args) {
		new TestEjercicio3().test();
	}
}
