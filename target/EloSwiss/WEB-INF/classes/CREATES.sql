-- Create Table: Player
--------------------------------------------------------------------------------
CREATE TABLE Player
(
	ID INTEGER NOT NULL 
	,Name VARCHAR(250) NOT NULL 
	,Rating VARCHAR(250) NOT NULL 
	,CONSTRAINT PK_Player_ID PRIMARY KEY (ID)
);


-- Create Table: Format
--------------------------------------------------------------------------------
CREATE TABLE Format
(
	ID INTEGER NOT NULL 
	,Descriptor VARCHAR(250)  NULL 
	,CONSTRAINT PK_Format_ID PRIMARY KEY (ID)
);



-- Create Table: Tournament
--------------------------------------------------------------------------------
CREATE TABLE Tournament
(
	ID INTEGER NOT NULL 
	,Date TIMESTAMP NOT NULL 
	,Format INTEGER NOT NULL 
	,CONSTRAINT PK_Tournament_ID PRIMARY KEY (ID)
	,FOREIGN KEY(Format) REFERENCES Format(ID)
);


-- Create Table: Match
--------------------------------------------------------------------------------
CREATE TABLE Match
(
	ID INTEGER NOT NULL 
	,Player1 INTEGER NOT NULL 
	,Player2 INTEGER NOT NULL 
	,Player1NumWins INTEGER NOT NULL 
	,Player2NumWins INTEGER NOT NULL 
	,Tournament INTEGER NOT NULL 
	,RatingsDelta INTEGER NOT NULL 
	,CONSTRAINT PK_Match_ID PRIMARY KEY (ID)
	,FOREIGN KEY(Player1) REFERENCES Player(ID)
	,FOREIGN KEY(Player2) REFERENCES Player(ID)
	,FOREIGN KEY(Tournament) REFERENCES Tournament(ID)
);