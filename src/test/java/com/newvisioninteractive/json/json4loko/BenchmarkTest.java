package com.newvisioninteractive.json.json4loko;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import com.newvisioninteractive.json.json4loko.dto.MockUser;
import com.newvisioninteractive.json.json4loko.mappers.GsonAdapter;
import com.newvisioninteractive.json.json4loko.mappers.JacksonAdapter;
import com.newvisioninteractive.json.json4loko.util.ArrayUtils;
import com.newvisioninteractive.json.json4loko.util.StatsUtils;

public class BenchmarkTest {

    public static final int MOCK_OBJECTS_COUNT = 1000;
    public static final int TEST_COUNT = 1000;

    Json4Loko gson;
    Json4Loko jackson;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception {
	gson = new Json4Loko();
	gson.setMapper( new GsonAdapter() );

	jackson = new Json4Loko();
	jackson.setMapper( new JacksonAdapter() );
    }


    @Test
    public void testArrayToJson() {

	MockUser[] users = createMockUserArray( MOCK_OBJECTS_COUNT );

	StopWatch timer = new StopWatch();
	timer.start();
	String json = gson.toJson( users );
	timer.stop();

	assertNotNull( json );
	System.out.println( "gson - array to json time: " + timer.getTime() +
		" ms" );
	timer.reset();

	timer.start();
	String jsonJackson = jackson.toJson( users );
	timer.stop();

	assertNotNull( jsonJackson );
	System.out.println( "jackson - array to json time: " + timer.getTime() +
		" ms" );
    }


    @Test
    public void testArrayToJsonPopulation() {

	double[] gsonPopulation = new double[TEST_COUNT];
	double[] jacksonPopulation = new double[TEST_COUNT];

	MockUser[] users = createMockUserArray( MOCK_OBJECTS_COUNT );

	StopWatch timer = new StopWatch();
	for( int i = 0; i < TEST_COUNT; i++ ) {
	    timer.start();
	    String json = gson.toJson( users );
	    timer.stop();

	    gsonPopulation[i] = timer.getTime();
	    timer.reset();
	}

	timer.reset();
	for( int j = 0; j < TEST_COUNT; j++ ) {
	    timer.start();
	    String json = jackson.toJson( users );
	    timer.stop();

	    jacksonPopulation[j] = timer.getTime();
	    timer.reset();
	}

	double gsonStdDev = StatsUtils.standardDeviation( gsonPopulation );
	double gsonMean = ArrayUtils.sum( gsonPopulation ) /
		gsonPopulation.length;

	double jacksonStdDev = StatsUtils.standardDeviation( jacksonPopulation );
	double jacksonMean = ArrayUtils.sum( jacksonPopulation ) /
		jacksonPopulation.length;

	System.out.println( "The mean of Array to Json Gson is: " + gsonMean +
		" with a standard deviation of: " + gsonStdDev );
	System.out.println( "The mean of Array to Json Jackson is: " +
		jacksonMean + " with a standard deviation of: " + jacksonStdDev );
    }


    @Test
    public void testListToJson() {

	List<MockUser> users = createMockUserList( MOCK_OBJECTS_COUNT );
	StopWatch timer = new StopWatch();
	timer.start();
	String jsonGson = gson.toJson( users );
	timer.stop();

	assertNotNull( jsonGson );
	System.out.println( "gson - list to json time: " + timer.getTime() +
		" ms" );
	timer.reset();

	timer.start();
	String jsonJackson = jackson.toJson( users );
	timer.stop();

	assertNotNull( jsonJackson );
	System.out.println( "jackson - list to json time: " + timer.getTime() +
		" ms" );
    }


    @Test
    public void testListToJsonPopulation() {
	double[] gsonPopulation = new double[TEST_COUNT];
	double[] jacksonPopulation = new double[TEST_COUNT];

	MockUser[] users = createMockUserArray( MOCK_OBJECTS_COUNT );

	StopWatch timer = new StopWatch();
	for( int i = 0; i < TEST_COUNT; i++ ) {
	    timer.start();
	    String jsonGson = gson.toJson( users );
	    timer.stop();

	    gsonPopulation[i] = timer.getTime();
	    timer.reset();
	}

	timer.reset();
	for( int j = 0; j < TEST_COUNT; j++ ) {
	    timer.start();
	    String jsonJackson = jackson.toJson( users );
	    timer.stop();

	    jacksonPopulation[j] = timer.getTime();
	    timer.reset();
	}

	double gsonStdDev = StatsUtils.standardDeviation( gsonPopulation );
	double gsonMean = ArrayUtils.sum( gsonPopulation ) /
		gsonPopulation.length;

	double jacksonStdDev = StatsUtils.standardDeviation( jacksonPopulation );
	double jacksonMean = ArrayUtils.sum( jacksonPopulation ) /
		jacksonPopulation.length;

	System.out.println( "The mean of List to Json Gson is: " + gsonMean +
		" with a standard deviation of: " + gsonStdDev );
	System.out.println( "The mean of List to Json Jackson is: " +
		jacksonMean + " with a standard deviation of: " + jacksonStdDev );
    }


    @Test
    public void testJsonToList() {

	String json = null;
	try {
	    json = createJsonOfAThousand();
	} catch( IOException e ) {
	    fail( "fail" );
	}

	StopWatch timer = new StopWatch();
	timer.start();
	Type collectionType = new TypeToken<Collection<MockUser>>() {
	}.getType();
	List<MockUser> users = gson.fromJson( json, collectionType );
	timer.stop();

	assertNotNull( users );
	System.out.println( "gson - json to List time: " + timer.getTime() );
	timer.reset();

	timer.start();
	List<MockUser> usersJackson = jackson.fromJson( json,
							new TypeReference<List<MockUser>>() {
							} );
	timer.stop();

	assertNotNull( usersJackson );
	System.out.println( "jackson - json to List time: " + timer.getTime() );
    }


    @Test
    public void testJsonToListPopulation() throws IOException {

	double[] gsonPopulation = new double[TEST_COUNT];
	double[] jacksonPopulation = new double[TEST_COUNT];
	String json = createJsonOfAThousand();

	StopWatch timer = new StopWatch();
	for( int i = 0; i < TEST_COUNT; i++ ) {
	    timer.start();
	    List<MockUser> users = gson.fromJson( json,
						  new TypeToken<Collection<MockUser>>() {}.getType() );
	    timer.stop();

	    gsonPopulation[i] = timer.getTime();
	    timer.reset();
	}

	timer.reset();
	for( int j = 0; j < TEST_COUNT; j++ ) {
	    timer.start();
	    List<MockUser> usersJackson = jackson.fromJson( json,
								new TypeReference<List<MockUser>>() {
								} );
	    timer.stop();
	    
	    jacksonPopulation[j] = timer.getTime();
	    timer.reset();
	}
	
	double gsonStdDev = StatsUtils.standardDeviation( gsonPopulation );
	double gsonMean = ArrayUtils.sum( gsonPopulation ) /
		gsonPopulation.length;

	double jacksonStdDev = StatsUtils.standardDeviation( jacksonPopulation );
	double jacksonMean = ArrayUtils.sum( jacksonPopulation ) /
		jacksonPopulation.length;

	System.out.println( "The mean of Json to List Gson is: " + gsonMean +
		" with a standard deviation of: " + gsonStdDev );
	System.out.println( "The mean of Json to List Jackson is: " +
		jacksonMean + " with a standard deviation of: " + jacksonStdDev );
    }


    public MockUser[] createMockUserArray( int count ) {
	MockUser[] users = new MockUser[count];
	for( int i = 0; i < count; i++ ) {
	    MockUser tmp = new MockUser();
	    tmp.setFirstName( "firstName" + i );
	    tmp.setLastName( "lastName" + i );
	    tmp.setPostalCode( "postalCode" + i );
	    tmp.setEmail( "email" + i );
	    tmp.setCountryCode( "countryCode" + i );
	    users[i] = tmp;
	}
	return users;
    }


    public List<MockUser> createMockUserList( int count ) {
	List<MockUser> users = new ArrayList<MockUser>();
	for( int i = 0; i < count; i++ ) {
	    MockUser tmp = new MockUser();
	    tmp.setFirstName( "firstName" + i );
	    tmp.setLastName( "lastName" + i );
	    tmp.setPostalCode( "postalCode" + i );
	    tmp.setEmail( "email" + i );
	    tmp.setCountryCode( "countryCode" + i );
	    users.add( tmp );
	}
	return users;
    }


    public String createJsonOfAThousand() throws IOException {
	File file = new File( "target/test-classes/onethousand.json" );
	FileInputStream stream = new FileInputStream( file );
	try {
	    FileChannel fc = stream.getChannel();
	    MappedByteBuffer bb = fc.map( FileChannel.MapMode.READ_ONLY,
					  0,
					  fc.size() );
	    /* Instead of using default, pass in a decoder. */
	    return Charset.defaultCharset().decode( bb ).toString();
	} finally {
	    stream.close();
	}
    }
}
