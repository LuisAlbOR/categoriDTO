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

    //Connsulta de todas las categorias
    @GetMapping("/categorias")
    public Page<DatosListadoCategoria> listarCategorias(@PageableDefault(page = 0, size = 5, sort = {"nombre"}) Pageable paginacion){
        //return categoriaRepository.findAll(paginacion).map(DatosListadoCategoria::new);
        //Se obtienen todas las categorias activas, haciendo la consulta con el estándar de JPA
        return categoriaRepository.findByActivoTrue(paginacion).map(DatosListadoCategoria::new);
    }

    //Consulta de todas las categorias de cierto nivel
    @GetMapping("/nivel/{nivel}")
    public Page<DatosListadoCategoriaNivel> listarCategoriaNive(@PageableDefault(page = 0, size = 5, sort = {"nombre"}) Pageable paginacion, @PathVariable int nivel){
        return categoriaRepository.findByNivel(paginacion, nivel).map(DatosListadoCategoriaNivel::new);
    }

    //Consulta de la categoria por el nombre de la misma
    @GetMapping("/nombre/{nombre}")
    public Page<DatosListadoCategoriaNombre> listarCategoriaNombre(@PageableDefault(page = 0, size = 5, sort = {"nombre"}) Pageable paginacion, @PathVariable String nombre){
        return categoriaRepository.findByNombre(paginacion, nombre).map(DatosListadoCategoriaNombre::new);
    }

    //Consulta de la categoria por el id de la misma
    @GetMapping("/{id}")
    public Page<DatosListadoCategoria> listarCategoriaId(@PageableDefault(page = 0, size = 5, sort = {"nombre"}) Pageable paginacion, @PathVariable int id){
        return categoriaRepository.findById(paginacion, id).map(DatosListadoCategoria::new);
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

    //Se hace un borrado lógico del registro en la base de datos
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarCategoriaLogicamente(@PathVariable int id){
        Categoria categoria = categoriaRepository.getReferenceById(id);
        categoria.desactivarCategoria();
    }
}
