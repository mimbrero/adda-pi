package test;

import java.util.function.Function;

import ejercicios.Ejercicio4;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;
import util.test.TreeTest;

public class TestEjercicio4 extends TreeTest<String> {
	
	private final Ejercicio4 ejercicio4 = new Ejercicio4();
	
	public TestEjercicio4() {
		super(4, Function.identity());
	}

	@Override
	public void testBinaryTree(BinaryTree<String> tree) {
		print("%s: %b".formatted(tree, ejercicio4.hasSameNumberOfVowelsInEachBranch(tree)));
	}

	@Override
	public void testNaryTree(Tree<String> tree) {
		print("%s: %b".formatted(tree, ejercicio4.hasSameNumberOfVowelsInEachBranch(tree)));
	}
	
	public static void main(String[] args) {
		new TestEjercicio4().test();
	}
}
