package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Manuel Aguilera
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    Book save(Book book);
}
