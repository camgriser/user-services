package org.acg.user.repository.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity(name = "role")
data class Role (

    @Id
    @Column(name = "role_id")
    val id: Long,

    @Column(name = "role_nm")
    val name: String,

    @ManyToMany(mappedBy = "roles")
    val users: Set<UserInfo>
)