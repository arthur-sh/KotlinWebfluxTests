package com.example.arthur.testesReactive.controller

import com.example.arthur.testesReactive.domain.DTO.TaskDTO
import com.example.arthur.testesReactive.domain.Task
import com.example.arthur.testesReactive.service.TaskService
import com.example.arthur.testesReactive.utils.AlreadyExistingTaskException
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CrossOrigin(origins = ["http://localhost:3000"] )
@RestController
@RequestMapping("task")
class TaskController(val taskService: TaskService) {

    @PostMapping
    fun createTask(@RequestBody taskDTO: TaskDTO): Mono<Task>{
        try {
            return taskService.createTask(taskDTO)
        } catch (e: AlreadyExistingTaskException) {
            throw e
        }
    }

    @PutMapping("/{taskName}")
    fun changeTaskStatus(@PathVariable taskName: String): Mono<Task> {
        return taskService.changeTaskStatus(taskName)
    }

    @GetMapping("/{taskName}")
    fun findTaskByName(@PathVariable taskName: String): Mono<Task>{
        return taskService.findTaskByName(taskName)
    }

    @DeleteMapping("/{taskName}")
    fun deleteTask(@PathVariable taskName: String): Mono<Long>  {
        return taskService.deleteTask(taskName)
    }

    @CrossOrigin(origins = ["http://localhost:3000"] )
    @GetMapping()
    fun findAllTaSks(): Mono<List<Task>>{
        return taskService.findAllTasks().collectList()
    }

}