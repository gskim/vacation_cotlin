package com.example.vacation.controller

import com.example.vacation.model.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        mockMvc.perform(
                get("/users/hello")
        ).andExpect (
            status().isOk
        ).andExpect (
                content().string("Hello")
        ).andDo(print())
    }

    @Test
    fun getUsersTest() {
        val queryParam = LinkedMultiValueMap<String, Any>()
        queryParam.add("name", "steve")
        queryParam.add("age", 20)
        mockMvc.perform(
                get("/users")
        ).andExpect(
                status().isOk
        ).andExpect(
                content().contentType("application/json")
        )
    }

    @Test
    fun addUserTest() {
        val userRequest = UserRequest().apply {
            this.name  = "gskim"
            this.age = 20
            this.address = "안양시 동안구"
            this.email = "kiseon1987@gmail.com"
            this.createdAt = "2020-11-04 11:11:11"
            this.updatedAt = "2020-11-04 11:11:11"
        }
        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
            post("/users")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isOk
        ).andExpect(
            jsonPath("\$.name").value("gskim")
        ).andExpect (
            jsonPath("\$.age").value(20)
        ).andExpect(
            jsonPath("\$.email").value("kiseon1987@gmail.com")
        )
    }
}