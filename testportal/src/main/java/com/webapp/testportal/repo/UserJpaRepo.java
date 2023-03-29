package com.webapp.testportal.repo;

import com.webapp.testportal.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepo extends JpaRepository<Users,Long> {
//    public Users findByUsername(String username);
    Users findByUsername(String username);

    void deleteByUsername(String username);
}
