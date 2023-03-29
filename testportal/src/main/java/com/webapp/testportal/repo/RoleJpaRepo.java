package com.webapp.testportal.repo;

import com.webapp.testportal.entity.Roles;
import com.webapp.testportal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepo extends JpaRepository<Roles,Long> {
}
