package com.newvisioninteractive.json.json4loko;

import java.lang.reflect.Type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.newvisioninteractive.json.json4loko.mappers.GsonAdapter;
import com.newvisioninteractive.json.json4loko.mappers.Json4LokoMapperAdapter;

public class Json4Loko {

    private Json4LokoMapperAdapter mapper;
    
    public Json4Loko() {
	this.mapper = new GsonAdapter();
    }


    public void setMapper( Json4LokoMapperAdapter mapper ) {
	this.mapper = mapper;
    }


    public <A> A fromJson( String json, Class<A> classOfA ) {
	return mapper.fromJson( json, classOfA );
    }


    public <A> A fromJson( String json, Type typeOfA ) {
	return mapper.fromJson( json, typeOfA );
    }


    public <A> A fromJson( String json, TypeReference<A> typeRef ) {
	return mapper.fromJson( json, typeRef );
    }


    public String toJson( Object obj ) {
	return mapper.toJson( obj );
    }

}
