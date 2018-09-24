CREATE TABLE units (
    unit_id SERIAL PRIMARY KEY,
    name TEXT

);

CREATE TABLE main_dish (
    dish_id SERIAL PRIMARY KEY,
    name TEXT,
    favourite INTEGER,
    recipe TEXT
);


CREATE TABLE soup (
    soup_id SERIAL PRIMARY KEY,
    name TEXT,
    favourite INTEGER,
    recipe TEXT
);

--PRIMARY KEY (soup(soup_id), main_dish(dish_id));


CREATE TABLE components (
    component_id SERIAl PRIMARY KEY,
    name TEXT
 );

CREATE TABLE dish_components (
    dish_component_id SERIAL PRIMARY KEY,
    dish_id INTEGER REFERENCES main_dish(dish_id),
    component_id INTEGER REFERENCES components(component_id),
    count INTEGER,
    unit TEXT
);

CREATE TABLE soup_components (
    soup_component_id SERIAL PRIMARY KEY,
    soup_id INTEGER REFERENCES soup(soup_id),
    component_id INTEGER REFERENCES components(component_id),
    count INTEGER,
    unit TEXT

);


