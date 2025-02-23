package com.iticbcn.jairo.dao;

import com.iticbcn.jairo.entidades.Empleado;
import org.hibernate.SessionFactory;

public class EmpleadoDAO extends GenDAOImpl<Empleado> {
    public EmpleadoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Empleado.class);
    }
}