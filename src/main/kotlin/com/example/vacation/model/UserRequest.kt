package com.example.vacation.model

import com.example.vacation.annotation.StringFormatDateTime
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

data class UserRequest (

        @field:NotEmpty
        @field:Size(min = 2, max = 8)
        var name: String? = null,

        @field:Min(10)
        var password: String? = null,

        @field:Email
        var email: String? = null

//        var createdAt: String? = null,

//        @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지않습니다")
//        var updatedAt: String? = null
) {
//        @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 여야 합니다.")
//        private fun isValidCreatedAt(): Boolean {
//                return try {
//                    LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//                        true
//                } catch (e: Exception) {
//                        false
//                }
//        }
}