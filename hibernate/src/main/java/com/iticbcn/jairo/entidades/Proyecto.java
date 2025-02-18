package com.iticbcn.jairo.entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private double presupuesto;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private List<Asignacion> asignaciones;

    public Proyecto() {}

    public Proyecto(String nombre, double presupuesto, Departamento departamento) {
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.departamento = departamento;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPresupuesto() { return presupuesto; }
    public void setPresupuesto(double presupuesto) { this.presupuesto = presupuesto; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
    
    public List<Asignacion> getAsignaciones() { return asignaciones; }
    public void setAsignaciones(List<Asignacion> asignaciones) { this.asignaciones = asignaciones; }

    @Override
    public String toString() {
        return "Proyecto{id=" + id + ", nombre='" + nombre + "', presupuesto=" + presupuesto + ", departamento=" + departamento.getNombre() + "}";
    }
}