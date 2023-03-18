package util.parse;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterLookup {

	private ParameterLookup() {
		// utility class, not instantiable
	}

	public static <T> T lookup(String line, String parameter, Function<String, T> parsingFunction) {
		return parsingFunction.apply(lookup(line, parameter));
	}

	public static String lookup(String line, String parameter) {
		// después de un igual, cualquier secuencia de caracteres hasta que encuentre un punto y coma
		// o un fin de String ($). \s* significa ninguno o algún espacio.
		Pattern pattern = Pattern.compile(parameter + "\\s*=\\s*(.+?)(;|$)");
		Matcher matcher = pattern.matcher(line);

		if (!matcher.find())
			return null;

		// el grupo 0 devuelve todo el match, el 1 devuelve el primer grupo, los primeros paréntesis,
		// es decir, el parámetro que buscamos
		return matcher.group(1);
	}

	public static void main(String[] args) {
		assert lookup("test = qwerty; test2=prueba;", "test").equals("qwerty");
		assert lookup("test = qwerty; test2=prueba;", "test2").equals("prueba");
		assert lookup("test=21", "test", Integer::parseInt).equals(21);
	}
}
