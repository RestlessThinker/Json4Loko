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

public class BenchmarkTest {

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


    @Test
    public void testToArray() {

	MockUser[] users = createMockUserArray( 1000 );

	StopWatch timer = new StopWatch();
	timer.start();
	String json = gson.toJson( users );
	timer.stop();

	System.out.println( json );
	System.out.println( "gson to array time: " + timer.getTime() );
	timer.reset();

	timer.start();
	String jsonJackson = jackson.toJson( users );
	timer.stop();

	System.out.println( "jackson to array time: " + timer.getTime() );
    }


    @Test
    public void testToList() {

	String json = null;
	try {
	    json = createJsonOfAThousand();
	} catch( IOException e ) {
	    fail("fail");
	}
	
	StopWatch timer = new StopWatch();
	timer.start();
	Type collectionType = new TypeToken<Collection<MockUser>>() {}.getType();
	List<MockUser> users = gson.fromJson( json, collectionType );
	timer.stop();
	
	assertNotNull( users );
	System.out.println( "gson to List time: " + timer.getTime() );
	timer.reset();
	
	timer.start();
	List<MockUser> usersJackson = jackson.fromJson( json, new TypeReference<List<MockUser>>() {});
	timer.stop();
	
	assertNotNull( usersJackson );
	System.out.println( "jackson to List time: " + timer.getTime() );
    }

}
