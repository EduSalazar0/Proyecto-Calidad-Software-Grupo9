package com.udla.taskmanager.exception;

/**
 * Se lanza al intentar agregar una tarea cuyo titulo
 * ya existe en el repositorio.
 */
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException(String message) {
        super(message);
    }
}
