package com.dualproject.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dualproject.ecommerce.RoutesPath;
import com.dualproject.ecommerce.Models.User;
import com.dualproject.ecommerce.Services.UserService;

@RestController
public class RestUsers {

    private final String userRoutes = RoutesPath.routeUsers;

    @Autowired
    UserService userService;
    
    // ---------------------------------------- Get Methods -----------------------------------------
    @GetMapping(userRoutes + "/get/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getById(id).get();
    }

    // ---------------------------------------- Post Methods ----------------------------------------
    @PostMapping(userRoutes + "/register")
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }

    // ---------------------------------------- Put Methods -----------------------------------------
    @PutMapping(userRoutes + "/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user){
        userService.updateUser(id, user);
    }

    @PutMapping(value = userRoutes + "/update/{id}", params = "password")
    public void updatePassword(@PathVariable Long id, @RequestParam String password){
        userService.updatePassword(id, password);
    }

    @PutMapping(value = userRoutes + "/change_password", params = {"userId","oldPassword","newPassword"})
    public boolean changePassword(@RequestParam Long userId ,@RequestParam String oldPassword, @RequestParam String newPassword){
        return userService.changePassword(userId, oldPassword, newPassword);
    }

    @PutMapping(value = userRoutes + "/update/{id}", params = "roles")
    public void updateRoles(@PathVariable Long id, @RequestParam String roles){
        userService.updateRoles(id, roles);
    }

    @PutMapping(value = userRoutes + "/add/{id}", params = "role")
    public void addRole(@PathVariable Long id, @RequestParam String role){
        userService.addRole(id, role);
    }

    // ---------------------------------------- Delete Methods --------------------------------------
    @DeleteMapping(userRoutes + "/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
