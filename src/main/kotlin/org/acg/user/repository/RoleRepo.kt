package org.acg.user.repository;

import org.acg.user.repository.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RoleRepo : CrudRepository<Role, Long> {
    fun findByName(name: String): Role
}