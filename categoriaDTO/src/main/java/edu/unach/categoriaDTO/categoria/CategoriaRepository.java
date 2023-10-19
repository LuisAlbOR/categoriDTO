package edu.unach.categoriaDTO.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    @Override
    Page<Categoria> findAll(Pageable pageable);
}
