package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * @author Manuel Aguilera
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book save(Book book);

}
