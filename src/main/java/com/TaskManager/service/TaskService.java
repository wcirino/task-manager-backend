package com.TaskManager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.TaskManager.entity.Task;
import com.TaskManager.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setTitulo(taskDetails.getTitulo());
            existingTask.setDescricao(taskDetails.getDescricao());
           // existingTask.setCompleted(taskDetails.isCompleted());
            existingTask.setPrioridade(taskDetails.getPrioridade());
            existingTask.setResponsavel(taskDetails.getResponsavel());
            existingTask.setStatus(taskDetails.getStatus());
            existingTask.setDtCriacao(taskDetails.getDtCriacao());
            existingTask.setDtConclusao(taskDetails.getDtConclusao());
            return taskRepository.save(existingTask);
        }
        return null;
    }
    
    public Page<Task> searchTasks(Long id, String title, String description, Boolean completed, Date dt_created, Pageable pageable) {
            return taskRepository.findAll(
                Specification.where(idEquals(id))
                    .and(titleContainsIgnoreCase(title))
                    .and(descriptionContainsIgnoreCase(description))
                    .and(completedEquals(completed))
                    .and(dtCreatedEquals(dt_created)),
                pageable
            );
        }

        private Specification<Task> idEquals(Long id) {
            return (root, query, criteriaBuilder) -> id == null ? null : criteriaBuilder.equal(root.get("id"), id);
        }

        private Specification<Task> titleContainsIgnoreCase(String title) {
            return (root, query, criteriaBuilder) -> title == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
        }

        private Specification<Task> descriptionContainsIgnoreCase(String description) {
            return (root, query, criteriaBuilder) -> description == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%");
        }

        private Specification<Task> completedEquals(Boolean completed) {
            return (root, query, criteriaBuilder) -> completed == null ? null : criteriaBuilder.equal(root.get("completed"), completed);
        }

        private Specification<Task> dtCreatedEquals(Date dt_created) {
            return (root, query, criteriaBuilder) -> dt_created == null ? null : criteriaBuilder.equal(root.get("dt_created"), dt_created);
        }
}
