package edu.unach.categoriaDTO.domain.categoria;

public record DatosListadoCategoriaNombre(int id, String nombre, int nivel) {
    public DatosListadoCategoriaNombre(Categoria categoria){
        this(categoria.getId(), categoria.getNombre(), categoria.getNivel());
    }

}
