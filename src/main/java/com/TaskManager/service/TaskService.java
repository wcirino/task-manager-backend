package com.TaskManager.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.TaskManager.dto.taskDTO;
import com.TaskManager.dto.taskNewDTO;
import com.TaskManager.entity.Task;
import com.TaskManager.enums.Prioridade;
import com.TaskManager.enums.Responsavel;
import com.TaskManager.enums.Status;
import com.TaskManager.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<taskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public Page<taskDTO> getAllTasks(Pageable pageable) {
        Page<Task> taskPage = taskRepository.findAll(pageable);
        return taskPage.map(this::convertToDto);
    }
    
    public taskDTO findById(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        return convertToDto(task);
    }

    public taskDTO createTask(taskNewDTO taskNewDto) {
        Task task = convertNewToEntity(taskNewDto);
        Task savedTask = taskRepository.save(task);
        return convertToDto(savedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public taskDTO updateTask(Long id, taskNewDTO taskNewDto) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {

            Task updatedTask = convertNewToEntity(taskNewDto);
            updatedTask.setId(existingTask.getId());
            updatedTask.setDt_Criacao(existingTask.getDt_Criacao()); 
            updatedTask.setDt_limite(existingTask.getDt_limite()); 

            Task savedTask = taskRepository.save(updatedTask);
            return convertToDto(savedTask);
        }
        return null;
    }

    
    public Page<taskDTO> searchTasks(Long id, String title, String description, Boolean completed, Date dtCreated, Pageable pageable) {
        Page<Task> taskPage = taskRepository.findAll(
                Specification.where(idEquals(id))
                    .and(titleContainsIgnoreCase(title))
                    .and(descriptionContainsIgnoreCase(description))
                    .and(completedEquals(completed))
                    .and(dtCreatedEquals(dtCreated)),
                pageable
            );
        return taskPage.map(this::convertToDto);
    }
    
    public taskDTO updateTaskStatusToConcluido(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setDt_Criacao(new Date()); 
            task.setStatus(Status.CONCLUIDA.getCodigo());
            Task updatedTask = taskRepository.save(task);
            return convertToDto(updatedTask);
        }
        return null;
    }

//    private taskDTO convertToDto(Task task) {
//        return modelMapper.map(task, taskDTO.class);
//    }

    private Task convertToEntity(taskDTO taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }
    
    private taskDTO convertToDto(Task task) {
        taskDTO dto = modelMapper.map(task, taskDTO.class);

        // Mapeia manualmente os campos de enumeração
        if (task.getStatus() != null) {
            dto.setStatus(Status.values()[task.getStatus() - 1]); 
        }
        if (task.getPrioridade() != null) {
            dto.setPrioridade(Prioridade.values()[task.getPrioridade() - 1]);
        }
        if (task.getResponsavel() != null) {
            dto.setResponsavel(Responsavel.values()[task.getResponsavel() - 1]); 
        }

        dto.setDt_limite(task.getDt_limite());

        return dto;
    }
    
    private taskDTO convertNewToDto(taskNewDTO taskNewDto) {
        taskDTO dto = new taskDTO();
        dto.setId(taskNewDto.getId());
        dto.setTitulo(taskNewDto.getTitulo());
        dto.setDescricao(taskNewDto.getDescricao());
        dto.setDt_Criacao(taskNewDto.getDt_Criacao());
        dto.setDt_Conclusao(taskNewDto.getDt_Conclusao());
        dto.setDt_limite(taskNewDto.getDt_limite());
        dto.setStatus(Status.values()[taskNewDto.getStatus() - 1]);
        dto.setPrioridade(Prioridade.values()[taskNewDto.getPrioridade() - 1]);
        dto.setResponsavel(Responsavel.values()[taskNewDto.getResponsavel() - 1]);
        return dto;
    }
    
    private Task convertNewToEntity(taskNewDTO taskNewDto) {
        Task task = new Task();
        task.setTitulo(taskNewDto.getTitulo());
        task.setDescricao(taskNewDto.getDescricao());
        task.setDt_Criacao(taskNewDto.getDt_Criacao());
        task.setDt_Conclusao(taskNewDto.getDt_Conclusao());
        task.setDt_limite(taskNewDto.getDt_limite());
       
        task.setPrioridade(taskNewDto.getPrioridade()); 
        task.setResponsavel(taskNewDto.getResponsavel()); 
        task.setStatus(taskNewDto.getStatus());
        return task;
    }



    private Specification<Task> idEquals(Long id) {
        return (root, query, criteriaBuilder) -> id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    private Specification<Task> titleContainsIgnoreCase(String title) {
        return (root, query, criteriaBuilder) -> title == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + title.toLowerCase() + "%");
    }

    private Specification<Task> descriptionContainsIgnoreCase(String description) {
        return (root, query, criteriaBuilder) -> description == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")), "%" + description.toLowerCase() + "%");
    }

    private Specification<Task> completedEquals(Boolean completed) {
        return (root, query, criteriaBuilder) -> completed == null ? null : criteriaBuilder.equal(root.get("completed"), completed);
    }

    private Specification<Task> dtCreatedEquals(Date dtCreated) {
        return (root, query, criteriaBuilder) -> dtCreated == null ? null : criteriaBuilder.equal(root.get("dtCriacao"), dtCreated);
    }
}
