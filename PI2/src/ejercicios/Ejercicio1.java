package ejercicios;

import java.math.BigInteger;

public class Ejercicio1 {

	public static BigInteger recursivaBigInteger(Integer n) {
		if (n < 2)
			return BigInteger.ONE;

		return recursivaBigInteger(n - 1).multiply(BigInteger.valueOf(n));
	}

	public static BigInteger iterativaBigInteger(Integer n) {
		BigInteger ac = n > 1 ? BigInteger.valueOf(n) : BigInteger.ONE;

		while (n > 1) {
			ac = ac.multiply(BigInteger.valueOf(--n));
		}

		return ac;
	}

	public static Double recursivaDouble(Integer n) {
		if (n < 2)
			return 1D;

		return n * recursivaDouble(n - 1);
	}

	public static Double iterativaDouble(Integer n) {
		Double ac = n > 1 ? n : 1D;

		while (n > 1) {
			ac *= --n;
		}

		return ac;
	}
}
