package edu.unach.categoriaDTO.domain.categoria;

public record DatosListadoCategoriaNivel(int id, String nombre, int nivel) {
    public DatosListadoCategoriaNivel(Categoria categoria){
        this(categoria.getId(), categoria.getNombre(), categoria.getNivel());
    }
}
