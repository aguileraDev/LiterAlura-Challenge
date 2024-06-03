package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Aguilera / @aguileradev
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author save(Author author);
}
