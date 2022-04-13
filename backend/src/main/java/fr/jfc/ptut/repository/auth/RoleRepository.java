package fr.jfc.ptut.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.jfc.ptut.entity.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
