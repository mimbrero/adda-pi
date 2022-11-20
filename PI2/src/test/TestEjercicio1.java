package test;

import java.util.List;

import ejercicios.Ejercicio1;
import util.curvefitting.TipoAjuste;
import util.test.BenchmarkTest;

public class TestEjercicio1 extends BenchmarkTest {

	private static final String RECURSIVA_BIG_INTEGER = "recursiva-biginteger";
	private static final String ITERATIVA_BIG_INTEGER = "iterativa-biginteger";
	private static final String RECURSIVA_DOUBLE = "recursiva-double";
	private static final String ITERATIVA_DOUBLE = "iterativa-double";

	public TestEjercicio1() {
		super("Factorial", "ficheros/tiempos-factorial-%s.csv");
	}

	@Override
	public void test() {
		this.generateBenchmarks();
		this.generateGraphs();
	}

	private void generateBenchmarks() {
		this.generateBenchmark(size -> Ejercicio1.recursivaBigInteger(size), RECURSIVA_BIG_INTEGER, 2, 5000, 333, 1000, 50);
		this.generateBenchmark(size -> Ejercicio1.iterativaBigInteger(size), ITERATIVA_BIG_INTEGER, 2, 5000, 333, 1000, 50);
		this.generateBenchmark(size -> Ejercicio1.recursivaDouble(size), RECURSIVA_DOUBLE, 2, 5000, 333, 10000, 10000);
		this.generateBenchmark(size -> Ejercicio1.iterativaDouble(size), ITERATIVA_DOUBLE, 2, 5000, 333, 10000, 10000);
	}

	private void generateGraphs() {
		this.generateGraph(RECURSIVA_BIG_INTEGER, TipoAjuste.POLYNOMIALLOG);
		this.generateGraph(ITERATIVA_BIG_INTEGER, TipoAjuste.POLYNOMIALLOG);
		this.generateGraph(RECURSIVA_DOUBLE, TipoAjuste.LINEAL);
		this.generateGraph(ITERATIVA_DOUBLE, TipoAjuste.LINEAL);
		
		this.generateCombinedGraph(List.of(RECURSIVA_BIG_INTEGER, ITERATIVA_BIG_INTEGER, RECURSIVA_DOUBLE, ITERATIVA_DOUBLE));
	}

	public static void main(String[] args) {
		new TestEjercicio1().test();
	}
}
