package com.webapp.testportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.testportal.repo.RoleJpaRepo;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
@Table(name="user-role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private Users user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "roleId")
    @JsonIgnore
    private Roles role;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Roles getRole() {
        return role;
    }

    public void setRoles(Roles role) {
        this.role = role;
    }


}
