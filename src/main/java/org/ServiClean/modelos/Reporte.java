package org.ServiClean.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Reportes")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Integer id;

    private String tipo;

    @Column(name = "fecha_generado")
    private LocalDate fechaGenerado = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "generado_por")
    private Usuario generadoPor;

    private String contenido;

    public Integer getId()
    { return id; }

    public void setId(Integer id)
    { this.id = id; }

    public String getTipo()
    { return tipo; }

    public void setTipo(String tipo)
    { this.tipo = tipo; }

    public LocalDate getFechaGenerado()
    { return fechaGenerado; }

    public void setFechaGenerado(LocalDate fechaGenerado)
    { this.fechaGenerado = fechaGenerado; }

    public Usuario getGeneradoPor()
    { return generadoPor; }

    public void setGeneradoPor(Usuario generadoPor)
    { this.generadoPor = generadoPor; }

    public String getContenido()
    { return contenido; }

    public void setContenido(String contenido)
    { this.contenido = contenido; }
}
