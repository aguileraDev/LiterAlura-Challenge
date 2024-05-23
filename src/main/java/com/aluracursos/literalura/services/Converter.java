package com.aluracursos.literalura.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Manuel Aguilera
 */
public class Converter implements IConverter {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getDataFromJsonToClass(String json, Class <T> dataClass){

        T mapper;

        try{

         mapper = objectMapper.readValue(json, dataClass);

        }catch(JsonProcessingException e){
            System.out.println("Ocurrio un error al mapear los datos");
            throw new RuntimeException(e);
        }

        return mapper;
    }
}
