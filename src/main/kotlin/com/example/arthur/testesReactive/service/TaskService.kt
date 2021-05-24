package com.example.arthur.testesReactive.service

import com.example.arthur.testesReactive.domain.DTO.TaskDTO
import com.example.arthur.testesReactive.domain.Task
import com.example.arthur.testesReactive.repository.TaskRepository
import com.example.arthur.testesReactive.utils.AlreadyExistingTaskException
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class TaskService(val taskRepository: TaskRepository) {

    fun createTask(taskDTO: TaskDTO): Mono<Task>{
        taskDTO.name ?: run {
            throw IllegalArgumentException("You need to insert a valid name to your task")
        }
        return taskRepository.countByName(taskDTO.name).flatMap {
            if (it > 0) throw AlreadyExistingTaskException(taskDTO.name)
            return@flatMap taskRepository.save(Task(name = taskDTO.name, description = taskDTO.description)) }
    }

    fun changeTaskStatus(name: String): Mono<Task> {
            return taskRepository.findByName(name).flatMap { taskRepository.save(it.apply { done = !done }) }
    }

    fun findAllTasks(): Flux<Task> {
        return taskRepository.findAll()
    }

    fun deleteTask(name: String): Mono<Long>{
        return taskRepository.deleteByName(name)
        //taskRepository.findByName(name).flatMap { taskRepository.delete(it) }
    }

    fun findTaskByName(name: String): Mono<Task>{
        return taskRepository.findByName(name)
    }

    fun countByName(name: String): Mono<Long>{
        return taskRepository.countByName(name)
    }
/*
    fun create(taskDTO: TaskDTO): Mono<Task?>? {
        return this.taskRepository.findByName(taskDTO.name!!)
            .flatMap { existingTask -> Mono.error(TaskNotNew(existingTask.name!!)) }
            .then(this.taskRepository.save(Task(name = taskDTO.name, description = taskDTO.description)))
    }
*/
    internal class TaskNotNew(name: String) :
        RuntimeException("Task already present with name $name")

}