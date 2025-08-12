package org.ServiClean.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "Auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotBlank(message = "La acción es requerida")
    private String accion;

    @Column(name = "tabla_afectada")
    private String tablaAfectada;

    private String descripcion;

    private LocalDateTime fecha = LocalDateTime.now();

    public Integer getId()
    { return id; }

    public void setId(Integer id)
    { this.id = id; }

    public Usuario getUsuario()
    { return usuario; }

    public void setUsuario(Usuario usuario)
    { this.usuario = usuario; }

    public String getAccion()
    { return accion; }

    public void setAccion(String accion)
    { this.accion = accion; }

    public String getTablaAfectada()
    { return tablaAfectada; }

    public void setTablaAfectada(String tablaAfectada)
    { this.tablaAfectada = tablaAfectada; }

    public String getDescripcion()
    { return descripcion; }

    public void setDescripcion(String descripcion)
    { this.descripcion = descripcion; }

    public LocalDateTime getFecha()
    { return fecha; }

    public void setFecha(LocalDateTime fecha)
    { this.fecha = fecha; }
}
