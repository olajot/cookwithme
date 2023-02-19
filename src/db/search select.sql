select  DISTINCT  r.title , r.preparationTime , mt.name /*, l.name */  from recipe r
left join recipeMealType rmt on rmt.recipeId = r.id
left join mealType mt on mt.id  = rmt.mealTypeId
left join labelledRecipe lr on lr.recipeId = r.id
left join label l on l.id = lr.labelId
where r.hidden = 0
and l.name in ('tomatoes')
and mt.name = 'lunch'
and preparationTime BETWEEN 1 and 100




select * from recipe r where r.id = 4;