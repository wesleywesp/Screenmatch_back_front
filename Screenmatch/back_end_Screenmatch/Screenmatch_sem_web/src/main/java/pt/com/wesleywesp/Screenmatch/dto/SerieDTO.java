package pt.com.wesleywesp.Screenmatch.dto;


import java.time.LocalDate;

    public record SerieDTO(Long id,
                           String titulo,
                           Integer totalSeasons,
                           Double avaliacao,
                           LocalDate anoDeLancamento,
                           String atores,
                           String sinopse,
                           String premios,
                           String poster) {
    }
