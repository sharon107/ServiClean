package org.ServiClean.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> roles;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    private Boolean estado = true;

    // Relación Usuario → Auditoria (1:N)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Auditoria> auditorias = new ArrayList<>();

    // 🔹 Relación Usuario (Creador) → Tareas (1:N)
    @OneToMany(mappedBy = "creador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tarea> tareasCreadas = new HashSet<>();

    // 🔹 Relación Usuario (Asignado) → Tareas (1:N)
    @OneToMany(mappedBy = "asignado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tarea> tareasAsignadas = new HashSet<>();

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

    public String getContrasena()
    { return contrasena; }

    public void setContrasena(String contraseña)
    { this.contrasena = contraseña; }

    public Boolean getEstado()
    { return estado; }

    public void setEstado(Boolean estado)
    { this.estado = estado; }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Rol getRol() { return rol; }

    public void setRol(Rol rol) { this.rol = rol; }
}
