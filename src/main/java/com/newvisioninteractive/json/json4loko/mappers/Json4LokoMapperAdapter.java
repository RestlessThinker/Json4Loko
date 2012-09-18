package com.newvisioninteractive.json.json4loko.mappers;

import java.lang.reflect.Type;

import com.fasterxml.jackson.core.type.TypeReference;


public interface Json4LokoMapperAdapter {

    public String toJson( Object src );
    public <A> A fromJson( String json, Class<A> classOfA );
    public <A> A fromJson( String json, Type typeOfA );
    public <A> A fromJson( String json, TypeReference<A> typeRef );
}
