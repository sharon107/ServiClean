package org.ServiClean.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    private String descripcion;

    private Boolean estado; // true = activo, false = inactivo

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_edicion")
    private LocalDateTime fechaEdicion;

    // ----------------- Getters y Setters -----------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaEdicion() {
        return fechaEdicion;
    }

    // ----------------- Métodos automáticos -----------------

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now(); // Se guarda la fecha de hoy exacta
        this.estado = true; // Por defecto activo al crear
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaEdicion = LocalDateTime.now(); // Se actualiza automáticamente al editar
    }
}
