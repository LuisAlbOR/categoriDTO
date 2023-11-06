package edu.unach.categoriaDTO.domain.categoria;

public record DatosListadoCategoria(int id, String nombre, int nivel) {

    public DatosListadoCategoria(Categoria categoria){
        this(categoria.getId(), categoria.getNombre(), categoria.getNivel());
    }
}
