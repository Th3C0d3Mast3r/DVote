CREATE DATABASE IF NOT EXISTS DVoteIndia;
USE DVoteIndia;

CREATE TABLE IF NOT EXISTS candidateDatabase (
    candidateID INT AUTO_INCREMENT PRIMARY KEY,
    regionCode VARCHAR(6),
    candidateName VARCHAR(30),
    candidateParty VARCHAR(10),
    UNIQUE (regionCode, candidateParty)  -- Ensures unique party per region
);

CREATE TABLE IF NOT EXISTS voterDatabase (
    voterName VARCHAR(30),
    aadharNumber VARCHAR(15) PRIMARY KEY,
    mobNo VARCHAR(11),
    regionCode VARCHAR(6),
    voteStatus ENUM("VOTED", "NOT VOTED"),
    voteDate DATE,
    voteTime TIME
);

CREATE TABLE IF NOT EXISTS votingDatabase (
    regionCode VARCHAR(6),
    partyName VARCHAR(10),
    totalVotes INT
);

CREATE TABLE IF NOT EXISTS VotingStatuss (
    id INT AUTO_INCREMENT PRIMARY KEY,
    votingStatus ENUM("ON", "END")
);
INSERT INTO VotingStatuss (votingStatus) VALUES ('END');

-- Viewing all the tables
SELECT * FROM candidateDatabase;
SELECT * FROM voterDatabase;
SELECT * FROM votingDatabase;
SELECT * FROM VotingStatuss;

-- Deleting and emptying the tables for a new Election
SET sql_safe_updates=0;
DELETE FROM candidateDatabase;
DELETE FROM voterDatabase;
DELETE FROM votingDatabase;
DELETE FROM VotingStatuss;