package com.iticbcn.jairo.app;

import com.iticbcn.jairo.dao.DepartamentoDAO;
import com.iticbcn.jairo.dao.EmpleadoDAO;
import com.iticbcn.jairo.dao.ProyectoDAO;
import com.iticbcn.jairo.dao.AsignacionDAO;
import com.iticbcn.jairo.entidades.Departamento;
import com.iticbcn.jairo.entidades.Empleado;
import com.iticbcn.jairo.entidades.Proyecto;
import com.iticbcn.jairo.entidades.Asignacion;
import java.util.List;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate_db");
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        
        // Inicialización de DAOs
        DepartamentoDAO departamentoDAO = new DepartamentoDAO(sessionFactory);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO(sessionFactory);
        ProyectoDAO proyectoDAO = new ProyectoDAO(sessionFactory);
        AsignacionDAO asignacionDAO = new AsignacionDAO(sessionFactory);
        
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("=== MENÚ ===");
            System.out.println("1. Crear Departamento");
            System.out.println("2. Crear Empleado");
            System.out.println("3. Crear Proyecto");
            System.out.println("4. Asignar Empleado a Proyecto");
            System.out.println("5. Mostrar Departamentos");
            System.out.println("6. Actualizar Departamento");
            System.out.println("7. Eliminar Departamento");
            System.out.println("8. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.print("Nombre del departamento: ");
                    String nombreDepto = scanner.nextLine();
                    System.out.print("Presupuesto: ");
                    double presupuesto = scanner.nextDouble();
                    departamentoDAO.save(new Departamento(nombreDepto, presupuesto));
                    System.out.println("Departamento creado.");
                    break;
                    
                case 2:
                    System.out.print("Nombre del empleado: ");
                    String nombreEmp = scanner.nextLine();
                    System.out.print("Salario: ");
                    double salario = scanner.nextDouble();
                    System.out.print("ID del departamento: ");
                    int deptoId = scanner.nextInt();
                    Departamento depto = departamentoDAO.get(deptoId);
                    if (depto != null) {
                        empleadoDAO.save(new Empleado(nombreEmp, salario, depto));
                        System.out.println("Empleado creado.");
                    } else {
                        System.out.println("Departamento no encontrado.");
                    }
                    break;
                    
                case 3:
                    System.out.print("Nombre del proyecto: ");
                    String nombreProyecto = scanner.nextLine();
                    System.out.print("Presupuesto: ");
                    double presupuestoProyecto = scanner.nextDouble();
                    System.out.print("ID del departamento: ");
                    int deptoIdProyecto = scanner.nextInt();
                    Departamento deptoProyecto = departamentoDAO.get(deptoIdProyecto);
                    if (deptoProyecto != null) {
                        proyectoDAO.save(new Proyecto(nombreProyecto, presupuestoProyecto, deptoProyecto));
                        System.out.println("Proyecto creado.");
                    } else {
                        System.out.println("Departamento no encontrado.");
                    }
                    break;
                    
                case 4:
                    System.out.print("ID del empleado: ");
                    int empId = scanner.nextInt();
                    Empleado empleado = empleadoDAO.get(empId);
                    if (empleado == null) {
                        System.out.println("Empleado no encontrado.");
                        break;
                    }
                    System.out.print("ID del proyecto: ");
                    int proyectoId = scanner.nextInt();
                    Proyecto proyecto = proyectoDAO.get(proyectoId);
                    if (proyecto == null) {
                        System.out.println("Proyecto no encontrado.");
                        break;
                    }
                    System.out.print("Horas trabajadas: ");
                    int horas = scanner.nextInt();
                    asignacionDAO.save(new Asignacion(empleado, proyecto, horas));
                    System.out.println("Asignación creada correctamente.");
                    break;
                    
                case 5:
                    List<Departamento> departamentos = departamentoDAO.getAll();
                    departamentos.forEach(System.out::println);
                    break;
                    
                case 6:
                    System.out.print("ID del departamento a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    Departamento departamentoActualizar = departamentoDAO.get(idActualizar);
                    if (departamentoActualizar != null) {
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nuevo presupuesto: ");
                        double nuevoPresupuesto = scanner.nextDouble();
                        departamentoActualizar.setNombre(nuevoNombre);
                        departamentoActualizar.setPresupuesto(nuevoPresupuesto);
                        departamentoDAO.update(departamentoActualizar);
                        System.out.println("Departamento actualizado correctamente.");
                    } else {
                        System.out.println("Departamento no encontrado.");
                    }
                    break;
                    
                case 7:
                    System.out.print("ID del departamento a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    Departamento departamentoEliminar = departamentoDAO.get(idEliminar);
                    if (departamentoEliminar != null) {
                        departamentoDAO.delete(departamentoEliminar);
                        System.out.println("Departamento eliminado correctamente.");
                    } else {
                        System.out.println("Departamento no encontrado.");
                    }
                    break;
                    
                case 8:
                    System.out.println("Saliendo...");
                    break;
                    
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 8);
        
        // Cierre de recursos
        sessionFactory.close();
        emf.close();
        scanner.close();
    }
}