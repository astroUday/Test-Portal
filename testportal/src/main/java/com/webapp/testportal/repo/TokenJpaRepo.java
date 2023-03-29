package com.webapp.testportal.repo;

import com.webapp.testportal.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokenJpaRepo extends JpaRepository<UserToken,String> {
//    void deleteByUserUuid(String uuid);
}
