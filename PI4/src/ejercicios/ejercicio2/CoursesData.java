package ejercicios.ejercicio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import util.parse.ParameterLookup;

public class CoursesData {

	private final Integer maxCenters;
	private final List<Course> courses;

	public CoursesData(Integer maxCenters, List<Course> courses) {
		this.maxCenters = maxCenters;
		this.courses = courses;
	}

	public static CoursesData fromFile(Path path) throws IOException {
		Iterator<String> lineIterator = Files.lines(path)
				.filter(line -> !line.isBlank())
				.map(String::strip)
				.iterator();

		Integer maxCenters = ParameterLookup.lookup(lineIterator.next(), "Max_Centros", Integer::valueOf);
		List<Course> courses = new ArrayList<>();
		while (lineIterator.hasNext()) {
			courses.add(Course.parse(lineIterator.next()));
		}

		return new CoursesData(maxCenters, courses);
	}

	public Integer getMaxCenters() {
		return this.maxCenters;
	}

	public Integer getCoursesNumber() {
		return this.courses.size();
	}

	public Integer getTopicsNumber() {
		return (int) this.courses.stream()
				.flatMap(course -> course.topics().stream())
				.mapToInt(topic -> topic)
				.max().getAsInt();
	}

	public Integer getCentersNumber() {
		return (int) this.courses.stream()
				.map(Course::center)
				.distinct()
				.count();
	}

	public Double getCoursePrice(Integer i) {
		return this.courses.get(i).price();
	}

	public Integer getCourseCenter(Integer i) {
		return this.courses.get(i).center();
	}

	public Integer isTopicInCourse(Integer j, Integer i) {
		// j + 1 porque en los datos de entrada empiezan por 1 pero el modelo
		// está creado con las temáticas empezando en 0
		return this.courses.get(i).topics().contains(j + 1) ? 1 : 0;
	}
}
