package ua.edu.lnu.shop_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.lnu.shop_api.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailIgnoreCase(String email);

//
//    @Query("select u from User u LEFT JOIN FETCH u.history LEFT JOIN FETCH u.addresses where u.id = ?1")
//    Optional<User> findById(UUID id);
}