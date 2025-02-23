package com.iticbcn.jairo.dao;

import com.iticbcn.jairo.entidades.Departamento;
import org.hibernate.SessionFactory;

public class DepartamentoDAO extends GenDAOImpl<Departamento> {
    public DepartamentoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Departamento.class);
    }
}