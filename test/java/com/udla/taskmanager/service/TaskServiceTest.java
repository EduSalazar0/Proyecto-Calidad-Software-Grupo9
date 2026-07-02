package com.udla.taskmanager.service;

import com.udla.taskmanager.exception.DuplicateTaskException;
import com.udla.taskmanager.exception.InvalidTaskIdException;
import com.udla.taskmanager.exception.TaskNotFoundException;
import com.udla.taskmanager.model.Task;
import com.udla.taskmanager.repository.InMemoryTaskRepository;
import com.udla.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        TaskRepository repository = new InMemoryTaskRepository();
        taskService = new TaskService(repository);
    }

    @Test
    @DisplayName("Agrega una tarea valida")
    void addsValidTask() {
        Task task = taskService.addTask("Comprar boletos", "Para el concierto");

        assertEquals("Comprar boletos", task.getTitle());
        assertEquals(1, taskService.listTasks().size());
    }

    @Test
    @DisplayName("Rechaza una tarea con titulo duplicado")
    void rejectsDuplicateTitle() {
        taskService.addTask("Comprar boletos", null);

        assertThrows(DuplicateTaskException.class,
                () -> taskService.addTask("Comprar boletos", "Otra descripcion"));
    }

    @Test
    @DisplayName("Rechaza una tarea con titulo nulo o vacio")
    void rejectsNullOrBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> taskService.addTask(null, null));
        assertThrows(IllegalArgumentException.class, () -> taskService.addTask("   ", null));
    }

    @Test
    @DisplayName("Lista vacia cuando no hay tareas registradas")
    void listsEmptyWhenNoTasks() {
        List<Task> tasks = taskService.listTasks();
        assertTrue(tasks.isEmpty());
    }

    @Test
    @DisplayName("Lista todas las tareas registradas")
    void listsAllRegisteredTasks() {
        taskService.addTask("Tarea 1", null);
        taskService.addTask("Tarea 2", null);

        assertEquals(2, taskService.listTasks().size());
    }

    @Test
    @DisplayName("Elimina una tarea existente")
    void removesExistingTask() {
        Task task = taskService.addTask("Tarea a eliminar", null);

        taskService.removeTask(task.getId());

        assertTrue(taskService.listTasks().isEmpty());
    }

    @Test
    @DisplayName("Lanza TaskNotFoundException al eliminar un id inexistente")
    void throwsWhenRemovingMissingTask() {
        assertThrows(TaskNotFoundException.class, () -> taskService.removeTask(999));
    }

    @Test
    @DisplayName("Lanza InvalidTaskIdException al eliminar con id negativo o cero")
    void throwsWhenRemovingWithInvalidId() {
        assertThrows(InvalidTaskIdException.class, () -> taskService.removeTask(0));
        assertThrows(InvalidTaskIdException.class, () -> taskService.removeTask(-5));
    }

    @Test
    @DisplayName("Actualiza una tarea existente")
    void updatesExistingTask() {
        Task task = taskService.addTask("Titulo original", "Descripcion original");

        Task updated = taskService.updateTask(task.getId(), "Titulo nuevo", "Descripcion nueva");

        assertEquals("Titulo nuevo", updated.getTitle());
        assertEquals("Descripcion nueva", updated.getDescription());
    }

    @Test
    @DisplayName("Lanza TaskNotFoundException al actualizar un id inexistente")
    void throwsWhenUpdatingMissingTask() {
        assertThrows(TaskNotFoundException.class,
                () -> taskService.updateTask(999, "Nuevo titulo", null));
    }

    @Test
    @DisplayName("Lanza InvalidTaskIdException al actualizar con id invalido")
    void throwsWhenUpdatingWithInvalidId() {
        assertThrows(InvalidTaskIdException.class,
                () -> taskService.updateTask(-1, "Nuevo titulo", null));
    }
}