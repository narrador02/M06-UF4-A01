package com.iticbcn.jairo.dao;

import com.iticbcn.jairo.entidades.Asignacion;
import jakarta.persistence.EntityManager;
import java.util.List;

public class AsignacionDAO {
    private EntityManager entityManager;

    public AsignacionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void crear(Asignacion asignacion) {
        entityManager.getTransaction().begin();
        entityManager.persist(asignacion);
        entityManager.getTransaction().commit();
    }

    public List<Asignacion> obtenerTodas() {
        return entityManager.createQuery("SELECT a FROM Asignacion a", Asignacion.class).getResultList();
    }

    public void actualizar(Asignacion asignacion) {
        entityManager.getTransaction().begin();
        entityManager.merge(asignacion);
        entityManager.getTransaction().commit();
    }

    public void eliminar(int id) {
        entityManager.getTransaction().begin();
        Asignacion asignacion = entityManager.find(Asignacion.class, id);
        if (asignacion != null) {
            entityManager.remove(asignacion);
        }
        entityManager.getTransaction().commit();
    }
}