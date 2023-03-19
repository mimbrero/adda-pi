package ejercicios.ejercicio3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JobsData {

	private final List<Researcher> researchers;
	private final List<Job> jobs;

	public JobsData(List<Researcher> researchers, List<Job> jobs) {
		this.researchers = researchers;
		this.jobs = jobs;
	}

	public static JobsData fromFile(Path path) throws IOException {
		Iterator<String> lineIterator = Files.lines(path)
				.filter(line -> !line.isBlank())
				.map(String::strip)
				.skip(1) // eliminamos la primera línea, // INVESTIGADORES
				.iterator();

		List<Researcher> researchers = new ArrayList<>();
		// por cada línea del archivo hasta que llegue a una línea que sea // TRABAJOS
		while (lineIterator.hasNext()) {
			String line = lineIterator.next();
			if (line.equals("// TRABAJOS"))
				break;

			researchers.add(Researcher.parse(line));
		}

		List<Job> jobs = new ArrayList<>();
		// por cada línea restante hasta el final
		while (lineIterator.hasNext()) {
			jobs.add(Job.parse(lineIterator.next()));
		}
		
		return new JobsData(researchers, jobs);
	}

	public Integer getResearchersNumber() {
		return this.researchers.size();
	}
	
	public Integer getJobsNumber() {
		return this.jobs.size();
	}
	
	public Integer getSpecialitiesNumber() {
		return (int) this.researchers.stream()
				.map(Researcher::speciality)
				.distinct()
				.count();
	}
	
	public Integer getResearcherCapacity(Integer researcher) {
		return this.researchers.get(researcher).capacity();
	}
	
	public Integer isSpecialist(Integer researcher, Integer speciality) {
		return this.researchers.get(researcher).speciality() == speciality ? 1 : 0;
	}
	
	public Integer getQuality(Integer job) {
		return this.jobs.get(job).quality();
	}
	
	public Integer getJobDaysForSpeciality(Integer job, Integer speciality) {
		return this.jobs.get(job).getDaysNeededForSpeciality(speciality);
	}
	
	public List<Researcher> getResearchers() {
		return this.researchers;
	}
}
