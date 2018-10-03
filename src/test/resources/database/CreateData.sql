CREATE SEQUENCE common_seq;

CREATE TABLE units (
    unit_id INTEGER DEFAULT nextval('common_seq') PRIMARY KEY,
    name TEXT

);

CREATE TABLE main_dish (
    dish_id INTEGER DEFAULT nextval('common_seq') PRIMARY KEY,
    name TEXT,
    favourite INTEGER,
    recipe TEXT
);


CREATE TABLE soup (
    soup_id INTEGER DEFAULT nextval('common_seq') PRIMARY KEY,
    name TEXT,
    favourite INTEGER,
    recipe TEXT
);


CREATE TABLE components (
    component_id INTEGER DEFAULT nextval('common_seq') PRIMARY KEY,
    name TEXT
 );

CREATE TABLE dish_components (
    dish_component_id INTEGER DEFAULT nextval('common_seq') PRIMARY KEY,
    dish_id INTEGER REFERENCES main_dish(dish_id),
    component_id INTEGER REFERENCES components(component_id),
    count INTEGER,
    unit TEXT
);

CREATE TABLE soup_components (
    soup_component_id INTEGER DEFAULT nextval('common_seq') PRIMARY KEY,
    soup_id INTEGER REFERENCES soup(soup_id),
    component_id INTEGER REFERENCES components(component_id),
    count INTEGER,
    unit TEXT

);


