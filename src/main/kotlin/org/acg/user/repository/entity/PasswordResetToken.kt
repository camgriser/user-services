package org.acg.user.repository.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import java.sql.Date
import java.time.LocalDate
import java.util.Calendar

@Entity(name = "pw_reset_tkn")
class PasswordResetToken (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column
    var token: String,

    @OneToOne
    var user: UserInfo,

    @Column(name = "expr_dt")
    var expireDate: Date
) {

    constructor(token: String, user: UserInfo) : this(null, token, user, Date.valueOf(LocalDate.now())) {
        setExpiration()
    }

    private val EXPIRATION = 60 * 24 // 24 HRS

    private fun setExpiration() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR,EXPIRATION)
        this.expireDate = Date(calendar.timeInMillis)
    }
}
