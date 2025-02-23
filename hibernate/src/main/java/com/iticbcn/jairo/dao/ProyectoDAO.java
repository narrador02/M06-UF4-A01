package com.iticbcn.jairo.dao;

import com.iticbcn.jairo.entidades.Proyecto;
import org.hibernate.SessionFactory;

public class ProyectoDAO extends GenDAOImpl<Proyecto> {
    public ProyectoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Proyecto.class);
    }
}