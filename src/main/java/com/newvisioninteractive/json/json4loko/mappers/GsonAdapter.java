package com.newvisioninteractive.json.json4loko.mappers;

import java.lang.reflect.Type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.newvisioninteractive.json.json4loko.MethodNotAllowedException;

public class GsonAdapter implements Json4LokoMapperAdapter {
    
    Gson gson;
    
    
    public GsonAdapter() {
	gson = new Gson();
    }
    
    
    public <A> A fromJson( String json, Class<A> classOfA ) {
	return gson.fromJson( json, (Type) classOfA );
    }
    
    
    public <A> A fromJson( String json, Type typeOfA ) {
	return gson.fromJson( json, typeOfA );
    }

    
    public String toJson( Object src ) {
	return gson.toJson( src );
    }
    
    
    public <A> A fromJson( String json, TypeReference<A> typeRef ) {
	throw new MethodNotAllowedException( "GsonAdapter does not support this method. Try JacksonAdapter" );
    }
    
}
