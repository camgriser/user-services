package org.acg.user.service;

import org.acg.user.repository.PasswordResetTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
class HouseKeepingServiceImpl : HouseKeepingService {

    @Autowired
    private lateinit var passwordResetTokenRepo: PasswordResetTokenRepo

    @Scheduled(cron = "0, 0, *, *, *, *") // Everyday @ Midnight
    override fun housekeep() {
        passwordResetTokenRepo.deleteAllByExpireDateBefore( Date(System.currentTimeMillis()))
    }
}
