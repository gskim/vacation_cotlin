package com.example.vacation.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name="user")
class User (
    @Id()
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @field: [NotNull]
    var id: Long,

    @field: [NotNull]
    var name: String,

    @field: [NotNull]
    var email: String,

    @field: [NotNull]
    var password: String,

    @Column(name="access_token")
    var accessToken: String? = null,

    @field: [NotNull]
    @Column(name="vacation_count", columnDefinition = "float(12) default 15")
    var vacationCount: Float = 15f,

    @CreatedDate
//    @CreationTimestamp
    @Column(name="created_at", columnDefinition = "datetime(6) DEFAULT CURRENT_TIMESTAMP(6)")
    var createdAt: LocalDateTime,

//    @UpdateTimestamp
    @LastModifiedDate
    @Column(name="updated_at", columnDefinition = "datetime(6) DEFAULT CURRENT_TIMESTAMP(6)")
    var updatedAt: LocalDateTime

) {

//    @PreUpdate
//    private fun beforeUpdate() {
//        this.updatedAt = Instant.now()
//    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User
        if (email != other.email) return false
        return true
    }
    override fun hashCode(): Int {
        return email.hashCode()
    }
}