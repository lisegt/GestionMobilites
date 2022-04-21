--Etudiants
INSERT INTO ETUDIANT (nom, num_etud, prenom, promo) VALUES
    ('LAMULA',1,'Kilian',2024),
    ('BENCHAREF',2,'Rayane',2024),
    ('MATTON',3,'Hugo',2024),
    ('GAUTHIER',4,'Lise', 2024),
    ('VILLEDIEU',5,'Baptiste',2023),
    ('GAY',6,'Colin',2023),
    ('BONNAFOUS',7,'Arnaud',2024),
    ('BOUABOUB',8,'Wiame',2024),
    ('DUPEYROUX',9,'Lisa',2024),
    ('CHABBERT',10,'Axel',2025);

--Destionations
INSERT INTO DESTINATION (date_fin_de_contrat_isis,image,nb_place_annee,nb_place_semestre,nom_etablissement_accueil,type_mobilite,ville,pays) VALUES
    ('2025-02-02', '', 4, 2, 'Artic University Of Norway', 2,'Tromso','Norvege'),
    ('2025-07-01', '', 8, 4, 'Université Rey Juan Carlos', 2,'Madrid','Espagne'),
    ('2025-07-01', '', 4, 2, 'University of Applied Sciences', 2,'Krefeld','Allemagne'),
    ('2026-11-01', '', 2, 1, 'Universidad de Antioquia', 1,'Medellin','Colombie'),
    ('2024-06-12', '', 2, 1, 'Alice Project', 3,'Bodhgaya','Inde'),
    ('2025-04-10', '', 5, 1, 'Universitat Politecnica de Mataro', 3,'Mataro','Espagne'),
    ('2022-09-02', '', 6, 3, 'European University Of Cyprus', 2,'Nicosia','Chypre'),
    ('2026-09-03', '', 2, 1, 'Saint Francis University', 2,'Loretto - Pensylvanie','Etats-Unis');

--Mobilités
INSERT INTO MOBILITE (date_depart, duree_en_mois, periode, destination_id, etudiant_id) VALUES
    ('2023-02-22', 5, 1, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'European University Of Cyprus', SELECT ID FROM ETUDIANT WHERE nom = 'BENCHAREF' ),
    ('2023-02-22', 5, 1, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'European University Of Cyprus', SELECT ID FROM ETUDIANT WHERE nom = 'MATTON' ),
    ('2023-02-22', 5, 1, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'European University Of Cyprus', SELECT ID FROM ETUDIANT WHERE nom = 'LAMULA' ),
    ('2022-08-16', 5, 0, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'Artic University Of Norway', SELECT ID FROM ETUDIANT WHERE nom = 'GAUTHIER' ),
    ('2022-05-08', 2, 1, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'Alice Project', SELECT ID FROM ETUDIANT WHERE nom = 'DUPEYROUX' ),
    ('2022-09-02', 2, 0, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'Universitat Politecnica de Mataro', SELECT ID FROM ETUDIANT WHERE nom = 'BONNAFOUS' ),
    ('2019-04-10', 5, 0, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'Artic University Of Norway', SELECT ID FROM ETUDIANT WHERE nom = 'VILLEDIEU' ),
    ('2022-09-10', 5, 2, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'Saint Francis University', SELECT ID FROM ETUDIANT WHERE nom = 'CHABBERT' );

--Documents administratifs
INSERT INTO DOCUMENT (description, fichier, intitule) VALUES
    ('Permet au candidat de se singulariser, de retenir l''attention du recruteur et de décrocher un entretien pour un poste', 'fichier','Dossier de candidature 2022/2023'),
    ('Contrat de travail qui permet à l''étudiant d''exercer un emploi et de financer ses études', 'fichier','Contrat d''étude 2022'),
    ('Attestation à remplir à l''arrivée sur le lieu de stage à envoyer au maximum 10 jours après', 'fichier', 'Attestation d''arrivée'),
    ('Attestation à remplir à la fin de la mobilité', 'fichier', 'Attestation de fin de séjour');

--Identifiant, email et mot de passe administrateur
INSERT INTO USERS (username, email, password) VALUES 
    ('admin','admin@mail.com','$2y$10$6aP8DVtO4oEFuJ2gFjNjH.Zz8JvSaQG2wZlL7stsmdI23Iz0cRFPS');

--Rôles
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO USER_ROLES VALUES 
(SELECT id FROM users WHERE username = 'admin', SELECT id FROM roles WHERE name = 'ROLE_USER' ),
(SELECT id FROM users WHERE username = 'admin', SELECT id FROM roles WHERE name = 'ROLE_MODERATOR'),
(SELECT id FROM users WHERE username = 'admin', SELECT id FROM roles WHERE name = 'ROLE_ADMIN');