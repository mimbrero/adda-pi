package ejercicios;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

import ejercicios.ejercicio3.JobsData;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {

	private static JobsData jobsData;

	public static Integer getResearchersNumber() {
		return jobsData.getResearchersNumber();
	}
	
	public static Integer getJobsNumber() {
		return jobsData.getJobsNumber();
	}
	
	public static Integer getSpecialitiesNumber() {
		return jobsData.getSpecialitiesNumber();
	}
	
	public static Integer getResearcherCapacity(Integer researcher) {
		return jobsData.getResearcherCapacity(researcher);
	}
	
	public static Integer isSpecialist(Integer researcher, Integer speciality) {
		return jobsData.isSpecialist(researcher, speciality);
	}
	
	public static Integer getQuality(Integer job) {
		return jobsData.getQuality(job);
	}
	
	public static Integer getJobDaysForSpeciality(Integer job, Integer speciality) {
		return jobsData.getJobDaysForSpeciality(job, speciality);
	}

	public static void run(int dataFileNumber) throws IOException {
		Path dataFilePath = Path.of("ficheros/Ejercicio3DatosEntrada%d.txt".formatted(dataFileNumber));
		String lpFilePath = "lp-models/ejercicio3-%d.lp".formatted(dataFileNumber);

		jobsData = JobsData.fromFile(dataFilePath);

		AuxGrammar.generate(Ejercicio3PLE.class, "lsi-models/ejercicio3.lsi", lpFilePath);
		GurobiSolution solution = GurobiLp.gurobi(lpFilePath);
		System.out.println(solution.toString((s, d) -> d > 0.));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);

		for (int i = 1; i <= 3; i++)
			run(i);
	}
}
