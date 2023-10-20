package edu.unach.categoriaDTO.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroCategoria(

        @NotBlank
        String nombre,

        int nivel) {
}
