package com.udla.taskmanager.service;

import com.udla.taskmanager.exception.DuplicateTaskException;
import com.udla.taskmanager.exception.InvalidTaskIdException;
import com.udla.taskmanager.exception.TaskNotFoundException;
import com.udla.taskmanager.model.Task;
import com.udla.taskmanager.repository.TaskRepository;

import java.util.List;

/**
 * Logica de negocio para la gestion de tareas.
 *
 * Depende de la abstraccion TaskRepository, no de una implementacion
 * concreta (Dependency Inversion Principle). No conoce nada sobre
 * como se presentan los resultados al usuario (Single Responsibility
 * Principle): esa responsabilidad vive en la capa view.
 */
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task addTask(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El titulo de la tarea no puede ser nulo o vacio");
        }
        if (repository.existsByTitle(title)) {
            throw new DuplicateTaskException("Ya existe una tarea con el titulo: " + title);
        }
        Task task = new Task(repository.nextId(), title, description);
        return repository.add(task);
    }

    public List<Task> listTasks() {
        return repository.findAll();
    }

    public Task updateTask(int id, String newTitle, String newDescription) {
        validateId(id);
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("No existe una tarea con id: " + id));
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        return repository.update(task);
    }

    public void removeTask(int id) {
        validateId(id);
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException("No existe una tarea con id: " + id);
        }
        repository.deleteById(id);
    }

    private void validateId(int id) {
        if (id <= 0) {
            throw new InvalidTaskIdException("El id debe ser un numero positivo: " + id);
        }
    }
}
