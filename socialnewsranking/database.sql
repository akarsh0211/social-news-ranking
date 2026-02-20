-- PROJECT 1: Social Media News Ranking System

CREATE DATABASE IF NOT EXISTS newsdb;
USE newsdb;

CREATE TABLE IF NOT EXISTS posts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    likes INT,
    shares INT,
    comments INT
);

INSERT INTO posts (title, likes, shares, comments) VALUES
('AI Breakthrough', 120, 50, 30),
('Tech News Today', 90, 40, 20),
('New Java Update', 150, 70, 60);
