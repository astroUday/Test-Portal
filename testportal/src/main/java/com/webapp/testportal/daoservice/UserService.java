package com.webapp.testportal.daoservice;

import com.webapp.testportal.entity.UserRole;
import com.webapp.testportal.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {
    public Users createUser(Users users, Set<UserRole> userRoleSet) ;
}
