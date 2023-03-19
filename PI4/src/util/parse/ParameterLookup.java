package util.parse;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilidad para obtener valores de una cadena con parámetros.
 */
public class ParameterLookup {

	private ParameterLookup() {
		// utility class, not instantiable
	}

	/**
	 * Obtiene el parámetro especificado de una cadena pasada como argumento, mapeado según la
	 * función especificada.
	 * <p>
	 * Por ejemplo, para obtener el parámetro key de la cadena "key=true; key2=value2":
	 * <pre>lookup("key=true; key2=value2", "key", Boolean::parseBoolean)</pre>
	 * 
	 * @param string cadena a obtener el parámetro
	 * @param parameter parámetro a obtener
	 * @param mappingFunction función para devolver el resultado en otro tipo
	 * @return el parámetro en la cadena, o {@code null} si no se encuentra
	 */
	public static <T> T lookup(String string, String parameter, Function<String, T> mappingFunction) {
		return mappingFunction.apply(lookup(string, parameter));
	}

	/**
	 * Obtiene el parámetro especificado de una cadena pasada como argumento.
	 * <p>
	 * Por ejemplo, para obtener el parámetro key de la cadena "key=value; key2=value2":
	 * <pre>lookup("key=value; key2=value2", "key")</pre>
	 * 
	 * @param string cadena a obtener el parámetro
	 * @param parameter parámetro a obtener
	 * @return el parámetro en la cadena, o {@code null} si no se encuentra
	 */
	public static String lookup(String string, String parameter) {
		// después de un igual, cualquier secuencia de caracteres hasta que encuentre un punto y coma
		// o un fin de String ($). \s* significa ninguno o algún espacio.
		Pattern pattern = Pattern.compile(parameter + "\\s*=\\s*(.+?)(;|$)");
		Matcher matcher = pattern.matcher(string);

		// si no encuentra 
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
