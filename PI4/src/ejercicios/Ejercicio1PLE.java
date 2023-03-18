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
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		
		coffeeData = CoffeeData.fromFile(Path.of("ficheros/Ejercicio1DatosEntrada1.txt"));
		
		AuxGrammar.generate(Ejercicio1PLE.class, "lsi-models/ejercicio1.lsi", "lp-models/ejercicio1.lp");
		GurobiSolution solution = GurobiLp.gurobi("lp-models/ejercicio1.lp");
		System.out.println(solution.toString((s, d) -> d > 0.));
	}

}
