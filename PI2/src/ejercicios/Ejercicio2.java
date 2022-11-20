package ejercicios;

import java.util.Comparator;
import java.util.List;

import us.lsi.common.IntPair;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;
import us.lsi.math.Math2;

public class Ejercicio2 {

	@SuppressWarnings("unchecked")
	public <E> void quickSort(List<E> lista, int umbral) {
		this.quickSort(lista, 0, lista.size(), (Comparator<? super E>) Comparator.naturalOrder(), umbral);
	}

	private <E> void quickSort(List<E> lista, int i, int j, Comparator<? super E> ord, Integer umbral) {
		Preconditions.checkArgument(j >= i);

		if (j - i <= umbral)
			this.ordenaBase(lista, i, j, ord);
		else {
			E pivote = this.escogePivote(lista, i, j);
			IntPair p = this.banderaHolandesa(lista, pivote, i, j, ord);
			this.quickSort(lista, i, p.first(), ord, umbral);
			this.quickSort(lista, p.second(), j, ord, umbral);       
		}
	}

	private <E> IntPair banderaHolandesa(List<E> ls, E pivote, Integer i, Integer j, Comparator<? super E> cmp) {
		Integer a=i, b=i, c=j;

		while (c - b > 0) {
			E elem = ls.get(b);

			if (cmp.compare(elem, pivote) < 0) {
				List2.intercambia(ls, a, b);
				a++;
				b++;
			} else if (cmp.compare(elem, pivote) > 0) {
				List2.intercambia(ls, b, c-1);
				c--;    
			} else
				b++;
		}

		return IntPair.of(a, b);
	}

	private <E> E escogePivote(List<E> lista, int i, int j) {
		return lista.get(Math2.getEnteroAleatorio(i, j));
	}

	private <T> void ordenaBase(List<T> lista, Integer inf, Integer sup, Comparator<? super T> ord) {
		for (int i = inf; i < sup; i++)
			for(int j = i+1; j < sup; j++)
				if(ord.compare(lista.get(i),lista.get(j))>0)
					List2.intercambia(lista, i, j);
	}
}
