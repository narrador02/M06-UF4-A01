package com.iticbcn.jairo.dao;

import com.iticbcn.jairo.entidades.Departamento;
import java.util.List;
import jakarta.persistence.EntityManager;

public class DepartamentoDAO {
    private EntityManager entityManager;

    public DepartamentoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void crear(Departamento departamento) {
        entityManager.getTransaction().begin();
        entityManager.persist(departamento);
        entityManager.getTransaction().commit();
    }

    public Departamento obtener(int id) {
        return entityManager.find(Departamento.class, id);
    }

    public List<Departamento> obtenerTodos() {
        return entityManager.createQuery("SELECT d FROM Departamento d", Departamento.class).getResultList();
    }

    public void actualizar(Departamento departamento) {
        entityManager.getTransaction().begin();
        entityManager.merge(departamento);
        entityManager.getTransaction().commit();
    }

    public void eliminar(int id) {
        entityManager.getTransaction().begin();
        Departamento departamento = entityManager.find(Departamento.class, id);
        if (departamento != null) {
            entityManager.remove(departamento);
        }
        entityManager.getTransaction().commit();
    }
}