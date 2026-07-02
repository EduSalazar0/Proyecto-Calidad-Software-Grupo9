package com.udla.taskmanager;

import com.udla.taskmanager.exception.DuplicateTaskException;
import com.udla.taskmanager.exception.InvalidTaskIdException;
import com.udla.taskmanager.exception.TaskNotFoundException;
import com.udla.taskmanager.model.Task;
import com.udla.taskmanager.repository.InMemoryTaskRepository;
import com.udla.taskmanager.repository.TaskRepository;
import com.udla.taskmanager.service.TaskService;
import com.udla.taskmanager.view.ConsoleTaskFormatter;
import com.udla.taskmanager.view.TaskFormatter;

/**
 * Punto de ensamblaje del sistema (composition root).
 *
 * Decide que implementaciones concretas usar (InMemoryTaskRepository,
 * ConsoleTaskFormatter) e inyecta las dependencias, pero no contiene
 * logica de negocio propia.
 */
public class Main {

    public static void main(String[] args) {
        TaskRepository repository = new InMemoryTaskRepository();
        TaskService taskService = new TaskService(repository);
        TaskFormatter formatter = new ConsoleTaskFormatter();

        try {
            Task task = taskService.addTask("Complete project", "Entrega final del curso");
            formatter.showTaskAdded(task);

            formatter.showTaskList(taskService.listTasks());

            Task updated = taskService.updateTask(
                    task.getId(), "Complete project - revisado", "Entrega final revisada");
            formatter.showTaskUpdated(updated);

            taskService.removeTask(task.getId());
            formatter.showTaskRemoved(task.getId());

            formatter.showTaskList(taskService.listTasks());

            // Ejemplo de manejo de un error de dominio (id inexistente)
            taskService.removeTask(999);
        } catch (DuplicateTaskException | TaskNotFoundException
                 | InvalidTaskIdException | IllegalArgumentException e) {
            formatter.showError(e.getMessage());
        }
    }
}
