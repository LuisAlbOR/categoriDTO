package edu.unach.categoriaDTO.controller;

import edu.unach.categoriaDTO.categoria.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public Page<DatosListadoCategoria> listarCategorias(@PageableDefault(page = 0, size = 5, sort = {"nombre"}) Pageable paginacion){
        //return categoriaRepository.findAll(paginacion).map(DatosListadoCategoria::new);
        return categoriaRepository.findByActivoTrue(paginacion).map(DatosListadoCategoria::new);
    }

    @PostMapping
    public void  registrarCategoria(@RequestBody @Valid DatosRegistroCategoria datosRegistroMedico){
        categoriaRepository.save(new Categoria(datosRegistroMedico));
    }

    @PutMapping
    @Transactional
    public void actualizarCategoria(@RequestBody @Valid DatosActualizarCategoria datosActualizarCategoria){
        Categoria categoria = categoriaRepository.getReferenceById(datosActualizarCategoria.id());
        categoria.actualizarDatos(datosActualizarCategoria);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarCategoriaLogicamente(@PathVariable int id){
        Categoria categoria = categoriaRepository.getReferenceById(id);
        categoria.desactivarCategoria();
    }
}
