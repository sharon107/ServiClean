package org.ServiClean.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;

import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> roles;

    private Boolean estado = true;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Auditoria> auditorias = new ArrayList<>();

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
}
