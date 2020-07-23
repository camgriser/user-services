package org.acg.user_services.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.Calendar;

@Entity(name = "pw_reset_tkn")
@Getter @Setter
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24; // 24 HRS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String token;

    @OneToOne(targetEntity = UserInfo.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserInfo user;

    @Column(name = "expr_dt")
    private Date expireDate;

    public void setExpiration() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, EXPIRATION);
        this.expireDate = new Date(calendar.getTimeInMillis());
    }
}
