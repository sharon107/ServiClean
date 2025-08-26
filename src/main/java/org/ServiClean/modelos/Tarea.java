package org.ServiClean.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea")
    private Integer id;

    @NotBlank(message = "El título es requerido")
    private String titulo;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "id_asignado")
    private Usuario asignado;

    @ManyToOne
    @JoinColumn(name = "id_creador")
    private Usuario creador;

    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion = LocalDate.now();

    private String estado = "Pendiente";

@ManyToMany(mappedBy = "tareas")
private Set<Usuario> usuarios = new HashSet<>();

    public Integer getId()
    { return id; }

    public void setId(Integer id)
    { this.id = id; }

    public String getTitulo()
    { return titulo; }

    public void setTitulo(String titulo)
    { this.titulo = titulo; }

    public String getDescripcion()
    { return descripcion; }

    public void setDescripcion(String descripcion)
    { this.descripcion = descripcion; }

    public Equipo getEquipo()
    { return equipo; }

    public void setEquipo(Equipo equipo)
    { this.equipo = equipo; }

    public Usuario getAsignado()
    { return asignado; }

    public void setAsignado(Usuario asignado)
    { this.asignado = asignado; }

    public Usuario getCreador()
    { return creador; }

    public void setCreador(Usuario creador)
    { this.creador = creador; }

    public LocalDate getFechaAsignacion()
    { return fechaAsignacion; }

    public void setFechaAsignacion(LocalDate fechaAsignacion)
    { this.fechaAsignacion = fechaAsignacion; }

    public String getEstado()
    { return estado; }

    public void setEstado(String estado)
    { this.estado = estado; }
}
