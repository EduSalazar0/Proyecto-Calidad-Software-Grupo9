package com.udla.taskmanager.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskTest {

    @Test
    @DisplayName("Crea una tarea valida con estado PENDING por defecto")
    void createsValidTaskWithPendingStatus() {
        Task task = new Task(1, "Estudiar SOLID", "Repasar los 5 principios");

        assertEquals(1, task.getId());
        assertEquals("Estudiar SOLID", task.getTitle());
        assertEquals(TaskStatus.PENDING, task.getStatus());
    }

    @Test
    @DisplayName("Rechaza titulo nulo en el constructor")
    void rejectsNullTitleOnConstruction() {
        assertThrows(IllegalArgumentException.class, () -> new Task(1, null, "desc"));
    }

    @Test
    @DisplayName("Rechaza titulo vacio o en blanco en el constructor")
    void rejectsBlankTitleOnConstruction() {
        assertThrows(IllegalArgumentException.class, () -> new Task(1, "   ", "desc"));
    }

    @Test
    @DisplayName("Rechaza titulo invalido tambien al actualizarlo con setTitle")
    void rejectsInvalidTitleOnSetter() {
        Task task = new Task(1, "Titulo valido", null);
        assertThrows(IllegalArgumentException.class, () -> task.setTitle(""));
    }

    @Test
    @DisplayName("Dos tareas con el mismo id son iguales, sin importar el titulo")
    void tasksWithSameIdAreEqual() {
        Task a = new Task(1, "A", null);
        Task b = new Task(1, "B", null);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}