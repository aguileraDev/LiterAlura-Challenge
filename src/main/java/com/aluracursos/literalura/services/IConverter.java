package com.aluracursos.literalura.services;

/**
 * @author Manuel Aguilera
 */
public interface IConverter {

    <T> T getDataFromJsonToClass(String json, Class <T> dataClass);
}
