package org.acg.user_services.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity(name = "role")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_nm")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserInfo> users;
}
