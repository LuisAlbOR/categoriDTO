package edu.unach.categoriaDTO.controller;

import edu.unach.categoriaDTO.domain.categoria.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    //Connsulta de todas las categorias
    @GetMapping("/categorias")
    public ResponseEntity<Page<DatosListadoCategoria>> listarCategorias(@PageableDefault(page = 0, size = 5, sort = {"nombre"}) Pageable paginacion){
        //return categoriaRepository.findAll(paginacion).map(DatosListadoCategoria::new);
        //Se obtienen todas las categorias activas, haciendo la consulta con el estándar de JPA
        return ResponseEntity.ok(categoriaRepository.findByActivoTrue(paginacion).map(DatosListadoCategoria::new));
    }

    //Consulta de todas las categorias de cierto nivel
    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<Page<DatosListadoCategoriaNivel>> listarCategoriaNive(@PageableDefault(page = 0, size = 5, sort = {"nombre"}) Pageable paginacion, @PathVariable int nivel){
        return ResponseEntity.ok(categoriaRepository.findByNivel(paginacion, nivel).map(DatosListadoCategoriaNivel::new));
    }

    //Consulta de la categoria por el nombre de la misma
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<DatosRespuestaCategoria> listarCategoriaNombre( @PathVariable String nombre){
        Categoria categoria = categoriaRepository.getReferenceByNombre(nombre);

        var datosCategoria = new DatosRespuestaCategoria(categoria.getId(), categoria.getNombre(), categoria.getNivel());

        return ResponseEntity.ok(datosCategoria);

    }

    //Consulta de la categoria por el id de la misma
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCategoria> listarCategoriaId(@PathVariable int id){
        Categoria categoria = categoriaRepository.getReferenceById(id);

        var datosCategoria = new DatosRespuestaCategoria(categoria.getId(), categoria.getNombre(), categoria.getNivel());

        return ResponseEntity.ok(datosCategoria);
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaCategoria>  registrarCategoria(@RequestBody @Valid DatosRegistroCategoria datosRegistroMedico,
                                                                       UriComponentsBuilder uriComponentsBuilder){
        Categoria categoria =categoriaRepository.save(new Categoria(datosRegistroMedico));

        DatosRespuestaCategoria datosRespuestaCategoria = new DatosRespuestaCategoria(categoria.getId(), categoria.getNombre(), categoria.getNivel());

        URI url = uriComponentsBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaCategoria);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarCategoria(@RequestBody @Valid DatosActualizarCategoria datosActualizarCategoria){
        Categoria categoria = categoriaRepository.getReferenceById(datosActualizarCategoria.id());

        categoria.actualizarDatos(datosActualizarCategoria);

        return ResponseEntity.ok(new DatosRespuestaCategoria(categoria.getId(), categoria.getNombre(), categoria.getNivel()));
    }

    //Se hace un borrado lógico del registro en la base de datos
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCategoriaLogicamente(@PathVariable int id){
        Categoria categoria = categoriaRepository.getReferenceById(id);
        categoria.desactivarCategoria();

        return ResponseEntity.noContent().build();
    }
}
