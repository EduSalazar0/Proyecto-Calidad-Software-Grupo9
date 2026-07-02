package com.udla.taskmanager.repository;

import com.udla.taskmanager.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * Contrato de persistencia para tareas.
 *
 * TaskService depende de esta abstraccion, no de una implementacion
 * concreta (Dependency Inversion Principle). Cualquier implementacion
 * (en memoria, en archivo, en base de datos) debe poder sustituir a
 * otra sin alterar el comportamiento esperado por sus clientes
 * (Liskov Substitution Principle).
 */
public interface TaskRepository {

    /** Genera el siguiente id disponible para una nueva tarea. */
    int nextId();

    Task add(Task task);

    List<Task> findAll();

    Optional<Task> findById(int id);

    Task update(Task task);

    void deleteById(int id);

    boolean existsByTitle(String title);

    boolean existsById(int id);
}
