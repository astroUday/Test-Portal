package com.webapp.testportal.controller;

import com.webapp.testportal.exception.UserNotFoundException;
import com.webapp.testportal.daoservice.serviceimpl.UserServiceImpl;
import com.webapp.testportal.entity.Roles;
import com.webapp.testportal.entity.UserRole;
import com.webapp.testportal.entity.Users;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class userController {

    Roles roles= new Roles();
    private final UserServiceImpl userServiceImpl;

    //create user
    @PostMapping("/register/")
    public Users createUser(@Valid @RequestBody Users user) {

        roles.setRoleId(1L);
        roles.setRoleName("NORMAL");

        Set<UserRole> userRoleSet= new HashSet<>();
        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setRoles(roles);

        userRoleSet.add(userRole);
        return userServiceImpl.createUser(user,userRoleSet);
    }

    @GetMapping("/{username}")
    public EntityModel<Users> getuser(@PathVariable String username) throws UserNotFoundException {
    Users user=this.userServiceImpl.getUserByUsername(username);

    WebMvcLinkBuilder linkBuilder=linkTo(methodOn(this.getClass()).findAll());

        EntityModel<Users> entityModel=EntityModel.of(user);
        entityModel.add(linkBuilder.withRel("all-users"));
        return entityModel;
    }

    @GetMapping("/")
    public List<Users> findAll(){
        return userServiceImpl.findAll();
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username) throws UserNotFoundException {

        userServiceImpl.deleteUserByUsername(username);
        return "User Deleted";
    }

    @PostMapping("/login/")
    public User userLogin(@RequestBody User user){
        System.out.println("successfully logged In");
        return user;
    }



    @GetMapping("/logout")
    public String userLogout(@RequestHeader("Authorization") String auth){
        System.out.println(auth.substring(7));
        userServiceImpl.logout(auth.substring(7));
        return "Successfully logged out !";
    }

}
