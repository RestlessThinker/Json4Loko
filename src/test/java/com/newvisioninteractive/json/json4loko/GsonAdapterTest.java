package com.newvisioninteractive.json.json4loko;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;
import com.newvisioninteractive.json.json4loko.dto.MockUser;
import com.newvisioninteractive.json.json4loko.mappers.GsonAdapter;

public class GsonAdapterTest {

    Json4Loko jsonMapper;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception {
	jsonMapper = new Json4Loko();
	jsonMapper.setMapper( new GsonAdapter() );
    }


    @Test
    public void testToJson() {
	MockUser user = new MockUser();
	user.setFirstName( "Ricky" );
	user.setLastName( "Bobby" );
	user.setPostalCode( "90210" );
	user.setCountryCode( "us" );

	String json = jsonMapper.toJson( user );
	String expectedJson = "{\"firstName\":\"Ricky\",\"lastName\":\"Bobby\",\"postalCode\":\"90210\",\"countryCode\":\"us\"}";
	assertNotNull( json );
	assertTrue( json.length() > 2 );
	assertEquals( expectedJson, json );

	System.out.println( json );
    }


    @Test
    public void testFromJson() {
	String json = "{\"firstName\":\"Ricky\",\"lastName\":\"Bobby\",\"postalCode\":\"90210\",\"countryCode\":\"us\"}";
	MockUser user = jsonMapper.fromJson( json, MockUser.class );
	assertNotNull( user );
	assertTrue( user.getFirstName().equalsIgnoreCase( "Ricky" ) );
	assertTrue( user.getLastName().equalsIgnoreCase( "Bobby" ) );
    }


    @Test
    public void testArrayFromJson() {
	String json = "[{\"firstName\":\"Ricky\",\"lastName\":\"Bobby\",\"postalCode\":\"90210\",\"countryCode\":\"us\"}, {\"firstName\":\"Cal\",\"lastName\":\"Naughton\",\"postalCode\":\"90210\",\"countryCode\":\"us\"}]";
	MockUser[] users = jsonMapper.fromJson( json, MockUser[].class );
	assertNotNull( users );
	assertTrue( users.length == 2 );
	
	MockUser user = users[1];
	assertTrue( user.getFirstName().equalsIgnoreCase( "Cal" ) );
	assertTrue( user.getLastName().equalsIgnoreCase( "Naughton" ) );
    }
    
    
    @Test
    public void testListFromJson() {
	String json = "[{\"firstName\":\"Ricky\",\"lastName\":\"Bobby\",\"postalCode\":\"90210\",\"countryCode\":\"us\"}, {\"firstName\":\"Cal\",\"lastName\":\"Naughton\",\"postalCode\":\"90210\",\"countryCode\":\"us\"}]";
	Type collectionType = new TypeToken<Collection<MockUser>>() {}.getType();
	List<MockUser> users = jsonMapper.fromJson( json, collectionType );
	
	assertNotNull( users );
	assertTrue( users.size() == 2 );
	
	MockUser user = users.get( 1 );
	assertTrue( user.getFirstName().equalsIgnoreCase( "Cal" ) );
	assertTrue( user.getLastName().equalsIgnoreCase( "Naughton" ) );
    }

}
