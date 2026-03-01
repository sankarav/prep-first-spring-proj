package edu.san.prep.prepfirstspringproj.tasks;

import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

  public Collection<Task> getAllTasks() {
    return taskRepository.getAllTasks();
  }

  public Optional<Task> getTask(Long id) {
    return taskRepository.getTask(id);
  }

  public boolean addTask(Task task) {
    return this.taskRepository.addTask(task);
  }

  public Task deleteTask(Long id) {
    return this.taskRepository.deleteTask(id);
  }

  public Task updateTask(Task task) {
    return this.taskRepository.updateTask(task);
  }


}
