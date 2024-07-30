
# DVote-India (Java Version)

DVote-India is a Java-Based digital Voting System designed to provide a secure and transparent platform for conducting elections. This project represents a simplified version of our original Blockchain-based project, which uses Solidity for enhanced security and transparency.

## Features

- **Admin Portal** ```adminLogin``` : 
  - Add candidate details.
  - Add voter details.
  - Start and End elections.

- **Voter Portal**:
  - Voter authentication using Aadhar and Mobile Number.
  - View candidate list for the Voter's Voting region.
  - Cast vote securely.

- **Database Management**:
  - MySQL database setup with constraints to ensure data integrity.
  - Transaction management during voting to ensure consistency.

## Project Structure

- **Main.java**: Entry point of the application, handles user navigation.
- **Admins.java**: Manages admin operations like adding candidates and voters, and managing election status.
- **VotersLogin.java**: Handles voter login and authentication.
- **Voting.java**: Manages the voting process, including displaying candidates and updating vote counts.

## Setup and Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repository/DVote-India-Java.git
   ```

2. **Set up the database**:
   - Install MySQL and create a database named `DVoteIndia`.
   - Run the SQL script provided below to set up the tables and initial data.

3. **Configure the project**:
   - Update the database connection details in the Java files as needed.

4. **Run the application**:
   - Compile and run the `Main.java` file.

## SQL Script

Here's the SQL script to set up the database:

```sql
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
```

## Link to Original Blockchain-based Project

This Java version is a simplified adaptation of our original DVote-India project, developed using Solidity and Blockchain Technology (I just made few backend edits via Solidity and had work in Ideating, and Resolving the Issues in the Initial stages of Code).

You can find the original project here: [DVote-India (Solidity Version)](https://github.com/Th3C0d3Mast3r/DVote-India.git).

## Contributing

We welcome contributions to improve this project. Please feel free to fork the repository, make changes, and submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- **Original Team**: My Team Members who developed the Blockchain-based version of DVote-India for the Community Project.

---
