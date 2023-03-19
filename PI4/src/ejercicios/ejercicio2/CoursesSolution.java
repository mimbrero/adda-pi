package ejercicios.ejercicio2;

import java.util.List;
import java.util.stream.Collectors;

public class CoursesSolution {

	private final List<Course> courses;
	private final Double totalPrice;
	
	public CoursesSolution(List<Course> courses, Double totalPrice) {
		this.courses = courses;
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		String courses = this.courses.stream().map(Course::id).collect(Collectors.joining(", ", "{", "}"));
		
		return """
				Cursos elegidos: %s 
				Coste total: %f
				"""
				.formatted(courses, this.totalPrice);
	}
}
