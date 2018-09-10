CREATE TABLE units (
    unit_id SERIAL PRIMARY KEY,
    name TEXT

);

CREATE TABLE main_dish (
    dish_id SERIAL PRIMARY KEY,
    name TEXT,
    favourite INTEGER
);


CREATE TABLE soup (
    soup_id SERIAL PRIMARY KEY,
    name TEXT,
    favourite INTEGER
);

--PRIMARY KEY (soup(soup_id), main_dish(dish_id));


CREATE TABLE components (
    component_id SERIAl PRIMARY KEY,
    name TEXT
 );

CREATE TABLE dish_components (
    dish_id INTEGER REFERENCES main_dish(dish_id),
    component_id INTEGER REFERENCES components(component_id),
    count INTEGER,
    unit TEXT,
    PRIMARY KEY(dish_id, component_id)
);

CREATE TABLE soup_components (
    soup_id INTEGER REFERENCES soup(soup_id),
    component_id INTEGER REFERENCES components(component_id),
    count INTEGER,
    unit TEXT,
    PRIMARY KEY(soup_id, component_id)
);


