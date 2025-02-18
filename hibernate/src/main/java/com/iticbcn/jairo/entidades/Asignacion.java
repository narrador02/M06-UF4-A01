package com.iticbcn.jairo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "asignaciones")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @Column(nullable = false)
    private int horasTrabajadas;

    public Asignacion() {}

    public Asignacion(Empleado empleado, Proyecto proyecto, int horasTrabajadas) {
        this.empleado = empleado;
        this.proyecto = proyecto;
        this.horasTrabajadas = horasTrabajadas;
    }

    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }
    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }
    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }
    public int getHorasTrabajadas() { return horasTrabajadas; }
    public void setHorasTrabajadas(int horasTrabajadas) { this.horasTrabajadas = horasTrabajadas; }

    @Override
    public String toString() {
        return "Asignacion{id=" + id + ", empleado=" + empleado.getNombre() + ", proyecto=" + proyecto.getNombre() + ", horas=" + horasTrabajadas + "}";
    }
}