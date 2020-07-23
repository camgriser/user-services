package org.acg.user_services.repository;

import org.acg.user_services.repository.entity.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface PasswordResetTokenRepo extends CrudRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
    void deleteAllByExpireDateBefore(Date date);
}
