package com.udla.taskmanager.view;

import com.udla.taskmanager.model.Task;

import java.util.List;

/**
 * Contrato de presentacion de resultados al usuario.
 *
 * Permite agregar nuevos formatos de salida (por ejemplo, un
 * JsonTaskFormatter para una API REST) sin modificar TaskService
 * ni las implementaciones existentes (Open/Closed Principle).
 */
public interface TaskFormatter {
    void showTaskAdded(Task task);

    void showTaskList(List<Task> tasks);

    void showTaskUpdated(Task task);

    void showTaskRemoved(int id);

    void showError(String message);
}
