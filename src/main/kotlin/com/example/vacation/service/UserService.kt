package com.example.vacation.service

import com.example.vacation.entity.User
import com.example.vacation.model.UserDetail
import com.example.vacation.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val userRepository: UserRepository
) : UserDetailsService {

    fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    fun getUser(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(name: String): UserDetails {
        val user: User? = userRepository.findByName(name)
        user?: throw UsernameNotFoundException("cannot find such username : $name")

        return UserDetail(user.id, user.password, user.name)
    }
}