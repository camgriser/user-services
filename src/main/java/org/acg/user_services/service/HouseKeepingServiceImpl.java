package org.acg.user_services.service;

import org.acg.user_services.repository.PasswordResetTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class HouseKeepingServiceImpl implements HouseKeepingService {

    @Autowired
    private PasswordResetTokenRepo passwordResetTokenRepo;

    @Scheduled(cron = "0, 0, *, *, *, *") // Everyday @ Midnight
    @Override
    public void housekeep() {
        passwordResetTokenRepo.deleteAllByExpireDateBefore(new Date(System.currentTimeMillis()));
    }
}
