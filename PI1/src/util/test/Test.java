package util.test;

public abstract class Test {

	public abstract void test();

	protected void print() {
		System.out.println();
	}

	protected void print(Object o) {
		System.out.println(o);
	}

	protected void print(String s, Object... args) {
		System.out.printf(s + "\n", args);
	}

	protected void printSeparator(String title) {
		print("\n=================== %s ===================", title);
	}

	protected void printIterativa() {
		printSeparator("Solución iterativa");
	}

	protected void printRecursivaFinal() {
		printSeparator("Solución recursiva final");
	}

	protected void printRecursivaNoFinal() {
		printSeparator("Solución recursiva no final");
	}

	protected void printFuncional() {
		printSeparator("Solución funcional");
	}
}
