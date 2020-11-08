package com.example.vacation.controller

import com.example.vacation.entity.User
import com.example.vacation.model.UserRequest
import com.example.vacation.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/users")
@Validated
class UserController(
    @Autowired private val userService: UserService
) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello"
    }

    @GetMapping("")
    fun getUsers(
//            @NotBlank
//            @Size(min = 2, max = 6)
//            @RequestParam name: String,
//
//            @Min(10)
//            @RequestParam age: Int
    ): List<User> {
        return this.userService.getUsers()
    }

    @PostMapping("")
    fun addUser(@Valid @RequestBody userRequest: UserRequest
//                , bindingResult: BindingResult
    ): ResponseEntity<Any> {
//        if (bindingResult.hasErrors()) {
//            // error
//            val msg = StringBuilder()
//            bindingResult.allErrors.forEach {
//                val field = it as FieldError
//                var message = it.defaultMessage
//                msg.append("${field.field} : ${message}\n")
//            }
//            return ResponseEntity.badRequest().body(msg.toString())
//        }
        return ResponseEntity.ok().body(userRequest)
    }

    @PostMapping("/exception")
    fun exception(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        return userRequest

    }

}