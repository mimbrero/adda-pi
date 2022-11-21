package ejercicios;

import java.util.regex.Pattern;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ejercicio4 {

	private static Pattern VOWELS_PATTERN = Pattern.compile("[aeiou]", Pattern.CASE_INSENSITIVE);

	public Boolean hasSameNumberOfVowelsInEachBranch(BinaryTree<String> tree) {
		return switch(tree) {
			// si es una hoja vacía, devolvemos true
			case BEmpty<String> t -> true;
			// si es una hoja, devolvemos true
			case BLeaf<String> t -> true;
			// si es un árbol, lo inspeccionamos
			case BTree<String> t -> this.inspectTree(t);
			default -> true;
		};
	}

	public Boolean hasSameNumberOfVowelsInEachBranch(Tree<String> tree) {
		return switch(tree) {
		// si es una hoja vacía, devolvemos true
			case TEmpty<String> t -> true;
			// si es una hoja, devolvemos true
			case TLeaf<String> t -> true;
			// si es un árbol, lo inspeccionamos
			case TNary<String> t -> this.inspectTree(t);
			default -> true;
		};
	}
	
	private boolean inspectTree(BTree<String> tree) {
		// devolvemos si el número de vocales es igual en las subramas, y por cada una también ejecutamos
		// el algoritmo
		return this.vowelCount(tree.left().toString()) == this.vowelCount(tree.right().toString())
				&& this.hasSameNumberOfVowelsInEachBranch(tree.left())
				&& this.hasSameNumberOfVowelsInEachBranch(tree.right());
	}
	
	private boolean inspectTree(TNary<String> tree) {
		// devolvemos si el número de vocales es igual en las subramas, y por cada una también ejecutamos
		// el algoritmo
		return tree.elements().stream()
				.map(Tree::toString)
				.map(this::vowelCount)
				.distinct().limit(2).count() <= 1
			&& tree.elements().stream()
				.allMatch(this::hasSameNumberOfVowelsInEachBranch);
	}

	private long vowelCount(String string) {
		// mediante regex vemos cuántas vocales hay, haciendo match del patrón [aeiou], es decir, si es a, e, i, o o u.
		return VOWELS_PATTERN.matcher(string.toLowerCase()).results().count();
	}
}
