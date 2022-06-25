package com.finalproject.homeservice.RoleTest;

import com.finalproject.homeservice.entity.Role;
import com.finalproject.homeservice.entity.User;
import com.finalproject.homeservice.repository.RoleRepository;
import com.finalproject.homeservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    /*@Test
    /public void getRolesByUser(){
        long id = 1;
        User user = userRepository.getById(id);

        List<Role> roleList = roleRepository.findRolesByUser(user);
        roleList.forEach(System.out::println);
    }*/

    @Test
    public void getRoleByRoleName(){
        String roleName = "RegularUser";
        Role role = roleRepository.findRoleByName(roleName);
        System.out.println(role.getId());
    }
}
