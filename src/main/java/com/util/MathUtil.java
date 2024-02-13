package com.util;

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
	
	private static double calculateMean(double[] array) {
		double output = 0;
		
		for(double i : array) {
			output += i;
		}
		
		return output / array.length;
	}
	
	public static double[] calculateMean(double[][] array) {
		double[] output = new double[array.length];
		
		for(int i = 0; i < output.length; i++) {
			output[i] = calculateMean(array[i]);
		}
		
		return output;
	}
	
	private static double calculateCovarience(double[] first, double[] second) {
		double output = 0;
		
		double firstMean = calculateMean(first);
		double secondMean = calculateMean(second);
		
		for(int i = 0; i < Math.min(first.length, second.length); i++) {
			output += (first[i] - firstMean) * (second[i] - secondMean);
		}
		
		return output / (Math.min(first.length, second.length) - 1);
	}
	
	public static double[][] calculateCovarience(double[][] array) {
		double[][] output = new double[array.length][array.length];
		
		for(int i = 0; i < output.length; i++) {
			for(int j = 0; j < output.length; j++) {
				output[i][j] = calculateCovarience(array[i], array[j]);
			}
		}
		
		for(int i = 0; i < output.length; i++) {
			output[i][i] = 0;
		}
		
		return output;
	}
	
	private static double standardDeviation(double[] array) {
		double output = 0;
		
		double mean = calculateMean(array);
		
		for(double i : array) {
			output += Math.pow(i - mean, 2);
		}
		
		output /= array.length;
		
		return Math.sqrt(output);
	}
	
	public static double[] calculateStandardDeviation(double[][] array) {
		double[] output = new double[array.length];
		
		for(int i = 0; i < array.length; i++) {
			output[i] = standardDeviation(array[i]);
		}
		
		return output;
	}
}
