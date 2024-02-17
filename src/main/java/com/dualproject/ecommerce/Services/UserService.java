package com.dualproject.ecommerce.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dualproject.ecommerce.Models.User;
import com.dualproject.ecommerce.Repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepo repository;

    public void registerUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        repository.save(user);
    }

    // Toma la lista de roles y las agrega a una lista, agrega el rol a esa lista y pasa la lista a String para el query
    public void addRole(Long userId, String role){
        // Se hacen 2 listas ya que si no es ArrayList es menos compatible y falla
        List<String> rolesTemp = Arrays.asList(repository.findById(userId).get().getRoles().split(","));
        List<String> roles = new ArrayList<>();

        for(String rol : rolesTemp){
            // Si ya tiene el rol, no lo agrega
            if(rol.equalsIgnoreCase(role)) return;

            roles.add(rol);
        }
        roles.add(role);

        repository.updateRoles(userId, String.join(",", roles));
    }

    public Optional<User> getById(Long userId){
        return repository.findById(userId);
    }

    public Optional<User> getUserByUsername(String username) throws UsernameNotFoundException{
        return repository.findByUsername(username);
    }

    public void updateUser(Long userId, User user){
        repository.updateUser(user.getId(), user.getUsername(), user.isActive(), user.getRoles());
    }

    public void updateRoles(Long userId, String roles){
        repository.updateRoles(userId, roles);
    }

    public void updatePassword(Long userId, String password){
        repository.updatePassword(userId, new BCryptPasswordEncoder().encode(password));
    }

    // Comprueba si 'oldPassword' es la guardada en la base de datos, si es la misma cambia a la nueva contraseña y devuelve true
    public boolean changePassword(Long userId, String oldPassword, String newPassword){
        if(!new BCryptPasswordEncoder().matches(oldPassword, repository.findById(userId).get().getPassword())) return false;

        repository.updatePassword(userId, new BCryptPasswordEncoder().encode(newPassword));
        return true;
    }

    public void deleteUser(Long userId){
        repository.deleteById(userId);
    }
}
