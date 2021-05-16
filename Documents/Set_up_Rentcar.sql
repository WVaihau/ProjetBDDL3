use rentcar;

set autocommit=0;

-- AJOUT DE DONNEE

-- Type d'utilisateur 
INSERT INTO type_user (code_type_user, libelle_type_user) VALUES 
	("ADM", "Administrateur"),
    ("REC", "Receptionniste"),
    ("TRS", "Transporteur"),
    ("USR", "Utilisateur"),
    ("DEF", "Default");
    
SELECT * FROM type_user;

-- Marque de voiture
INSERT INTO marque (code_marque, libelle_marque) VALUES 
	("BMW", "BMW"),
    ("AUD", "AUDI"),
    ("TYT", "Toyota"),
    ("VSG", "Volkswagen"),
    ("MCD", "Mercedes"),
    ("LBG", "Lamborghini"),
    ("BGT", "Bugatti"),
    ("PCH", "Porsche");
    
SELECT * FROM marque;

-- Fonctions

-- Procédure stockée
