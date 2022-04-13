package fr.jfc.ptut.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.jfc.ptut.entity.auth.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
