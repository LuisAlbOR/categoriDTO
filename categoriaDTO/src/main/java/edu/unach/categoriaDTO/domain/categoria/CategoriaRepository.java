package edu.unach.categoriaDTO.domain.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    Page<Categoria> findByActivoTrue(Pageable paginacion);

    Page<Categoria> findByNivel(Pageable paginacion, int nivel);

    Categoria getReferenceByNombre(String nombre);
}
