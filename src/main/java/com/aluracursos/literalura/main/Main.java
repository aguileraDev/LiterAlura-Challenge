package com.aluracursos.literalura.main;

import com.aluracursos.literalura.models.ApiResponse;
import com.aluracursos.literalura.models.Book;
import com.aluracursos.literalura.services.Api;
import com.aluracursos.literalura.services.BookService;
import com.aluracursos.literalura.services.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import java.util.Scanner;

/**
 * @author Manuel Aguilera
 */
public class Main {

    @Autowired
    private BookService bookService;

    private final Scanner input = new Scanner(System.in);
    private final Api api = new Api();
    private final Converter converter = new Converter();
    private final String BASE_URL = "https://gutendex.com/books/";

    public void bootstrap(){
        menu();
    }

    private void menu(){
        int option;

        String menu = """
                1 - Buscar libro por titulo
                2 - Buscar
                3 - Buscar
                                
                0 - Salir
                """;

        do{
            System.out.println("                LiterAlura  \n");
            System.out.println("Selecciona una opcion");
            System.out.println(menu);
            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    findByTitle();
                    break;
                case 0:
                    System.out.println("Cerrando aplicacion...");
                    break;
                default:
                    System.out.println("opcion invalida");

            }
        }while (option !=0);

    }

    private void findByTitle(){
        System.out.println("Ingresa nombre del libro");
        String title = input.nextLine().replace(" ","%20").toLowerCase().trim();
        String param = String.format("?search=%s",title);

        String url = BASE_URL + param;

        String json = api.getData(url);

        ApiResponse listOfBooks = converter.getDataFromJsonToClass(json, ApiResponse.class);

        System.out.println(listOfBooks);


    }
}
