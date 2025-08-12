package org.ServiClean.modelos;

import jakarta.persistence.*;
        import java.time.LocalDate;

@Entity
@Table(name = "Historial_Tareas")
public class HistorialTarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tarea")
    private Tarea tarea;

    @Column(name = "fecha_completada")
    private LocalDate fechaCompletada = LocalDate.now();

    private String observaciones;

    public Integer getId()
    { return id; }

    public void setId(Integer id)
    { this.id = id; }

    public Tarea getTarea()
    { return tarea; }

    public void setTarea(Tarea tarea)
    { this.tarea = tarea; }

    public LocalDate getFechaCompletada()
    { return fechaCompletada; }

    public void setFechaCompletada(LocalDate fechaCompletada)
    { this.fechaCompletada = fechaCompletada; }

    public String getObservaciones()
    { return observaciones; }

    public void setObservaciones(String observaciones)
    { this.observaciones = observaciones; }
}
