package fr.jfc.ptut.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.jfc.ptut.entity.auth.ERole;
import fr.jfc.ptut.entity.auth.Role;
import fr.jfc.ptut.entity.auth.User;
import fr.jfc.ptut.payload.request.LoginRequest;
import fr.jfc.ptut.payload.request.SignupRequest;
import fr.jfc.ptut.payload.response.JwtResponse;
import fr.jfc.ptut.payload.response.MessageResponse;
import fr.jfc.ptut.repository.auth.RoleRepository;
import fr.jfc.ptut.repository.auth.UserRepository;
import fr.jfc.ptut.security.jwt.JwtUtils;
import fr.jfc.ptut.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

	/**
	 * Permet de se connecter avec un username et un mdp
	 * @param loginRequest
	 * @return
	 */
    @PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        //Contexte spring à partir de username et password
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
        //génération d'un jwt
		String jwt = jwtUtils.generateJwtToken(authentication);
		//On récupère un userDetails
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        //On regarde quel est le rôle de l'userDetails		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
        //On retourne une jwt response contenant le token jwt + info utilisateur + role de ce dernier
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	/**
	 *  Permet de créer un nouvel utilisateur
	 * @param signUpRequest
	 * @return
	 */
    @PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        //Si l'username est déjà utilisé
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            //On renvoie un message d'erreur
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
        //Si l'email est déjà utilisé 
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            //On renvoie un message d'erreur
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		// On crée un utilisateur
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
        //Si pas de role dans la requête
		if (strRoles == null) {
            //On met le role USER à l'utilisateur
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		}else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
