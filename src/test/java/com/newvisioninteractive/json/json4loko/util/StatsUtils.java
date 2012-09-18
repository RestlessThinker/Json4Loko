package com.newvisioninteractive.json.json4loko.util;

public class StatsUtils {

    private StatsUtils() {
	throw new AssertionError();
    }


    public static double variance( double[] population ) {
	long n = 0;
	double mean = ArrayUtils.sum( population ) / population.length;
	double s = 0.0;

	for( double x : population ) {
	    n++;
	    double delta = x - mean;
	    mean += delta / n;
	    s += delta * ( x - mean );
	}

	return ( s / n );
    }


    public static double standardDeviation( double[] population ) {
	return Math.sqrt( variance( population ) );
    }
}
