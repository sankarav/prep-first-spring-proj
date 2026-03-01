package edu.san.prep.prepfirstspringproj.tasks;

import jakarta.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {
  private final ConcurrentHashMap<Long, Task> tasks;

  public TaskRepository() {
    this.tasks = new ConcurrentHashMap<>();
  }

  @PostConstruct
  public void init() {
    this.populateData();
  }

  public Collection<Task> getAllTasks() {
    return this.tasks.values();
  }

  private void populateData() {
    var task1 =
        Task.builder()
              .id(1L)
              .title("Wake up at 6am")
              .completed(false)
            .build();

    this.tasks.put(task1.getId(), task1);

    var task2 =
        Task.builder()
            .id(2L)
            .title("File tax returns")
            .completed(false)
            .build();
    this.tasks.put(task2.getId(), task2);

    var task3 =
        Task.builder()
            .id(3L)
            .title("Sleep at 9pm")
            .completed(false)
            .build();
    this.tasks.put(task3.getId(), task3);

  }

  public Optional<Task> getTask(Long id) {
    return Optional.ofNullable(this.tasks.get(id));
  }

  public boolean addTask(Task task) {
    var result = this.tasks.putIfAbsent(task.getId(), task);
    return result == null;
  }

  public Task deleteTask(Long id) {
    return this.tasks.remove(id);
  }

  public Task updateTask(Task task) {
    return this.tasks.replace(task.getId(), task);
  }
}
