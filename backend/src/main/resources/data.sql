INSERT INTO ETUDIANT (nom, num_etud, prenom, promo) VALUES
    ( 'GAUTHIER',4,'Lise', 2024),
    ('MATTON',3,'Hugo',2024),
    ('BEN',2,'Rayane',2024),
    ('LAMULA',1,'Kilian',2024);

INSERT INTO LOCALISATION (nom_pays, nom_ville) VALUES
    ('Norvège', 'Tromso'),
    ('Chypre', 'Nicosia');

INSERT INTO DESTINATION (date_fin_de_contrat_isis,image,nb_place_annee,nb_place_semestre,nom_etablissement_accueil,type_mobilite, localisation_id) VALUES
    ('2025-02-02', 'image', 4, 2, 'Artic University', 1,SELECT id FROM LOCALISATION WHERE nom_ville = 'Tromso'),
    ('2025-02-02', 'image', 4, 2, 'European University Of Cyprus', 1,SELECT id FROM LOCALISATION WHERE nom_ville = 'Nicosia');

INSERT INTO MOBILITE (date_depart, duree_en_mois, periode, destination_id, etudiant_id) VALUES
    ('2023-04-10', 5, 1, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'European University Of Cyprus', SELECT ID FROM ETUDIANT WHERE nom = 'BEN' );

INSERT INTO DOCUMENT (description, fichier, intitule) VALUES
    ('Meilleur fichier ever', 'fichier','Le fichier a valider avec validation');



