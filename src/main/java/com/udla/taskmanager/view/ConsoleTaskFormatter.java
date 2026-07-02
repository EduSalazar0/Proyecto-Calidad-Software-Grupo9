package com.udla.taskmanager.view;

import com.udla.taskmanager.model.Task;

import java.util.List;

/**
 * Presenta los resultados de las operaciones sobre tareas en consola.
 */
public class ConsoleTaskFormatter implements TaskFormatter {

    @Override
    public void showTaskAdded(Task task) {
        System.out.println("Tarea agregada: " + task);
    }

    @Override
    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        for (Task task : tasks) {
            System.out.println("Tarea " + task.getId() + ": " + task.getTitle()
                    + " [" + task.getStatus() + "]");
        }
    }

    @Override
    public void showTaskUpdated(Task task) {
        System.out.println("Tarea actualizada: " + task);
    }

    @Override
    public void showTaskRemoved(int id) {
        System.out.println("Tarea con id " + id + " eliminada.");
    }

    @Override
    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
