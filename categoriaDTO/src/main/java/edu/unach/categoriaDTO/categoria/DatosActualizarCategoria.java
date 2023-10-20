package edu.unach.categoriaDTO.categoria;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCategoria(
        @NotNull
        int id,
        @NotNull
        String nombre,
        int nivel
) {
}
