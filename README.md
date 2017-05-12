# Wildlife-Tracker-App
This is application that allows Rangers to track wildlife sightings in the area
# Author
Prof Alex Kinuthia
# Detailed setup instructions with all commands necessary to re-create your databases, columns, and tables 
CREATE DATABASE wildlife_tracker;


\c wildlife_tracker;


CREATE TABLE animals (id serial PRIMARY KEY, name varchar);


CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);


CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar);


CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
