package ejercicios;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

import ejercicios.ejercicio2.CoursesData;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2PLE {

	private static CoursesData coursesData;

	public static Integer getMaxCenters() {
		return coursesData.getMaxCenters();
	}

	public static Integer getCoursesNumber() {
		return coursesData.getCoursesNumber();
	}

	public static Integer getTopicsNumber() {
		return coursesData.getTopicsNumber();
	}

	public static Integer getCentersNumber() {
		return coursesData.getCentersNumber();
	}

	public static Double getCoursePrice(Integer i) {
		return coursesData.getCoursePrice(i);
	}

	public static Integer getCourseCenter(Integer i) {
		return coursesData.getCourseCenter(i);
	}

	public static Integer isTopicInCourse(Integer j, Integer i) {
		return coursesData.isTopicInCourse(j, i);
	}

	public static void run(int dataFileNumber) throws IOException {
		Path dataFilePath = Path.of("ficheros/Ejercicio2DatosEntrada%d.txt".formatted(dataFileNumber));
		String lpFilePath = "lp-models/ejercicio2-%d.lp".formatted(dataFileNumber);

		coursesData = CoursesData.fromFile(dataFilePath);

		AuxGrammar.generate(Ejercicio2PLE.class, "lsi-models/ejercicio2.lsi", lpFilePath);
		GurobiSolution solution = GurobiLp.gurobi(lpFilePath);
		System.out.println(solution.toString((s, d) -> d > 0.));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);

		for (int i = 1; i <= 3; i++)
			run(i);
	}
}
