package edu.unach.categoriaDTO.domain.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarCategoria(
        @NotNull
        int id,
        @NotNull
        String nombre,
        @NotBlank
        int nivel
) {
}
