package com.dualproject.ecommerce.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dualproject.ecommerce.Models.User;

import jakarta.transaction.Transactional;

public interface UserRepo extends JpaRepository<User, Long>{
    
    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET username =?2, active =?3, roles =?4 WHERE id =?1", nativeQuery = true)
    int updateUser(Long userId, String username, boolean active, String roles);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET password =?2 WHERE id =?1", nativeQuery = true)
    int updatePassword(Long userId, String password);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET roles =?2 WHERE id =?1", nativeQuery = true)
    int updateRoles(Long userId, String roles);

}
