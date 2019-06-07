DROP DATABASE IF EXISTS movieDB;

CREATE DATABASE movieDB DEFAULT CHARACTER SET 'utf8'
  DEFAULT COLLATE 'utf8_unicode_ci';

USE movieDB;

CREATE TABLE actor(
    actorId   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(20) NOT NULL,
    lastName  VARCHAR(20) NOT NULL,
    birthDate DATE NOT NULL, 	
    CONSTRAINT actor_unique UNIQUE (firstName, lastName),
    INDEX actor_name  (firstName, lastName)
) ENGINE = InnoDB;

CREATE TABLE director(
    directorId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    firstName  VARCHAR(20) NOT NULL,
    lastName   VARCHAR(20) NOT NULL,
    birthDate  DATE NOT NULL,
    CONSTRAINT director_unique UNIQUE (firstName, lastName),
    INDEX director_name  (firstName, lastName)	
) ENGINE = InnoDB;

CREATE TABLE movie(    
    movieId     INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(50) NOT NULL,
    releaseDate DATE NOT NULL,
    directorId  INT NOT NULL, 
    runningTime INT,
    CONSTRAINT  movie_unique UNIQUE (title, releaseDate),
    FOREIGN KEY (directorId) REFERENCES director (directorId) ON DELETE CASCADE,
    INDEX movie_index (title, releaseDate) 
) ENGINE = InnoDB;

CREATE TABLE actorFilm(
    actorId INT NOT NULL,
    filmId  INT NOT NULL,
    FOREIGN KEY (filmId) REFERENCES movie (movieId) ON DELETE CASCADE,
    FOREIGN KEY (actorId) REFERENCES actor (actorId) ON DELETE CASCADE,
    CONSTRAINT actorFilm_unique UNIQUE (actorId, filmId) 
) ENGINE = InnoDB; 

CREATE TABLE actorImages(
    imageId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    data blob NOT NULL,
    actorId INT NOT NULL, 
    FOREIGN KEY (actorId) REFERENCES actor (actorId) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE UserPrincipal (
  UserId BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Username VARCHAR(30) NOT NULL,
  HashedPassword BINARY(60) NOT NULL,
  AccountNonExpired BOOLEAN NOT NULL,
  AccountNonLocked BOOLEAN NOT NULL,
  CredentialsNonExpired BOOLEAN NOT NULL,
  Enabled BOOLEAN NOT NULL,
  CONSTRAINT user_unique UNIQUE (Username)
) ENGINE = InnoDB;


INSERT INTO actor VALUES (
  '100', 'George', 'Clooney', '1961-05-06'
);

INSERT INTO actor VALUES (
  '101', 'Brad', 'Pitt', '1963-12-18'
);

INSERT INTO actor VALUES (
  '102', 'Tom', 'Cruise', '1962-07-03'
);

INSERT INTO actor VALUES (
  '103', 'Matt', 'Damon', '1970-10-08'
);

INSERT INTO actor VALUES (
  '104', 'James', 'Dean', '1931-02-08'
);

INSERT INTO actor VALUES (
  '105', 'Sean', 'Penn', '1960-08-17'
);

INSERT INTO actor VALUES (
  '106', 'Sean', 'Connery', '1930-08-25'
);

INSERT INTO actor VALUES (
  '107', 'Ben', 'Affleck', '1972-08-15'
);

INSERT INTO actor VALUES (
  '108', 'Ben', 'Kingsley', '1943-12-31'
);

INSERT INTO actor VALUES (
  '109', 'Orlando', 'Bloom', '1977-01-13'
);

INSERT INTO actor VALUES (
  '110', 'Angelina', 'Jolie', '1975-06-04'
);

INSERT INTO actor VALUES (
  '111', 'Will', 'Smith', '1968-09-25'
);

INSERT INTO actor VALUES (
  '112', 'Bill', 'Pullmann', '1953-12-16'
);

INSERT INTO actor VALUES (
  '113', 'Channing', 'Tatum', '1980-04-26'
);

INSERT INTO actor VALUES (
  '114', 'Jamie', 'Foxx', '1967-12-13'
);

INSERT INTO actor VALUES (
  '115', 'Halle', 'Berry', '1966-08-14'
);

INSERT INTO actor VALUES (
  '116', 'Daniel', 'Craig', '1968-03-02'
);


INSERT INTO director VALUES (
  '1', 'Brian', 'De Palma', '1940-09-11'
);

INSERT INTO director VALUES (
  '2', 'Marc', 'Forster', '1969-11-30'
);

INSERT INTO director VALUES (
  '3', 'Wolfgang', 'Petersen', '1941-03-14'
);

INSERT INTO director VALUES (
  '4', 'Roland', 'Emmerich', '1955-11-10'
);

INSERT INTO director VALUES (
  '5', 'Josh', 'Trank', '1984-02-19'
);

INSERT INTO director VALUES (
  '6', 'Tim', 'Story', '1970-03-13'
);

INSERT INTO director VALUES (
  '7', 'David', 'Fincher', '1962-08-18'
);

INSERT INTO director VALUES (
  '8', 'Doug', 'Liman', '1965-07-24'
);




INSERT INTO movie VALUES (
  '1', 'Mission: impossible', '1996-05-22', '1', '110' 
);

INSERT INTO movie VALUES (
  '2', 'Quantum of Solace', '2008-10-29', '2', '106' 
);

INSERT INTO movie VALUES (
  '3', 'Troy', '2004-05-09', '3', '196' 
);

INSERT INTO movie VALUES (
  '4', 'Fantastic Four', '2005-07-08', '6', '106' 
);

INSERT INTO movie VALUES (
  '5', 'Fantastic Four', '2015-08-04', '5', '100' 
);

INSERT INTO movie VALUES (
  '6', 'Fight Club', '1999-11-10', '7', '151' 
);

INSERT INTO movie VALUES (
  '7', 'Mr & Mrs Smith', '2005-06-10', '8', '122' 
);

INSERT INTO movie VALUES (
  '8', 'Independence Day', '1996-09-27', '4', '153' 
);

INSERT INTO movie VALUES (
  '9', 'White House Down', '2013-06-28', '4', '131' 
);

INSERT INTO movie VALUES (
  '10', 'Monster Ball', '2002-03-07', '2', '111' 
);



INSERT INTO actorFilm VALUES (
  '101', '3' 
);

INSERT INTO actorFilm VALUES (
  '102', '6'
);

INSERT INTO actorFilm VALUES (
  '101', '7'
);

INSERT INTO actorFilm VALUES (
  '110', '7'
);

INSERT INTO actorFilm VALUES (
  '111', '8' 
);

INSERT INTO actorFilm VALUES (
  '112', '8' 
);

INSERT INTO actorFilm VALUES (
  '113', '9'
);

INSERT INTO actorFilm VALUES (
  '114', '9' 
);

INSERT INTO actorFilm VALUES (
  '115', '10' 
);

