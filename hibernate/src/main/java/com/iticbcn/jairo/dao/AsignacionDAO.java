package com.iticbcn.jairo.dao;

import com.iticbcn.jairo.entidades.Asignacion;
import org.hibernate.SessionFactory;

public class AsignacionDAO extends GenDAOImpl<Asignacion> {
    public AsignacionDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Asignacion.class);
    }
}