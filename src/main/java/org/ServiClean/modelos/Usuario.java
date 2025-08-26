package org.ServiClean.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @NotBlank(message = "El correo es requerido")
    @Email(message = "Debe ser un correo válido")
    private String correo;

    @NotBlank(message = "La contraseña es requerida")
    private String contraseña;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    private Boolean estado = true;

    @ManyToMany
    @JoinTable(
            name = "usuarios_tareas",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "tarea_id")
    )

    private Set<Tarea> tareas = new HashSet<>();

    public Set<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tarea> tareas) {
        this.tareas = tareas;
    }

    public Integer getId()
    { return id; }

    public void setId(Integer id)
    { this.id = id; }

    public String getNombre()
    { return nombre; }

    public void setNombre(String nombre)
    { this.nombre = nombre; }

    public String getApellido()
    { return apellido; }

    public void setApellido(String apellido)
    { this.apellido = apellido; }

    public String getCorreo()
    { return correo; }

    public void setCorreo(String correo)
    { this.correo = correo; }

    public String getContraseña()
    { return contraseña; }

    public void setContraseña(String contraseña)
    { this.contraseña = contraseña; }

    public Rol getRol()
    { return rol; }

    public void setRol(Rol rol)
    { this.rol = rol; }

    public Boolean getEstado()
    { return estado; }

    public void setEstado(Boolean estado)
    { this.estado = estado; }
}
