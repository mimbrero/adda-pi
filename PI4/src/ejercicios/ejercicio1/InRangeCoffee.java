package ejercicios.ejercicio1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeCoffee implements ValuesInRangeData<Integer, CoffeeSolution> {

	private final CoffeeData coffeeData;

	public InRangeCoffee(CoffeeData coffeeData) {
		this.coffeeData = coffeeData;
	}

	@Override
	public Integer size() {
		return this.coffeeData.getVarietiesNumber();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> chromosome) {
		double goal = 0, error = 0;

		for (int variety = 0; variety < this.coffeeData.getVarietiesNumber(); variety++)
			goal += chromosome.get(variety) * this.coffeeData.getVarietyProfit(variety);

		// restricción: hay una cantidad limitada de cada tipo de café
		for (int type = 0; type < this.coffeeData.getTypesNumber(); type++) {
			double sum = 0;
			for (int variety = 0; variety < this.coffeeData.getVarietiesNumber(); variety++) {
				sum += chromosome.get(variety) * this.coffeeData.getTypePercentage(variety, type);
			}

			if (sum > this.coffeeData.getTypeQuantity(type)) {
				// error = kilos de más
				error += sum - this.coffeeData.getTypeQuantity(type);
			}
		}

		return goal - 10000 * error;
	}

	@Override
	public CoffeeSolution solucion(List<Integer> chromosome) { 
		double profit = 0.;
		Map<CoffeeVariety, Integer> results = new HashMap<>();
		for (int i = 0; i < chromosome.size(); i++) {
			profit += chromosome.get(i) * this.coffeeData.getVarietyProfit(i);
			results.put(this.coffeeData.getVarieties().get(i), chromosome.get(i));
		}
		
		return new CoffeeSolution(profit, results);
	}

	@Override
	public Integer max(Integer i) {
		return this.coffeeData.getMaxKg(i).intValue() + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

}
