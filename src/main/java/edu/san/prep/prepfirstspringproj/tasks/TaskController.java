package edu.san.prep.prepfirstspringproj.tasks;

import java.util.Collection;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @GetMapping
  public Collection<Task> getTasks() {
    return this.taskService.getAllTasks();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> getTask(@PathVariable Long id) {
    var task = taskService.getTask(id);
    if (task.isPresent()) {
      return ResponseEntity.ok(task.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<?> addTask(@RequestBody Task task) {
    if (taskService.addTask(task)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
    if (!Objects.equals(id, task.getId())) {
      return ResponseEntity.badRequest().build();
    }
    var oldValue = this.taskService.updateTask(task);
    if (Objects.nonNull(oldValue)) {
      return ResponseEntity.ok(oldValue);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
    var oldValue = this.taskService.deleteTask(id);
    if (Objects.nonNull(oldValue)) {
      return ResponseEntity.ok().body(oldValue);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

}
