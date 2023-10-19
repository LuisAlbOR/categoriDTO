package edu.unach.categoriaDTO.controller;

import edu.unach.categoriaDTO.categoria.CategoriaRepository;
import edu.unach.categoriaDTO.categoria.DatosListadoCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public Page<DatosListadoCategoria> listarMedicos(@PageableDefault(page = 0, size = 5, sort = {"nombre"}) Pageable paginacion){
        return categoriaRepository.findAll(paginacion).map(DatosListadoCategoria::new);
    }

}
