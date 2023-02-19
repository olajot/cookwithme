
select * from label l ;

select id from mealType mt where mt.name = 'snack';

select * from recipe;

select * from labelledRecipe;

select * from recipeMealType;


select  DISTINCT  r.title , r.preparationTime , mt.name , l.name  from recipe r
left join recipeMealType rmt on rmt.recipeId = r.id
left join mealType mt on mt.id  = rmt.mealTypeId
left join labelledRecipe lr on lr.recipeId = r.id
left join label l on l.id = lr.labelId
where r.hidden = 0
and r.id = 51


select  l.name  from labelledRecipe lr
left join label l on l.id  = lr.labelId
where  lr.recipeId  = 53

SELECT id, title, ingredientsDesc, stepsDesc, preparationTime , hidden  FROM recipe r


select mt.name from recipeMealType rmt
left join mealType mt on mt.id  = rmt.mealTypeId
where rmt.recipeId  = 53

