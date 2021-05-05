package org.acg.user.repository.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Transient

@Entity(name = "user_info")
data class UserInfo (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long?,

    @Column(name = "user_email")
    val email: String,

    @Column(name = "user_nm")
    val name: String,

    @Column(name = "phone_num")
    val phoneNumber: String,

    @Column(name = "address")
    val address: String,

    @Column(name = "city")
    val city: String,

    @Column(name = "state")
    val state: String,

    @Column(name = "zip_cd")
    val zipCode: String,

    @Column(name = "token")
    var token: String,

    @Transient
    val confirmToken: String,

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = [ JoinColumn(name = "user_id") ]
            , inverseJoinColumns = [ JoinColumn(name = "role_id") ])
    var roles: Set<Role>
)
