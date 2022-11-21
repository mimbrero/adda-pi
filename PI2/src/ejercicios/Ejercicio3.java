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

	public List<String> binario(BinaryTree<Character> tree, Character character) {
		List<String> result = new ArrayList<>();
		// comenzamos el algoritmo recursivo con una string vacía, ya que no hemos revisado todavía ninguna rama
		this.binario(result, "", tree, character);
		return result;
	}

	public void binario(List<String> ac, String s, BinaryTree<Character> tree, Character character) {
		switch (tree) {
			case BEmpty<Character> t -> {}
			case BLeaf<Character> t -> {
				if (t.label() == character)
					return;
				
				// en este punto, el caracter no es el que nos han pasado por parámetro, por tanto hemos terminado
				// y añadimos este caracter y los de las ramas superiores a la lista resultante
				ac.add(s += t.label());
			}
			case BTree<Character> t -> {
				if (t.label() == character)
					return;
				
				// en este punto, el caracter no es el que nos han pasado por parámetro, por tanto añadimos este
				// caracter a la string del camino de las ramas
				s += t.label();

				// y por cada rama seguimos con el algoritmo
				this.binario(ac, s, t.left(), character);
				this.binario(ac, s, t.right(), character);
			}
		};
	}

	public List<String> nario(Tree<Character> tree, Character character) {
		List<String> result = new ArrayList<>();
		// comenzamos el algoritmo recursivo con una string vacía, ya que no hemos revisado todavía ninguna rama
		this.nario(result, "", tree, character);
		return result;
	}

	public void nario(List<String> ac, String s, Tree<Character> tree, Character character) {
		switch (tree) {
			case TEmpty<Character> t -> {}
			case TLeaf<Character> t -> {
				if (t.label() == character)
					return;

				// en este punto, el caracter no es el que nos han pasado por parámetro, por tanto hemos terminado
				// y añadimos este caracter y los de las ramas superiores a la lista resultante
				ac.add(s += t.label());
			}
			case TNary<Character> t -> {
				if (t.label() == character)
					return;

				// en este punto, el caracter no es el que nos han pasado por parámetro, por tanto añadimos este
				// caracter a la string del camino de las ramas
				s += t.label();

				// y por cada rama seguimos con el algoritmo
				for (Tree<Character> child : tree.elements()) 
					this.nario(ac, s, child, character);
			}
		};
	}
}
