package com.webapp.testportal.repo;

import com.webapp.testportal.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleJpaRepo extends JpaRepository<UserRole,Long> {
}
