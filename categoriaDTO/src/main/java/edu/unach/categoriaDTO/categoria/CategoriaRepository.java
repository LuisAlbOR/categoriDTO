package edu.unach.categoriaDTO.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    Page<Categoria> findByActivoTrue(Pageable paginacion);

    Page<Categoria> findByNivel(Pageable paginacion, int nivel);

    Page<Categoria> findByNombre(Pageable paginacion, String nombre);

    Page<Categoria> findById(Pageable paginacion, int id);
}
