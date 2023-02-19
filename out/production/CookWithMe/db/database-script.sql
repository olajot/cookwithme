create table if not exists label (
	id int auto_increment primary key,
	name varchar(50)
);

create table if not exists mealType (
	id int auto_increment primary key,
	name varchar(50)
);

CREATE table if not exists recipe (
	id int auto_increment,
	title varchar(255) not null,
	ingredientsDesc varchar(2000) not null,
	stepsDesc varchar(2000),
	preparationTime int default 0,
	createdDate date default(CURRENT_DATE),
	hidden boolean,
	primary key (id)
);


CREATE table if not exists labelledRecipe (
	id int auto_increment,
	recipeId int,
	labelId int,
	primary key (id),
	foreign key (recipeId)
		references recipe(id)
		on update restrict on DELETE restrict,
	foreign key (labelId)
		references label(id)
		on update restrict on DELETE restrict
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