CREATE TABLE if NOT EXISTS label (
	ID int auto_increment PRIMARY KEY,
	NAME varchar(50)
);

create table if not exists mealType (
	id int auto_increment primary key,
	name varchar(50)
);

CREATE TABLE if NOT EXISTS recipe (
	ID int auto_increment,
	title varchar(255) NOT NULL,
	ingredientsDesc varchar(2000) NOT NULL,
	stepsDesc varchar(2000),
	preparationTime int DEFAULT 0,
	createdDate date DEFAULT(CURRENT_DATE),
	hidden boolean,
	PRIMARY KEY (ID)
);


CREATE TABLE if NOT EXISTS labelledRecipe (
	ID int auto_increment,
	recipeId int,
	labelId int,
	PRIMARY KEY (ID),
	FOREIGN KEY (recipeId)
		REFERENCES recipe(ID)
		ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (labelId)
		REFERENCES label(ID)
		ON UPDATE RESTRICT ON DELETE RESTRICT
);

create table if not exists recipeMealType (
	id int auto_increment,
	recipeId int,
	mealTypeId int,
	primary key (id),
	foreign key (recipeId)
		references recipe(id)
		on update restrict on DELETE restrict,
	foreign key (mealTypeId)
		references mealType(id)
		on update restrict on DELETE restrict

);