package ejercicios;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

import ejercicios.ejercicio1.CoffeeData;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio1PLE {

	private static CoffeeData coffeeData;

	public static Integer getTypesNumber() {
		return coffeeData.getTypesNumber();
	}

	public static Integer getVarietiesNumber() {
		return coffeeData.getVarietiesNumber();
	}

	public static Integer getVarietyProfit(Integer i) {
		return coffeeData.getVarietyProfit(i);
	}

	public static Integer getTypeQuantity(Integer j) {
		return coffeeData.getTypeQuantity(j);
	}

	public static Double getTypePercentage(Integer i, Integer j) {
		return coffeeData.getTypePercentage(i, j);
	}

	public static void run(int dataFileNumber) throws IOException {
		Path dataFilePath = Path.of("ficheros/Ejercicio1DatosEntrada%d.txt".formatted(dataFileNumber));
		String lpFilePath = "lp-models/ejercicio1-%d.lp".formatted(dataFileNumber);

		coffeeData = CoffeeData.fromFile(dataFilePath);

		AuxGrammar.generate(Ejercicio1PLE.class, "lsi-models/ejercicio1.lsi", lpFilePath);
		GurobiSolution solution = GurobiLp.gurobi(lpFilePath);
		System.out.println(solution.toString((s, d) -> d > 0.));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);

		for (int i = 1; i <= 3; i++)
			run(i);
	}
}
