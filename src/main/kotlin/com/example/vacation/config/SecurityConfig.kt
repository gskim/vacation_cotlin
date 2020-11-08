package com.example.vacation.config


import com.example.vacation.config.jwt.JwtConfig
import com.example.vacation.config.jwt.JwtTokenProvider
import com.example.vacation.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Autowired private val userService: UserService,
    @Autowired private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    @Autowired private val jwtTokenProvider: JwtTokenProvider
) : WebSecurityConfigurerAdapter()
{
    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .antMatcher("/api/**").authorizeRequests()
            .antMatchers("/users").anonymous()
            .antMatchers("/api/v1/user/register").anonymous()
            .antMatchers("/api/v1/user/login").anonymous()
            .anyRequest().authenticated()
            .and()
            .apply(JwtConfig(jwtTokenProvider))
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userService)
            .passwordEncoder(bCryptPasswordEncoder)
    }
}