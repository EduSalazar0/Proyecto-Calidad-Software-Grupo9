package com.udla.taskmanager.exception;

/**
 * Se lanza cuando el id de tarea recibido no es valido
 * (por ejemplo, negativo o cero).
 */
public class InvalidTaskIdException extends RuntimeException {
    public InvalidTaskIdException(String message) {
        super(message);
    }
}
