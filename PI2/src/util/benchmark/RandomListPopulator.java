package util.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class RandomListPopulator {
	
	private Random random = new Random(System.nanoTime());	
	
	public List<Integer> generate(int size) {
		return this.generate(size, Function.identity());
	}

	public <E> List<E> generate(int size, Function<Integer, E> mapper) {
		List<E> list = new ArrayList<>();
		this.populate(list, size, mapper);
		return list;
	}
	
	public void populate(List<Integer> list, int size) {
		this.populate(list, size, Function.identity());
	}
	
	public <E> void populate(List<E> list, int size, Function<Integer, E> mapper) {
		for (int i = 0; i < size; i++)
			list.add(mapper.apply(this.random.nextInt(999_999_999)));
	}
}
