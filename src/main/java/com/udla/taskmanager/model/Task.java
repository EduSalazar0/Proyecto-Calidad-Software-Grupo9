package com.udla.taskmanager.model;

import java.util.Objects;

/**
 * Modelo de dominio que representa una tarea.
 *
 * Sustituye el uso de un simple String para representar una tarea
 * (primitive obsession), permitiendo modelar un identificador estable,
 * un titulo, una descripcion opcional y un estado.
 */
public class Task {

    private final int id;
    private String title;
    private String description;
    private TaskStatus status;

    public Task(int id, String title, String description) {
        validateTitle(title);
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }

    private static void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El titulo de la tarea no puede ser nulo o vacio");
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", title='" + title + "', status=" + status + "}";
    }
}
