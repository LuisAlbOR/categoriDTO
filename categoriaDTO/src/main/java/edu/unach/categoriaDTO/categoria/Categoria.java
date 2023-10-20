package edu.unach.categoriaDTO.categoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private int nivel;

    private boolean activo;

    //Constructor para crear un nuevo registro en la BD con el método POST
    public Categoria(DatosRegistroCategoria datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.nivel = datosRegistroMedico.nivel();
    }

    public void actualizarDatos(DatosActualizarCategoria datosActualizarCategoria) {
        if(datosActualizarCategoria.nombre() != null){
            this.nombre = datosActualizarCategoria.nombre();
        }
        if(datosActualizarCategoria.nivel() != 0 && datosActualizarCategoria.nivel() > 0){
            this.nivel = datosActualizarCategoria.nivel();
        }
    }

    public void desactivarCategoria() {
        this.activo = false;
    }
}
