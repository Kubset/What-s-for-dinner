SELECT * FROM main_dish;
SELECT * FROM soup;
SELECT * FROM dish_components;
SELECT * FROM soup_components;
SELECT * FROM components;

SELECT main_dish.name, components.name FROM main_dish
INNER JOIN dish_components ON main_dish.dish_id = dish_components.dish_id
INNER JOIN components ON dish_components.component_id = components.component_id
    WHERE main_dish.name = 'dish1';

SELECT main_dish.name, components.name FROM main_dish
INNER JOIN dish_components ON main_dish.dish_id = dish_components.dish_id
INNER JOIN components ON dish_components.component_id = components.component_id
    WHERE main_dish.name = 'dish2';

SELECT main_dish.name, components.name FROM main_dish
INNER JOIN dish_components ON main_dish.dish_id = dish_components.dish_id
INNER JOIN components ON dish_components.component_id = components.component_id
    WHERE main_dish.name = 'dish3';


SELECT soup.name, components.name FROM soup
INNER JOIN soup_components ON soup.soup_id = soup_components.soup_id
INNER JOIN components ON soup_components.component_id = components.component_id
    WHERE soup.name = 'soup1';

SELECT soup.name, components.name FROM soup
INNER JOIN soup_components ON soup.soup_id = soup_components.soup_id
INNER JOIN components ON soup_components.component_id = components.component_id
    WHERE soup.name = 'soup2';
