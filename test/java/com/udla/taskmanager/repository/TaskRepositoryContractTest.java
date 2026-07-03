package com.udla.taskmanager.repository;

import com.udla.taskmanager.model.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class TaskRepositoryContractTest {

    protected abstract TaskRepository createRepository();

    @Test
    @DisplayName("nextId genera identificadores unicos y crecientes")
    void nextIdGeneratesUniqueIncreasingIds() {
        TaskRepository repository = createRepository();

        int first = repository.nextId();
        int second = repository.nextId();

        assertTrue(second > first);
    }

    @Test
    @DisplayName("add guarda la tarea y findById la recupera")
    void addAndFindById() {
        TaskRepository repository = createRepository();
        Task task = new Task(repository.nextId(), "Tarea 1", null);

        repository.add(task);
        Optional<Task> found = repository.findById(task.getId());

        assertTrue(found.isPresent());
        assertEquals(task.getTitle(), found.get().getTitle());
    }

    @Test
    @DisplayName("findById retorna vacio si la tarea no existe")
    void findByIdReturnsEmptyWhenMissing() {
        TaskRepository repository = createRepository();
        assertTrue(repository.findById(999).isEmpty());
    }

    @Test
    @DisplayName("findAll retorna todas las tareas agregadas")
    void findAllReturnsAllAddedTasks() {
        TaskRepository repository = createRepository();
        repository.add(new Task(repository.nextId(), "Tarea 1", null));
        repository.add(new Task(repository.nextId(), "Tarea 2", null));

        List<Task> tasks = repository.findAll();

        assertEquals(2, tasks.size());
    }

    @Test
    @DisplayName("existsByTitle detecta titulos existentes sin importar mayusculas")
    void existsByTitleIsCaseInsensitive() {
        TaskRepository repository = createRepository();
        repository.add(new Task(repository.nextId(), "Comprar boletos", null));

        assertTrue(repository.existsByTitle("comprar boletos"));
        assertFalse(repository.existsByTitle("Otra tarea"));
    }

    @Test
    @DisplayName("deleteById elimina la tarea correspondiente")
    void deleteByIdRemovesTask() {
        TaskRepository repository = createRepository();
        Task task = new Task(repository.nextId(), "Tarea a eliminar", null);
        repository.add(task);

        repository.deleteById(task.getId());

        assertFalse(repository.existsById(task.getId()));
    }

    @Test
    @DisplayName("update reemplaza los datos de la tarea existente")
    void updateReplacesExistingTask() {
        TaskRepository repository = createRepository();
        Task task = new Task(repository.nextId(), "Titulo original", null);
        repository.add(task);

        task.setTitle("Titulo actualizado");
        repository.update(task);

        Optional<Task> found = repository.findById(task.getId());
        assertTrue(found.isPresent());
        assertEquals("Titulo actualizado", found.get().getTitle());
    }
}