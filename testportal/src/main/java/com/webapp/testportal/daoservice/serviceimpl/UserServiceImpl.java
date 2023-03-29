package com.webapp.testportal.daoservice.serviceimpl;

import com.webapp.testportal.entity.*;
import com.webapp.testportal.exception.CustomApiExceptionHandler;
import com.webapp.testportal.daoservice.UserService;
import com.webapp.testportal.repo.RoleJpaRepo;
import com.webapp.testportal.repo.TokenJpaRepo;
import com.webapp.testportal.repo.UserJpaRepo;
import com.webapp.testportal.repo.UserRoleJpaRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService,UserService {

    private final UserJpaRepo userJpaRepo;
    private final UserRoleJpaRepo userRoleJpaRepo;
    private final RoleJpaRepo roleJpaRepo;
    private final TokenJpaRepo tokenJpaRepo;
    private final PasswordEncoder passwordEncoder;



    //Register a new User, Role, and UserRole
    @Override
    public Users createUser(Users users, Set<UserRole> userRoleSet1){

        Users local=userJpaRepo.findByUsername(users.getUsername());
        if(local!=null) throw new CustomApiExceptionHandler(HttpStatus.NOT_FOUND,"Username is already Present !");
        users.setPassword(passwordEncoder.encode(users.getPassword()));


        Roles roles=new Roles();
        roles.setRoleId(1L);
        roles.setRoleName("NORMAL");

        Roles role=roleJpaRepo.save(roles);


        userJpaRepo.save(users);

        UserRole userRole=new UserRole();
        userRole.setUser(users);
        userRole.setRoles(role);

        userRoleJpaRepo.save(userRole);


        return userJpaRepo.findByUsername(users.getUsername());
    }


    // find by username
    public Users getUserByUsername(String username) {
//        Predicate<? super Users> predicate= user->user.getUsername().equals(username);
//        return userJpaRepo.findAll().stream().filter(predicate).findFirst().orElse(null);
        return userJpaRepo.findByUsername(username);

    }

    //find all users
    public List<Users> findAll(){
        System.out.println(" Find all is calling ");
        return userJpaRepo.findAll();
    }

    // delete a user by username

    public void deleteUserByUsername(String username) {

        Users user=userJpaRepo.findByUsername(username);
        if(user==null) throw new CustomApiExceptionHandler(HttpStatus.BAD_REQUEST,"User you are trying to delete doesn't exists. Check the username !");
        System.out.println(userJpaRepo.findByUsername(username));
        userJpaRepo.deleteByUsername(username);
    }


    // at login, create new uuid
    public void createUuid(UserToken userToken){
        //child table
        tokenJpaRepo.save(userToken);
        //parent table
//        users.getUserTokenSet().add(userToken);
//        users.setUpdatedAt(LocalDateTime.now());
    }

    // find by username || used by daoAuthProvider
    @Override
    public UserDetails loadUserByUsername(String username) {

        System.out.println(userJpaRepo.findByUsername(username));
//        Predicate<? super Users> predicate= user->user.getUsername().equals(username);
        Users user= userJpaRepo.findByUsername(username);
        if(user==null)throw new CustomApiExceptionHandler(HttpStatus.NOT_FOUND,username + "doesn't exists, please REGISTER.");


        Collection<Authority> authorites=new ArrayList<>();
        user.getUserRoleSet().forEach(role -> {
            authorites.add(new Authority(role.getRole().getRoleName()));
        });
        System.out.println(user.getUsername()+user.getPassword()+authorites);
        return new User(user.getUsername(),user.getPassword(),authorites);
    }

    public Long findIdFromUUID(String uuid){
        System.out.println(uuid);
        Optional<UserToken> userToken=tokenJpaRepo.findById(uuid);
        return userToken.get().getUserr().getId();
    }

    public Optional<Users> getUserById(Long id) {
        return userJpaRepo.findById(id);
    }

//    public Users getCurrentUser(String uuid) {
//        UserToken userToken=tokenJpaRepo.findById(uuid.substring(7)).get();
//        System.out.println("This is user from UUID ->"+userToken.getUser());
//        Users users=userToken.getUser();
//        return userJpaRepo.findByUsername(users.getUsername());
//        // return userJpaRepo.findById(tokenJpaRepo.findById(uuid.substring(7)).get().getUser().getId()).get();
//    }

    public void logout(String token) {
        System.out.println(tokenJpaRepo.existsById(token));
        if(tokenJpaRepo.existsById(token)){
            tokenJpaRepo.deleteById(token);
        }
        else throw new UsernameNotFoundException("You cannot logout, user not found. ");
        System.out.println(tokenJpaRepo.findById(token));

    }
}
