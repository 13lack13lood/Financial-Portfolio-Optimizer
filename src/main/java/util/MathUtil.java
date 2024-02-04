package util;

public class MathUtil {
	public static double[] normalize(double[] array) {
		double[] output = new double[array.length];
		
		double sum = 0;
		
		for(double i : array) {
			sum += i;
		}
		
		for(int i = 0; i < array.length; i++) {
			output[i] = array[i] / sum;
		}
		
		return output;
	}
	
	public static double[] randomArray(int n) {
		double[] array = new double[n];
		
		for(int i = 0; i < n; i++) {
			array[i] = Math.random();
		}
		
		return array;
	}
}
