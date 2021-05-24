package com.example.arthur.testesReactive.utils

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
internal class AlreadyExistingTaskException(name: String?) :
    Exception(("Already exists a task with the name $name"))