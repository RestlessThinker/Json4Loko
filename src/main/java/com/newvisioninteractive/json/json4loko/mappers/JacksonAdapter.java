package com.newvisioninteractive.json.json4loko.mappers;

import java.io.IOException;
import java.lang.reflect.Type;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newvisioninteractive.json.json4loko.MethodNotAllowedException;

public class JacksonAdapter implements Json4LokoMapperAdapter {
    
    ObjectMapper mapper;
    
    public JacksonAdapter() {
	mapper = new ObjectMapper();
	
    }
    

    public String toJson( Object src ) {
	try {
	    return mapper.writeValueAsString( src );
	} catch( JsonMappingException jme ) {
	    
	} catch( JsonGenerationException jge ) {
	    
	} catch( IOException ioe ) {
	    
	}
	return null;
    }


    public <A> A fromJson( String json, Class<A> classOfA ) {
	try {
	    return mapper.readValue( json, classOfA );
	} catch( JsonMappingException jme ) {
	    
	} catch( JsonParseException jpe ) {
	    
	} catch( IOException ioe ) {
	    
	}
	return null;
    }
    
    
    
    public <A> A fromJson( String json, TypeReference<A> typeRef ) {
	try {
	    return mapper.readValue( json, typeRef );
	} catch( JsonMappingException jme ) {
	    
	} catch( JsonParseException jpe ) {
	    
	} catch( IOException ioe ) {
	    
	}
	return null;
    }
    
    
    
    public <A> A fromJson( String json, Type typeOfA ) {
	throw new MethodNotAllowedException( "JacksonAdapter does not support this method. Try GsonAdapter" );
    }

}
