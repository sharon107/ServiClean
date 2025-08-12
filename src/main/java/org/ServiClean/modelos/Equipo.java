package org.ServiClean.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "Equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Integer id;

    @NotBlank(message = "El nombre del equipo es requerido")
    @Column(name = "nombre_equipo")
    private String nombreEquipo;

    private String descripcion;
    private String ubicacion;
    private String estado;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro = LocalDate.now();

    public Integer getId()
    { return id; }

    public void setId(Integer id)
    { this.id = id; }

    public String getNombreEquipo()
    { return nombreEquipo; }

    public void setNombreEquipo(String nombreEquipo)
    { this.nombreEquipo = nombreEquipo; }

    public String getDescripcion()
    { return descripcion; }

    public void setDescripcion(String descripcion)
    { this.descripcion = descripcion; }

    public String getUbicacion()
    { return ubicacion; }

    public void setUbicacion(String ubicacion)
    { this.ubicacion = ubicacion; }

    public String getEstado()
    { return estado; }

    public void setEstado(String estado)
    { this.estado = estado; }

    public LocalDate getFechaRegistro()
    { return fechaRegistro; }

    public void setFechaRegistro(LocalDate fechaRegistro)
    { this.fechaRegistro = fechaRegistro; }
}
