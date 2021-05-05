package org.acg.user.repository;

import org.acg.user.repository.entity.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
interface PasswordResetTokenRepo : CrudRepository<PasswordResetToken, Long> {
    fun findByToken(token: String) : PasswordResetToken?
    fun deleteAllByExpireDateBefore(date: Date)
}
