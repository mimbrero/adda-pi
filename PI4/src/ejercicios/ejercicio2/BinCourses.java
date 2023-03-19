package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.ag.BinaryData;

public class BinCourses implements BinaryData<CoursesSolution> {

	private final CoursesData coursesData;

	public BinCourses(CoursesData coursesData) {
		this.coursesData = coursesData;
	}

	@Override
	public Integer size() {
		return this.coursesData.getCoursesNumber();
	}

	@Override
	public Double fitnessFunction(List<Integer> chromosome) {
		double goal = 0, error = 0;

		Set<Integer> topics = new HashSet<>();
		Set<Integer> centers = new HashSet<>();
		for (int course = 0; course < chromosome.size(); course++) {
			if (chromosome.get(course) <= 0)
				continue;

			goal += this.coursesData.getCoursePrice(course);
			topics.addAll(this.coursesData.getCourseTopics(course));
			centers.add(this.coursesData.getCourseCenter(course));
		}

		if (topics.size() < this.coursesData.getTopicsNumber())
			error += this.coursesData.getTopicsNumber() - topics.size();

		if (centers.size() > this.coursesData.getMaxCenters())
			error += centers.size() - this.coursesData.getMaxCenters();

		return -goal - 10000 * error;
	}

	@Override
	public CoursesSolution solucion(List<Integer> chromosome) {
		List<Course> courses = new ArrayList<>();
		for (int i = 0; i < chromosome.size(); i++)
			if (chromosome.get(i) == 1)
				courses.add(this.coursesData.getCourse(i));
		
		double price = courses.stream().mapToDouble(Course::price).sum();

		return new CoursesSolution(courses, price);
	}
}
