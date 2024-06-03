package com.aluracursos.literalura.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;


import java.util.List;
import java.util.Optional;


/**
 * @author Manuel Aguilera
 */

@Entity()
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private Author author;

    @Column(unique = true)
    private String title;

    @Column()
    private List<String> subjects;

    @Column()
    private List<String> bookshelves;

    @Column()
    private List<String> languages;

    @Column()
    private Boolean copyright;


    @Column()
    private Integer download_count;

    public Book(){}

    public Book(BookApi bookApi){
        this.title = bookApi.title();

        Optional<AuthorApi> author = bookApi.authors().stream().findFirst();

        if(author.isPresent()){
            this.author = new Author(author.get());
        }

        if(author.isEmpty()){
            this.author = new Author("desconocido",0,0);
        }

        this.subjects = bookApi.subjects();
        this.bookshelves = bookApi.bookshelves();
        this.languages = bookApi.languages();
        this.copyright = bookApi.copyright();
        this.download_count = bookApi.download_count();

    }
    @Override
    public String toString() {
        return  "__________Libro__________\n" +
                "titulo ='" + title + "\n" +
                author + "\n" +
                "temas =" + subjects + "\n" +
                "idiomas =" + languages + "\n" +
                "descargas =" + download_count + "\n" +
                "-------------------------";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }
}
