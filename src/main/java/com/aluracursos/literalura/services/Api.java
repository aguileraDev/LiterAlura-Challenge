package com.aluracursos.literalura.services;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Manuel Aguilera
 */
public class Api {

    public String getData(String url){

        String json = "";

        HttpRequest request = HttpRequest
                                .newBuilder()
                                .uri(URI.create(url))
                                .build();

        HttpClient client = HttpClient
                            .newBuilder()
                            .build();

        try{
            CompletableFuture<HttpResponse<String>> response = client
                                                    .sendAsync(request, BodyHandlers.ofString());
            json = response.get().body();

        }catch (InterruptedException | ExecutionException e){
            System.out.println("Ocurrio un error en la solicitud HTTP - " + e.getMessage());

        }

        return json;
    }
}
