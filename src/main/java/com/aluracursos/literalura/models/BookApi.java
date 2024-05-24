package com.aluracursos.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * @author Manuel Aguilera
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookApi(
        @JsonAlias("id")Integer id,
        @JsonAlias("title")String title,
        @JsonAlias("authors") List<AuthorApi> authors,
        @JsonAlias("subjects")List<String> subjects,
        @JsonAlias("bookshelves")List<String> bookshelves,
        @JsonAlias("languages")List<String> languages,
        @JsonAlias("copyright")Boolean copyright,
        @JsonAlias("download_count")Integer download_count
) {
}
