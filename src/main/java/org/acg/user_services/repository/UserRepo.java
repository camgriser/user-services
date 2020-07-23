package org.acg.user_services.repository;

import org.acg.user_services.repository.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserInfo, Long> {
    UserInfo findByEmail(String email);
}