-- ============================================================
--
--   Nom du Cas       :  Rentcar
--   Nom de SGBD      :  MySQL 
--   Date de création :  Mai 2021 
--   Auteur           :  WILLIAMU Vaihau - ABDELLI Nina - SENNECHAEL Maxime | Groupe G - Projet L3 Database
--   
-- ============================================================
drop database if exists rentcar;
create database rentcar;
use rentcar;

drop table if exists addresse;
drop table if exists programme_fidelite;
drop table if exists assurance;
drop table if exists agence;
drop table if exists marque;
drop table if exists categorie;
drop table if exists vehicule_transport;
drop table if exists devis;
drop table if exists utilisateur;
drop table if exists client;
drop table if exists receptionniste;
drop table if exists modele;
drop table if exists circuit;
drop table if exists facture;
drop table if exists transporteur;
drop table if exists vehicule_location;
drop table if exists abonnement;
drop table if exists vehicule_to_move;
drop table if exists composition_circuit;
drop table if exists reservation;
drop table if exists vehicule_ramener;
drop table if exists demande_devis;

CREATE TABLE addresse(
   id_addresse INT AUTO_INCREMENT,
   rue VARCHAR(50),
   ville VARCHAR(50),
   code_postal VARCHAR(50),
   PRIMARY KEY(id_addresse)
) ENGINE='InnoDB';

CREATE TABLE programme_fidelite(
   id_programme_fidelite VARCHAR(4),
   libelle_prgm_fidelite VARCHAR(50),
   prix_prgm_fidelite DECIMAL(2,2),
   taux_reduc_prgm_fidelite DECIMAL(2,2),
   PRIMARY KEY(id_programme_fidelite)
) ENGINE='InnoDB';

CREATE TABLE assurance(
   id_assurance INT AUTO_INCREMENT,
   type_assurance VARCHAR(50),
   tarif_assurance DECIMAL(19,4),
   PRIMARY KEY(id_assurance)
) ENGINE='InnoDB';

CREATE TABLE agence(
   code_agence VARCHAR(4),
   agence_nom VARCHAR(50),
   altitude INT,
   longitude INT,
   latitude INT,
   fk_id_addresse INT NOT NULL,
   PRIMARY KEY(code_agence),
   FOREIGN KEY(fk_id_addresse) REFERENCES addresse(id_addresse)
) ENGINE='InnoDB';

CREATE TABLE marque(
   code_marque VARCHAR(4),
   libelle_marque VARCHAR(50),
   PRIMARY KEY(code_marque)
) ENGINE='InnoDB';

CREATE TABLE categorie(
   code_categorie VARCHAR(4),
   libelle_categorie VARCHAR(50),
   tarif_journalier_categorie DECIMAL(2,2),
   caution_categorie INT,
   PRIMARY KEY(code_categorie)
) ENGINE='InnoDB';

CREATE TABLE vehicule_transport(
   matricule_vehicule_transport VARCHAR(50),
   capacity_vehicule_transport INT,
   PRIMARY KEY(matricule_vehicule_transport)
) ENGINE='InnoDB';

CREATE TABLE devis(
   id_devis INT AUTO_INCREMENT,
   tarif_location DECIMAL(4,2),
   date_debut DATE,
   fk_id_assurance INT,
   PRIMARY KEY(id_devis),
   FOREIGN KEY(fk_id_assurance) REFERENCES assurance(id_assurance)
) ENGINE='InnoDB';

CREATE TABLE utilisateur(
   mail_user VARCHAR(50),
   mdp_user VARCHAR(50),
   tel_user VARCHAR(50) NOT NULL,
   nom_user VARCHAR(50),
   prenom_user VARCHAR(50),
   fk_id_addresse INT NOT NULL,
   PRIMARY KEY(mail_user, mdp_user),
   FOREIGN KEY(fk_id_addresse) REFERENCES addresse(id_addresse)
) ENGINE='InnoDB';

CREATE TABLE client(
   fk_mail_user VARCHAR(50),
   fk_mdp_user VARCHAR(50),
   PRIMARY KEY(fk_mail_user, fk_mdp_user),
   FOREIGN KEY(fk_mail_user, fk_mdp_user) REFERENCES utilisateur(mail_user, mdp_user)
) ENGINE='InnoDB';

CREATE TABLE receptionniste(
   fk_mail_user VARCHAR(50),
   fk_mdp_user VARCHAR(50),
   login_receptionniste VARCHAR(50),
   PRIMARY KEY(fk_mail_user, fk_mdp_user),
   FOREIGN KEY(fk_mail_user, fk_mdp_user) REFERENCES utilisateur(mail_user, mdp_user)
) ENGINE='InnoDB';

CREATE TABLE modele(
   code_modele VARCHAR(3),
   libelle_modele VARCHAR(50),
   gearbox_type VARCHAR(50),
   climatisation BOOLEAN,
   fuel_type VARCHAR(50),
   fk_code_marque VARCHAR(4) NOT NULL,
   PRIMARY KEY(code_modele),
   FOREIGN KEY(fk_code_marque) REFERENCES marque(code_marque)
) ENGINE='InnoDB';

CREATE TABLE circuit(
   id_circuit INT AUTO_INCREMENT,
   fk_matricule_vehicule_transport VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_circuit),
   UNIQUE(fk_matricule_vehicule_transport),
   FOREIGN KEY(fk_matricule_vehicule_transport) REFERENCES vehicule_transport(matricule_vehicule_transport)
) ENGINE='InnoDB';

CREATE TABLE facture(
   id_facture INT AUTO_INCREMENT,
   cout_final DECIMAL(19,4),
   repair_cost DECIMAL(2,2),
   fk_id_devis INT NOT NULL,
   PRIMARY KEY(id_facture),
   UNIQUE(fk_id_devis),
   FOREIGN KEY(fk_id_devis) REFERENCES devis(id_devis)
) ENGINE='InnoDB';

CREATE TABLE transporteur(
   fk_mail_user VARCHAR(50),
   fk_mdp_user VARCHAR(50),
   login_transporteur VARCHAR(50),
   fk_id_circuit INT NOT NULL,
   PRIMARY KEY(fk_mail_user, fk_mdp_user),
   FOREIGN KEY(fk_mail_user, fk_mdp_user) REFERENCES utilisateur(mail_user, mdp_user),
   FOREIGN KEY(fk_id_circuit) REFERENCES circuit(id_circuit)
) ENGINE='InnoDB';

CREATE TABLE vehicule_location(
   matricule_location VARCHAR(50),
   kilometrage VARCHAR(50),
   statut BOOLEAN,
   fk_code_agence VARCHAR(4) NOT NULL,
   fk_code_categorie VARCHAR(4) NOT NULL,
   fk_code_modele VARCHAR(3) NOT NULL,
   PRIMARY KEY(matricule_location),
   FOREIGN KEY(fk_code_agence) REFERENCES agence(code_agence),
   FOREIGN KEY(fk_code_categorie) REFERENCES categorie(code_categorie),
   FOREIGN KEY(fk_code_modele) REFERENCES modele(code_modele)
) ENGINE='InnoDB';

CREATE TABLE abonnement(
   fk_mail_user VARCHAR(50),
   fk_mdp_user VARCHAR(50),
   fk_id_programme_fidelite VARCHAR(4),
   date_debut DATE,
   date_fin DATE,
   PRIMARY KEY(fk_mail_user, fk_mdp_user, fk_id_programme_fidelite),
   FOREIGN KEY(fk_mail_user, fk_mdp_user) REFERENCES client(fk_mail_user, fk_mdp_user),
   FOREIGN KEY(fk_id_programme_fidelite) REFERENCES programme_fidelite(id_programme_fidelite)
) ENGINE='InnoDB';

CREATE TABLE vehicule_to_move(
   fk_matricule_location VARCHAR(50),
   fk_id_circuit INT,
   PRIMARY KEY(fk_matricule_location, fk_id_circuit),
   FOREIGN KEY(fk_matricule_location) REFERENCES vehicule_location(matricule_location),
   FOREIGN KEY(fk_id_circuit) REFERENCES circuit(id_circuit)
) ENGINE='InnoDB';

CREATE TABLE composition_circuit(
   fk_code_agence VARCHAR(4),
   fk_id_circuit INT,
   PRIMARY KEY(fk_code_agence, fk_id_circuit),
   FOREIGN KEY(fk_code_agence) REFERENCES agence(code_agence),
   FOREIGN KEY(fk_id_circuit) REFERENCES circuit(id_circuit)
) ENGINE='InnoDB';

CREATE TABLE reservation(
   fk_mail_user VARCHAR(50),
   fk_mdp_user VARCHAR(50),
   fk_mail_user_1 VARCHAR(50),
   fk_mdp_user_1 VARCHAR(50),
   fk_matricule_location VARCHAR(50),
   date_debut DATE,
   duree DATE,
   PRIMARY KEY(fk_mail_user, fk_mdp_user, fk_mail_user_1, fk_mdp_user_1, fk_matricule_location),
   FOREIGN KEY(fk_mail_user, fk_mdp_user) REFERENCES client(fk_mail_user, fk_mdp_user),
   FOREIGN KEY(fk_mail_user_1, fk_mdp_user_1) REFERENCES receptionniste(fk_mail_user, fk_mdp_user),
   FOREIGN KEY(fk_matricule_location) REFERENCES vehicule_location(matricule_location)
) ENGINE='InnoDB';

CREATE TABLE vehicule_ramener(
   fk_matricule_location VARCHAR(50),
   date_retour DATE,
   etat_vehicule VARCHAR(50),
   duree_effective_location DATE,
   quantity_fuel_used DECIMAL(2,2),
   fk_code_agence VARCHAR(4) NOT NULL,
   fk_mail_user VARCHAR(50) NOT NULL,
   fk_mdp_user VARCHAR(50) NOT NULL,
   fk_mail_user_1 VARCHAR(50) NOT NULL,
   fk_mdp_user_1 VARCHAR(50) NOT NULL,
   fk_id_facture INT NOT NULL,
   PRIMARY KEY(fk_matricule_location),
   UNIQUE(fk_code_agence),
   FOREIGN KEY(fk_matricule_location) REFERENCES vehicule_location(matricule_location),
   FOREIGN KEY(fk_code_agence) REFERENCES agence(code_agence),
   FOREIGN KEY(fk_mail_user, fk_mdp_user) REFERENCES client(fk_mail_user, fk_mdp_user),
   FOREIGN KEY(fk_mail_user_1, fk_mdp_user_1) REFERENCES receptionniste(fk_mail_user, fk_mdp_user),
   FOREIGN KEY(fk_id_facture) REFERENCES facture(id_facture)
) ENGINE='InnoDB';

CREATE TABLE demande_devis(
   fk_mail_user VARCHAR(50),
   fk_mdp_user VARCHAR(50),
   fk_mail_user_1 VARCHAR(50),
   fk_mdp_user_1 VARCHAR(50),
   fk_matricule_location VARCHAR(50),
   fk_id_devis INT,
   PRIMARY KEY(fk_mail_user, fk_mdp_user, fk_mail_user_1, fk_mdp_user_1, fk_matricule_location, fk_id_devis),
   FOREIGN KEY(fk_mail_user, fk_mdp_user) REFERENCES client(fk_mail_user, fk_mdp_user),
   FOREIGN KEY(fk_mail_user_1, fk_mdp_user_1) REFERENCES receptionniste(fk_mail_user, fk_mdp_user),
   FOREIGN KEY(fk_matricule_location) REFERENCES vehicule_location(matricule_location),
   FOREIGN KEY(fk_id_devis) REFERENCES devis(id_devis)
) ENGINE='InnoDB';


COMMIT;