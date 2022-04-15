package fr.jfc.ptut.security.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.jfc.ptut.entity.auth.User;
import fr.jfc.ptut.repository.auth.UserRepository;

//Permet d'accéder aux objets de type user detail service
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
	UserRepository userRepository;

    /*
    *Retourne un UserDetails (avec le role grace à build) si l'username en parametre existe
    */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);
    }
    
}
