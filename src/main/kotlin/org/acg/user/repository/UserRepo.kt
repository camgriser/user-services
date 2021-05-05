package org.acg.user.repository;

import org.acg.user.repository.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepo : CrudRepository<UserInfo, Long> {
    fun findByEmail(email: String): UserInfo?
}