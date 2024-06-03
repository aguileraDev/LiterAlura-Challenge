package com.aluracursos.literalura.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;

import java.util.List;
/**
 * @author Manuel Aguilera
 */
@Entity()
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column()
    private Integer birth_year;

    @Column()
    private Integer death_year;

    @OneToMany(
            mappedBy = "author",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Book> book;

    public Author(){}

    public Author(AuthorApi authorApi){
        if(authorApi.name().isEmpty()){
            this.name = "desconocido";
        }else {
            this.name = authorApi.name();
        }
        try{
            this.birth_year = authorApi.birth_year();
            this.death_year = authorApi.death_year();
        }catch (NumberFormatException e){
            this.birth_year = 0;
            this.death_year = 0;
        }
    }

    public Author (String name, int birth_year, int death_year){
        this.name = name;
        this.birth_year = birth_year;
        this.death_year = death_year;
    }
    @Override
    public String toString() {
        return   name  +"\n" +
                "fecha de nacimiento = " + birth_year +"\n" +
                "fecha de fallecimiento = " + death_year ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
