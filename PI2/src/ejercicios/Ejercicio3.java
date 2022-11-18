package ejercicios;

import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ejercicio3 {

	public static List<String> binario(BinaryTree<Character> tree, Character character) {
		List<String> result = new ArrayList<>();
		binario(result, "", tree, character);
		return result;
	}

	public static void binario(List<String> ac, String s, BinaryTree<Character> tree, Character character) {
		switch (tree) {
			case BEmpty<Character> t -> {}
			case BLeaf<Character> t -> {
				if (t.label() == character)
					return;
				
				ac.add(s += t.label());
			}
			case BTree<Character> t -> {
				if (t.label() == character)
					return;
				
				s += t.label();

				binario(ac, s, t.left(), character);
				binario(ac, s, t.right(), character);
			}
		};
	}

	public static List<String> nario(Tree<Character> tree, Character character) {
		List<String> result = new ArrayList<>();
		nario(result, "", tree, character);
		return result;
	}

	public static void nario(List<String> ac, String s, Tree<Character> tree, Character character) {
		switch (tree) {
			case TEmpty<Character> t -> {}
			case TLeaf<Character> t -> {
				if (t.label() == character)
					return;
				
				ac.add(s += t.label());
			}
			case TNary<Character> t -> {
				if (t.label() == character)
					return;
				
				s += t.label();

				for (Tree<Character> child : tree.elements()) 
					nario(ac, s, child, character);
			}
		};
	}
	
	public static void main(String[] args) {
		Tree<Character> t = Tree.parse("A(B(C,D(E,F(G,H,I),J),K))", c -> c.charAt(0));
		
		System.out.println(nario(t, 'X'));
	}
}
