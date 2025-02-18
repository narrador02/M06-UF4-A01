package com.iticbcn.jairo.dao;

import com.iticbcn.jairo.entidades.Proyecto;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ProyectoDAO {
    private EntityManager entityManager;

    public ProyectoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void crear(Proyecto proyecto) {
        entityManager.getTransaction().begin();
        entityManager.persist(proyecto);
        entityManager.getTransaction().commit();
    }

    public Proyecto obtener(int id) {
        return entityManager.find(Proyecto.class, id);
    }

    public List<Proyecto> obtenerTodos() {
        return entityManager.createQuery("SELECT p FROM Proyecto p", Proyecto.class).getResultList();
    }

    public void actualizar(Proyecto proyecto) {
        entityManager.getTransaction().begin();
        entityManager.merge(proyecto);
        entityManager.getTransaction().commit();
    }

    public void eliminar(int id) {
        entityManager.getTransaction().begin();
        Proyecto proyecto = entityManager.find(Proyecto.class, id);
        if (proyecto != null) {
            entityManager.remove(proyecto);
        }
        entityManager.getTransaction().commit();
    }
} // git commit -m "DAOs actualizados con persist, main actualizado para tratar con DAOs"