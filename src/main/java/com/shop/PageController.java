package com.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Pages de contenu statique : mentions légales, confidentialité, CGU, cookies,
 * et page "indisponible" (remplace les anciens liens morts vers maintenance.php).
 */
@Controller
public class PageController {

	@GetMapping("/mentions-legales")
	public String legalNotice() {
		return "legalNotice";
	}

	@GetMapping("/confidentialite")
	public String privacy() {
		return "privacy";
	}

	@GetMapping("/cgu")
	public String terms() {
		return "terms";
	}

	@GetMapping("/cookies")
	public String cookies() {
		return "cookies";
	}

	@GetMapping("/maintenance")
	public String maintenance() {
		return "maintenance";
	}
}
