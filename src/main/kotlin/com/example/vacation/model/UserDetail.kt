package com.example.vacation.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    var ID: Long?,
    var PASSWORD: String,
    var NAME: String
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val auth: ArrayList<GrantedAuthority> = ArrayList<GrantedAuthority>()
//        auth.add(SimpleGrantedAuthority())
        return auth;
    }

    fun getId(): Long? = ID

    override fun getPassword(): String = PASSWORD

    override fun getUsername(): String = NAME

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}