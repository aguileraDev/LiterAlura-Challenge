package com.aluracursos.literalura.main;

import com.aluracursos.literalura.models.*;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.services.Api;
import com.aluracursos.literalura.services.Converter;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Manuel Aguilera
 */

public class Main {


    private final Scanner input = new Scanner(System.in);
    private final Api api = new Api();
    private final Converter converter = new Converter();
    private final String BASE_URL = "https://gutendex.com/books/";

    private BookRepository bookRepository;


    public Main(BookRepository repository){
        this.bookRepository = repository;

    }

    public void bootstrap(){
        menu();
    }

    private void menu(){
        int option = 1;

        String menu = """
                1 - Buscar libro por titulo
                2 - Ver libros registrados
                3 - Ver autores registrados
                4 - Busqueda de autor por año
                5 - Buscar libros por idioma
                                
                0 - Salir
                """;

        do{
            System.out.println("                LiterAlura  \n");
            System.out.println("Selecciona una opcion");
            System.out.println(menu);
            try{
                option = input.nextInt();
            }catch ( InputMismatchException | NumberFormatException e){
                System.out.println("Debes ingresar un numero");
                option = 7;
            }

            input.nextLine();

            switch (option) {
                case 1:
                    findByTitle();
                    break;

                case 2:
                    getAll();
                    break;

                case 3:
                    getAllAuthors();
                    break;
                case 4:
                    int year = 0;
                    System.out.println("Ingresa un año");
                    try {
                        year = input.nextInt();
                    }catch (InputMismatchException | NumberFormatException e){
                        System.out.println("Solo se permiten numeros");
                        menu();
                    }

                    input.nextLine();

                    authorsFilterByYear(year);
                    break;
                case 5:
                    int language = languageOptions();
                    booksByLanguages(language);
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

        Optional<BookApi> firstBook = listOfBooks.results().stream()
                .filter(book -> book.title().toLowerCase().contains(title))
                .findFirst();

        if(firstBook.isPresent()){
            var book = new Book(firstBook.get());

            try{
                bookRepository.save(book);
            }catch (DataIntegrityViolationException e ){
                System.out.println("El libro ya existe en la base de datos");
            }

            System.out.println(book);
        }
        else if(firstBook.isEmpty()){
            System.out.println("""
                    -----------------------------------------------------
                    Libro no encontrado
                    -----------------------------------------------------
                    
                    """);

        }


    }

    private void getAll(){
        bookRepository.findAll().stream()
                .sorted(Comparator.comparing(Book::getTitle).reversed())
                .forEach(System.out::println);
    }

    private void getAllAuthors(){
        bookRepository.findAll().stream()
                .map(Book::getAuthor)
                .sorted(Comparator.comparing(Author::getName).reversed())
                .forEach(System.out::println);
    }

    private void authorsFilterByYear(int year){
        bookRepository.findAll().stream()
                .map(Book::getAuthor)
                .filter(author -> author.getBirth_year() >= year )
                .forEach(System.out::println);
    }

    private void booksByLanguages(int languageOption){
        List<Book> books = new ArrayList<>();

        try{
            var lang = Languages.fromOption(languageOption);

            books = bookRepository.findAll().stream()
                    .filter(book -> book.getLanguages().stream()
                            .anyMatch(l-> l.equalsIgnoreCase(lang.name())))
                    .collect(Collectors.toList());


        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        if(books.isEmpty()){
            System.out.println("No hay libros registrados con el idioma seleccionado");
        }else {
            books.forEach(System.out::println);
        }

    }

    private Integer languageOptions(){
        String options = """
                Selecciona una opcion
                1 - Ingles
                2 - Español
                3 - Portugues
                4 - Frances
                5 - Aleman
                """;

        int languageSelected = 0;

        System.out.println(options);
        try{
            languageSelected = input.nextInt();
        }catch (InputMismatchException | NumberFormatException e){
            System.out.println("Ingresa el numero de tu idioma preferido");

        }

        input.nextLine();

        return languageSelected;
    }
}
