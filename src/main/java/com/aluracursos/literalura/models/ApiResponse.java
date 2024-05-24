package com.aluracursos.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Optional;

/**
 * @author Manuel Aguilera
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(
        @JsonAlias("count") Integer count,
        @JsonAlias("next") String next,
        @JsonAlias("previous") Optional previous,
        @JsonAlias("results") List<BookApi> results

) {
}
