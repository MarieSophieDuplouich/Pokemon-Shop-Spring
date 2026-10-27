package com.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Crée (ou promeut) le compte administrateur au démarrage, à partir de
 * site.admin.email et site.admin.password (variables ADMIN_EMAIL et ADMIN_PASSWORD).
 * Aucun identifiant n'est écrit dans le code. Sans ces variables, aucun admin n'est créé.
 */
@Component
public class AdminInitializer implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(AdminInitializer.class);

	@Autowired
	private UserRepository repo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Value("${site.admin.email:}")
	private String adminEmail;

	@Value("${site.admin.password:}")
	private String adminPassword;

	@Override
	public void run(ApplicationArguments args) {
		if (adminEmail.isBlank() || adminPassword.isBlank()) {
			log.warn("Aucun administrateur créé : définissez ADMIN_EMAIL et ADMIN_PASSWORD.");
			return;
		}

		User user = repo.findByEmail(adminEmail);
		if (user == null) {
			user = new User();
			user.setEmail(adminEmail);
			user.setFirstName("Admin");
			user.setLastName("ROCKET");
			user.setPassword(encoder.encode(adminPassword));
		}
		user.setRole("ADMIN");
		repo.save(user);
		log.info("Compte administrateur prêt : {}", adminEmail);
	}
}
