package com.iticbcn.jairo.dao;

import com.iticbcn.jairo.entidades.Empleado;
import jakarta.persistence.EntityManager;
import java.util.List;

public class EmpleadoDAO {
    private EntityManager entityManager;

    public EmpleadoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void crear(Empleado empleado) {
        entityManager.getTransaction().begin();
        entityManager.persist(empleado);
        entityManager.getTransaction().commit();
    }

    public Empleado obtener(int id) {
        return entityManager.find(Empleado.class, id);
    }

    public List<Empleado> obtenerTodos() {
        return entityManager.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
    }

    public void actualizar(Empleado empleado) {
        entityManager.getTransaction().begin();
        entityManager.merge(empleado);
        entityManager.getTransaction().commit();
    }

    public void eliminar(int id) {
        entityManager.getTransaction().begin();
        Empleado empleado = entityManager.find(Empleado.class, id);
        if (empleado != null) {
            entityManager.remove(empleado);
        }
        entityManager.getTransaction().commit();
    }
}