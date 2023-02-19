insert into label(name) values ('pineapple');
insert into label(name) values ('carrot');
insert into label(name) values ('cucumber');
insert into label(name) values ('vegan cheese');
insert into label(name) values ('tomatoes');
insert into label(name) values ('broccoli');
insert into label(name) values ('cilantro');
insert into label(name) values ('bell pepper');
insert into label(name) values ('onions');
insert into label(name) values ('chocolate');
insert into label(name) values ('basil');
insert into label(name) values ('chickpeas');
insert into label(name) values ('spicy');
insert into label(name) values ('noodles');
insert into label(name) values ('tofu');
insert into label(name) values ('garlic');
insert into label(name) values ('cherries');


insert into mealType(name) values ('breakfast');
insert into mealType(name) values ('dinner');
insert into mealType(name) values ('lunch');
insert into mealType(name) values ('snack');
insert into mealType(name) values ('cake');
insert into mealType(name) values ('holidays');
insert into mealType(name) values ('party');
insert into mealType(name) values ('dessert');


insert into recipe (title, ingredientsDesc, stepsDesc, preparationTime, hidden)
			values ('Tomato and basil soup', '12 tomatoes, salt, pepper, garlic, olive oil, onion, fresh basil',
			'1. Sautee onion, gralic and seasoning in olive oil. 2. Add chopped tomatoes. 3. Cook for 30 minutes. 4. Top with oil and basil. 5. Sautee onion, gralic and seasoning in olive oil. 6. Add chopped tomatoes. 7. Cook for 30 minutes. 8. Top with oil and basil. ',90,false);
insert into recipeMealType (recipeId, mealTypeId) values (1,2);
insert into labelledRecipe  (recipeId, labelId) values (1,4);
insert into labelledRecipe (recipeId, labelId) values (1,11);
insert into labelledRecipe (recipeId, labelId) values (1,16);


insert into recipe (title, ingredientsDesc, stepsDesc, preparationTime, hidden)
			values ('Tofu stir-fry', '1 block of tofu, 1/2 bell pepper, 1 small tomato, 3tsp ketchap manis, 1tsp sambal oelek, 300g noodles, 2tsp honey, salt, coconut oil' ,
		'1. Cook the noodles. 2.  Bake the tofu. 3. Sautee the bell pepper with salt in coconut oil until soft. 4. Add sauce, cooked noodles & tofu. ', 15, true);
insert into recipeMealType (recipeId, mealTypeId) values (2,3);
insert into labelledRecipe (recipeId, labelId) values (2,15);
insert into labelledRecipe (recipeId, labelId) values (2,13);
insert into labelledRecipe (recipeId, labelId) values (2,5);
insert into labelledRecipe (recipeId, labelId) values (2,14);
insert into labelledRecipe (recipeId, labelId) values (2,16);


insert into recipe (title, ingredientsDesc, stepsDesc, preparationTime, hidden)
			values ('Chocolate cherry cookies', '100g chocolate, handful of dried cherries, 50g vegan butter, 3tsp agave syrup, 1tsp oats, 90ml soy milk, pinch of salt, 300g flour' ,
		'1. Melt the chocolate. 2. Combine all ingredients in a bowl.. 3. Form cookies. 4. Bake. ', 60, false);
insert into recipeMealType (recipeId, mealTypeId) values (3,8);
insert into labelledRecipe (recipeId, labelId) values (3, 10);
insert into labelledRecipe (recipeId, labelId) values (3, 17);


insert into recipe (title, ingredientsDesc, stepsDesc, preparationTime, hidden)
			values ('Vegan soup', 'tralala',
			'1. krok, gralic and seasoning in olive oil. 2. Add chopped tomatoes. 3. Cook for 30 minutes. 4. Top with oil and basil.',40,false);
insert into recipeMealType (recipeId, mealTypeId) values (4,2);
insert into labelledRecipe  (recipeId, labelId) values (4,4);
insert into labelledRecipe (recipeId, labelId) values (4,11);
insert into labelledRecipe (recipeId, labelId) values (4,16);


insert into recipe (title, ingredientsDesc, stepsDesc, preparationTime, hidden)
			values ('Orange Tofu stir-fry', '1 block of tofu, 1/2 bell pepper, 1 small tomato, 3tsp ketchap manis, 1tsp sambal oelek, 300g noodles, 2tsp honey, salt, coconut oil' ,
		'1. Cook the noodles. 2.  Bake the tofu. 3. Sautee the bell pepper with salt in coconut oil until soft. 4. Add sauce, cooked noodles & tofu. ', 25, false);
insert into recipeMealType (recipeId, mealTypeId) values (5,3);
insert into labelledRecipe (recipeId, labelId) values (5,15);
insert into labelledRecipe (recipeId, labelId) values (5,13);
insert into labelledRecipe (recipeId, labelId) values (5,5);
insert into labelledRecipe (recipeId, labelId) values (5,14);


insert into recipe (title, ingredientsDesc, stepsDesc, preparationTime, hidden)
			values ('Syrniczki', '100g chocolate, handful of dried cherries, 50g vegan butter, 3tsp agave syrup, 1tsp oats, 90ml soy milk, pinch of salt, 300g flour' ,
		'1. Melt the chocolate. 2. Combine all ingredients in a bowl.. 3. Form cookies. 4. Bake. ', 31, false);
insert into recipeMealType (recipeId, mealTypeId) values (6,8);
insert into labelledRecipe (recipeId, labelId) values (6, 10);
insert into labelledRecipe (recipeId, labelId) values (6, 17);
insert into labelledRecipe (recipeId, labelId) values (6, 16);