package com.udla.taskmanager.repository;

import com.udla.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 *
 * Es una entre varias implementaciones posibles del contrato
 * TaskRepository; puede sustituirse por otra (por ejemplo, una que
 * persista en archivo o en base de datos) sin afectar a TaskService.
 */
public class InMemoryTaskRepository implements TaskRepository {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @Override
    public int nextId() {
        return idGenerator.getAndIncrement();
    }

    @Override
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public Optional<Task> findById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();
    }

    @Override
    public Task update(Task task) {
        deleteById(task.getId());
        tasks.add(task);
        return task;
    }

    @Override
    public void deleteById(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    @Override
    public boolean existsByTitle(String title) {
        return tasks.stream()
                .anyMatch(task -> task.getTitle().equalsIgnoreCase(title));
    }

    @Override
    public boolean existsById(int id) {
        return tasks.stream()
                .anyMatch(task -> task.getId() == id);
    }
}
