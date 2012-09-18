package com.newvisioninteractive.json.json4loko.util;

public class ArrayUtils {

    private ArrayUtils() {
	throw new AssertionError();
    }


    public static double sum( double[] a ) {
	double sum = 0;
	for( int i = 0; i < a.length; i++ ) {
	    sum += a[i];
	}
	return sum;
    }
}
