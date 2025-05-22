package com.amolsoftwares.tasklist.service;

import com.amolsoftwares.tasklist.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/*
 * Project: Task List
 * Author: Amol Gadage
 * Description: Spring Boot application for managing tasks using in-memory data
 */
@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Optional<Task> getTaskById(Long id) {
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public void deleteTaskById(Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
    }

    public List<Task> getCompletedTasks() {
        return tasks.stream().filter(Task::isCompleted).collect(Collectors.toList());
    }

    public Task createTask(Task task) {
        task.setId(counter.incrementAndGet());
        tasks.add(task);
        return task;
    }

    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return getTaskById(id).map(existingTask -> {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());
            return existingTask;
        });
    }

}
