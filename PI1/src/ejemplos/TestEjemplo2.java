package ejemplos;

import java.nio.file.Files;
import java.nio.file.Path;

import static ejemplos.Ejemplo2.*;

public class TestEjemplo2 {
	public static void main(String[] args) {
//		Files.lines(Path.of(""))
		
		int a = 1034949423, b = 58234139;
		System.out.println(iterativa(a, b));
		System.out.println(recursivaNoFinal(a, b));
		System.out.println(recursivaFinal(a, b));
		System.out.println(funcional(a, b));
	}
}
