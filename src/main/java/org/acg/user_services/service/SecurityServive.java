package org.acg.user_services.service;

public interface SecurityServive {

    String findLoggedInUsername();
    void autologin(String username, String token);
    String validatePasswordResetToken(long id, String token);

}
