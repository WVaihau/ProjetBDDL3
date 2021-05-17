-- ============================================================
--
--   Nom du Cas       : rentcar
--   Nom de SGBD      : MySQL
--   Date de création : 17/05/2021
--   Auteur           : WILLIAMU Vaihau - ABDELLI Nina - SENNECHAEL Maxime | Groupe G - Projet L3 Database
--
-- ============================================================

drop database if exists rentcar;
create database rentcar;
use rentcar;

drop table if exists adresse;
drop table if exists programme_fidelite;
drop table if exists assurance;
drop table if exists agence;
drop table if exists marque;
drop table if exists categorie;
drop table if exists devis;
drop table if exists type_user;
drop table if exists utilisateur;
drop table if exists modele;
drop table if exists facture;
drop table if exists vehicule_location;
drop table if exists abonnement;
drop table if exists reservation;
drop table if exists vehicule_ramener;
drop table if exists demande_devis;

CREATE TABLE adresse(

   id_adresse INT AUTO_INCREMENT,

   rue VARCHAR(50),

   ville VARCHAR(50),

   code_postal VARCHAR(50),

   PRIMARY KEY(id_adresse)

) ENGINE=`InnoDB`;


CREATE TABLE programme_fidelite(

   id_programme_fidelite VARCHAR(4),

   libelle_prgm_fidelite VARCHAR(50),

   prix_prgm_fidelite DECIMAL(2,2),

   taux_reduc_prgm_fidelite DECIMAL(2,2),

   duree DATE,

   PRIMARY KEY(id_programme_fidelite)

) ENGINE=`InnoDB`;


CREATE TABLE assurance(

   id_assurance INT AUTO_INCREMENT,

   type_assurance VARCHAR(50),

   tarif_assurance DECIMAL(19,4),

   PRIMARY KEY(id_assurance)

) ENGINE=`InnoDB`;


CREATE TABLE agence(

   code_agence VARCHAR(4),

   agence_nom VARCHAR(50),

   altitude INT,

   longitude INT,

   latitude INT,

   id_adresse INT NOT NULL,

   PRIMARY KEY(code_agence),

   UNIQUE(id_adresse),

   FOREIGN KEY(id_adresse) REFERENCES adresse(id_adresse)

) ENGINE=`InnoDB`;


CREATE TABLE marque(

   code_marque VARCHAR(4),

   libelle_marque VARCHAR(50),

   PRIMARY KEY(code_marque)

) ENGINE=`InnoDB`;


CREATE TABLE categorie(

   code_categorie VARCHAR(4),

   libelle_categorie VARCHAR(50),

   tarif_journalier_categorie DECIMAL(2,2),

   caution_categorie INT,

   PRIMARY KEY(code_categorie)

) ENGINE=`InnoDB`;


CREATE TABLE devis(

   id_devis INT AUTO_INCREMENT,

   tarif_location DECIMAL(4,2),

   date_debut DATE,

   duree DATE,

   id_assurance INT,

   PRIMARY KEY(id_devis),

   FOREIGN KEY(id_assurance) REFERENCES assurance(id_assurance)

) ENGINE=`InnoDB`;


CREATE TABLE type_user(

   code_type_user VARCHAR(3),

   libelle_type_user VARCHAR(50),

   PRIMARY KEY(code_type_user)

) ENGINE=`InnoDB`;


CREATE TABLE utilisateur(

   id_user INT AUTO_INCREMENT,

   tel_user VARCHAR(50) NOT NULL,

   mail_user VARCHAR(50),

   mdp_user VARCHAR(50),

   nom_user VARCHAR(50),

   prenom_user VARCHAR(50),

   date_abo DATE,

   id_adresse INT NOT NULL,

   code_type_user VARCHAR(3) NOT NULL,

   PRIMARY KEY(id_user),

   UNIQUE(id_adresse),

   FOREIGN KEY(id_adresse) REFERENCES adresse(id_adresse),

   FOREIGN KEY(code_type_user) REFERENCES type_user(code_type_user)

) ENGINE=`InnoDB`;


CREATE TABLE modele(

   code_modele VARCHAR(3),

   libelle_modele VARCHAR(50),

   gearbox_type VARCHAR(50),

   climatisation BOOLEAN,

   fuel_type VARCHAR(50),

   code_marque VARCHAR(4) NOT NULL,

   PRIMARY KEY(code_modele),

   FOREIGN KEY(code_marque) REFERENCES marque(code_marque)

) ENGINE=`InnoDB`;


CREATE TABLE facture(

   id_facture INT AUTO_INCREMENT,

   cout_final DECIMAL(19,4),

   repair_cost DECIMAL(2,2),

   id_devis INT NOT NULL,

   PRIMARY KEY(id_facture),

   UNIQUE(id_devis),

   FOREIGN KEY(id_devis) REFERENCES devis(id_devis)

) ENGINE=`InnoDB`;


CREATE TABLE vehicule_location(

   matricule_location VARCHAR(50),

   kilometrage VARCHAR(50),

   statut BOOLEAN,

   code_agence VARCHAR(4) NOT NULL,

   code_categorie VARCHAR(4) NOT NULL,

   code_modele VARCHAR(3) NOT NULL,

   PRIMARY KEY(matricule_location),

   FOREIGN KEY(code_agence) REFERENCES agence(code_agence),

   FOREIGN KEY(code_categorie) REFERENCES categorie(code_categorie),

   FOREIGN KEY(code_modele) REFERENCES modele(code_modele)

) ENGINE=`InnoDB`;


CREATE TABLE abonnement(

   id_user INT,

   id_programme_fidelite VARCHAR(4) NOT NULL,

   PRIMARY KEY(id_user),

   FOREIGN KEY(id_user) REFERENCES utilisateur(id_user),

   FOREIGN KEY(id_programme_fidelite) REFERENCES programme_fidelite(id_programme_fidelite)

) ENGINE=`InnoDB`;


CREATE TABLE reservation(

   id_user INT,

   matricule_location VARCHAR(50),

   date_debut DATE,

   duree DATE,

   PRIMARY KEY(id_user, matricule_location),

   FOREIGN KEY(id_user) REFERENCES utilisateur(id_user),

   FOREIGN KEY(matricule_location) REFERENCES vehicule_location(matricule_location)

) ENGINE=`InnoDB`;


CREATE TABLE vehicule_ramener(

   matricule_location VARCHAR(50),

   date_retour DATE,

   etat_vehicule VARCHAR(50),

   duree_effective_location DATE,

   quantity_fuel_used DECIMAL(2,2),

   code_agence VARCHAR(4) NOT NULL,

   id_user_agent INT NOT NULL,

   id_user_client INT NOT NULL,

   id_facture INT NOT NULL,

   PRIMARY KEY(matricule_location),

   UNIQUE(code_agence),

   FOREIGN KEY(matricule_location) REFERENCES vehicule_location(matricule_location),

   FOREIGN KEY(code_agence) REFERENCES agence(code_agence),

   FOREIGN KEY(id_user_agent) REFERENCES utilisateur(id_user),

   FOREIGN KEY(id_user_client) REFERENCES utilisateur(id_user),

   FOREIGN KEY(id_facture) REFERENCES facture(id_facture)

) ENGINE=`InnoDB`;


CREATE TABLE demande_devis(

   id_user INT,

   matricule_location VARCHAR(50),

   id_devis INT,

   PRIMARY KEY(id_user, matricule_location, id_devis),

   FOREIGN KEY(id_user) REFERENCES utilisateur(id_user),

   FOREIGN KEY(matricule_location) REFERENCES vehicule_location(matricule_location),

   FOREIGN KEY(id_devis) REFERENCES devis(id_devis)

) ENGINE=`InnoDB`;
COMMIT;
