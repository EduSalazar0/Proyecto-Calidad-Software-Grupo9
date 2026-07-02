package com.udla.taskmanager.exception;

/**
 * Se lanza cuando se intenta acceder, actualizar o eliminar
 * una tarea que no existe en el repositorio.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
