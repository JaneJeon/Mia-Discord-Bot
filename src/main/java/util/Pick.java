package util;

import java.util.List;

public class Pick {
	public static <E> E random(List<E> list) {
		return list.get((int) (Math.random() * list.size()));
	}
	
	// each item has a certain percentage of being picked
	public static <E> E random(List<E> list, List<Double> probabilities) {
		if (list.size() != probabilities.size())
			throw new RuntimeException("The items list and probabilities list must correspond 1:1");
		if (probabilities.stream().reduce(0.0, (a, b) -> a + b) != 1)
			throw new RuntimeException("The probabilities must add up to 1");
		
		int i;
		double rand = Math.random();
		for (i = 0; i < list.size()-1; i++)
			if (rand >= probabilities.get(i))
				rand -= probabilities.get(i);
			else return list.get(i);
		
		return list.get(i);
	}
}