# Marie-Sophie Duplouich Avant-propos/Preface

> ## ⚠️ Projet fictif / Fictional project
> **FR —** ROCKET est un **site fictif** : c'est un projet d'apprentissage de Java et de Spring Boot réalisé par **Marie-Sophie Duplouich** pour son portfolio. Aucun produit n'est réellement vendu, aucune commande ni aucun paiement n'est possible. N'y saisissez pas de vraies données personnelles.
>
> **Remerciements / Thanks —** Merci à **wontaekoh**, auteur du projet d'origine [Pokemon-Shop-Spring](https://github.com/wontaekoh/Pokemon-Shop-Spring), dont ce projet s'inspire. / Many thanks to **wontaekoh**, author of the original project, which this one is based on.
>
> **EN —** ROCKET is a **fictional site**: a Java / Spring Boot learning project by **Marie-Sophie Duplouich** for her portfolio. Nothing is actually sold. Do not enter real personal data.

Projet Java Spring Boot Pokemon E-commerce pour découvrir Java. Ce projet initial appartient à https://github.com/wontaekoh/Pokemon-Shop-Spring .  Je voudrais améliorer le front-end et me familiariser avec la POO de Java.

A Pokémon e-commerce project using Java Spring Boot, created to learn Java. The original project belongs to https://github.com/wontaekoh/Pokemon-Shop-Spring . I aim to improve the front-end and familiarize myself with Java OOP.

# Pokemon-Shop-Spring
This project is a RESTful Java web service version of [Pokemon e-commerce website](https://github.com/wontaekoh/Pokemon-Shop)
including user account CRUD operation testing using JUnit.


<br>

**Features:**
- User authentication (login and logout)
- User Create, Read, Update, and Delete
- Product Read and Delete

**Future Tasks:**
- Adding product create and update functions
- Adding product search function

<br>

### Technologies Used:
- Spring Boot framework
- Spring Security for user authentication
- Spring MVC
- Spring Data JPA & Hibernate for data access
- Thymeleaf as a template engine
- MySQL JDBC driver

<br>

### Project Demonstration: 
Click the image below to watch the project demo
<br><br>
[<img src="demo-images/Project-poster.png" width="600"/>](https://youtu.be/MyX94dwdLTw "Click to Watch!")




### Front-end, légal et accessibilité (branche front-end)
- Design Apple appliqué avec des tokens CSS (`style.css`, `maintenance.css`), sections alternées `#ffffff` / `#f5f5f7`.
- Pages : mentions légales (`/mentions-legales`), politique de confidentialité (`/confidentialite`), conditions d'utilisation avec information sur le droit de rétractation de 14 jours (`/cgu`), politique cookies (`/cookies`), pages d'erreur 404 et 5xx personnalisées.
- Bandeau de consentement : « Tout refuser » et « Tout accepter » identiques (`consent.js`). Aucun traceur optionnel n'est utilisé.
- SEO : titres et descriptions par page, image de partage, favicon, `robots.txt`, `sitemap.xml` (à compléter avec le vrai domaine).
- Accessibilité : lien d'évitement, textes alternatifs, libellés de formulaires, focus visible, contrastes vérifiés (≥ 4,5:1).
- Sécurité : `/list`, `/edit/**`, `/delete/**`, `/edit-product/**`, `/delete-product/**` et l'ajout de produit exigent une connexion ; les suppressions se font en POST (jeton CSRF).
- Administrateur : le compte admin est créé au démarrage depuis les variables d'environnement `ADMIN_EMAIL` et `ADMIN_PASSWORD` (jamais dans le code), par exemple `ADMIN_EMAIL=admin@exemple.test ADMIN_PASSWORD='un-mot-de-passe-long' mvn spring-boot:run`. Seul l'admin accède à `/list` et gère utilisateurs et produits. Les inscrits ont le rôle `USER`, non modifiable depuis un formulaire.
- Production : `--spring.profiles.active=prod` active la redirection HTTPS (`site.force-https`) et le cookie de session `secure`.
- Les éléments marqués « À COMPLÉTER » (contact, hébergeur, domaine, source du logo) doivent être renseignés avant publication.

<br>

### References:
Images: https://shey.tistory.com/category/Wallpapers/%ED%8F%AC%EC%BC%93%EB%AA%AC%EC%8A%A4%ED%84%B0?page=2

Pokemon Descriptions: https://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_National_Pok%C3%A9dex_number
