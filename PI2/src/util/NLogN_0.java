package util;

import java.util.List;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.SimpleCurveFitter;

/**
 * @author migueltoro
 * 
 * Una curva de la forma a*n*(ln n)
 *
 */
public class NLogN_0 implements ParametricUnivariateFunction {
	
	private static NLogN_0 pl = null;
	
	public static NLogN_0 of() {
		if(pl == null) pl = new NLogN_0();
		return pl;
	}
	
	public NLogN_0() {
		super();
	}

	@Override
	public double[] gradient(double n, double... p) {
		Double a0 = n*Math.log(n);
		double[] r = {a0};
		return r;
	}

	@Override
	public double value(double n, double... p) {
		Double a = p[0];
		return a*n*Math.log(n);
	}
	
	public double[] fit(List<WeightedObservedPoint> points, double[] start) {
		final SimpleCurveFitter fitter = SimpleCurveFitter.create(NLogN_0.of(),start);
		return fitter.fit(points);
	}
	
	public void print(double n, double... p) {
		String r = String.format("Values: n = %.2f,a = %.2f",n, p[0]);
		System.out.println(r);
		System.out.println("Value = "+this.value(n, p));
		double[] g = this.gradient(n, p);
		System.out.println("Gradiente = "+String.format("%.2f",g[0]));
	}

}
