package ejercicios.ejercicio3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeJobs implements ValuesInRangeData<Integer, JobsSolution> {

	private final JobsData jobsData;

	public InRangeJobs(JobsData jobsData) {
		this.jobsData = jobsData;
	}

	@Override
	public Integer size() {
		return this.jobsData.getJobsNumber() * this.jobsData.getResearchersNumber();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> chromosome) {
		double goal = 0, error = 0;

		Integer n = this.jobsData.getResearchersNumber();
		Integer m = this.jobsData.getJobsNumber();
		Integer sp = this.jobsData.getSpecialitiesNumber();

		// por cada trabajo: función objetivo y restricción que relaciona las variables en el modelo matemático
		for (int job = 0; job < m; job++) {
			// trabajadores para este trabajo (vemos el cromosoma como una matriz, fila j * n)
			List<Integer> researchers = chromosome.subList(job * n, (job + 1) * n);

			boolean jobAssigned = false;
			for (int speciality = 0; speciality < sp; speciality++) { 
				int sum = 0;
				for (int researcher = 0; researcher < n; researcher++)
					sum += researchers.get(researcher) * this.jobsData.isSpecialist(researcher, speciality);

				if (sum == this.jobsData.getJobDaysForSpeciality(job, speciality))
					jobAssigned = true;
				else
					error += Math.abs(sum - this.jobsData.getJobDaysForSpeciality(job, speciality));
			}

			if (jobAssigned)
				goal += this.jobsData.getQuality(job);
		}

		// restricción de la capacidad de cada trabajador
		for (int researcher = 0; researcher < n; researcher++) { 
			int sum = 0;
			// viendo el cromosoma como una matriz, obtenemos la columna del trabajador
			for (int j = researcher; j < chromosome.size(); j += n)
				sum += chromosome.get(j);

			if (sum > this.jobsData.getResearcherCapacity(researcher))
				error += sum - this.jobsData.getResearcherCapacity(researcher);
		}

		return goal - 10000 * error;
	}

	@Override
	public JobsSolution solucion(List<Integer> value) {
		Integer n = this.jobsData.getResearchersNumber();
		Integer m = this.jobsData.getJobsNumber();
		Integer sp = this.jobsData.getSpecialitiesNumber();

		int quality = 0;
		Map<Researcher, List<Integer>> days = new HashMap<>();
		
		for (int job = 0; job < m; job++) {
			List<Integer> researchers = value.subList(job * n, (job + 1) * n);

			for (int researcherIndex = 0; researcherIndex < n; researcherIndex++) {
				Researcher researcher = this.jobsData.getResearchers().get(researcherIndex);
				days.computeIfAbsent(researcher, r -> new ArrayList<>()).add(researchers.get(researcherIndex));
			}

			boolean jobAssigned = true;
			for (int speciality = 0; speciality < sp; speciality++) { 
				int sum = 0;
				for (int researcher = 0; researcher < n; researcher++)
					sum += researchers.get(researcher) * this.jobsData.isSpecialist(researcher, speciality);
				
				if (sum < this.jobsData.getJobDaysForSpeciality(job, speciality)) {
					jobAssigned = false;
					break;
				}
			}
			
			if (jobAssigned)
				quality += this.jobsData.getQuality(job);
			else {
				for (List<Integer> list : days.values())
					list.set(job, 0);
			}
		}
		
		return new JobsSolution(quality, days);
	}

	@Override
	public Integer max(Integer i) {
		// viendo el cromosoma como una matriz, obtenemos el número de fila, el del trabajador.
		return this.jobsData.getResearcherCapacity(i % this.jobsData.getResearchersNumber()) + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
}
