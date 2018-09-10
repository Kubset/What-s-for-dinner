INSERT INTO main_dish (name, favourite)
    VALUES ('dish1', 5),
           ('dish2', 4),
           ('dish3', 6);
INSERT INTO soup (name, favourite)
    VALUES ('soup1', 5),
           ('soup2', 4);
INSERT INTO components (name)
    VALUES ('component1_fordish1'),
           ('component2_fordish1'),
           ('component3_fordish12'),
           ('component4_fordish2'),
           ('component5_fordish31'),
           ('component6_fordish3'),
           ('component7_forsoup12'),
           ('component8_forsoup1fordish3');


INSERT INTO dish_components(dish_id, component_id)
    VALUES ((SELECT dish_id FROM main_dish WHERE name='dish1'), (SELECT component_id FROM components WHERE name='component1_fordish1')),
            ((SELECT dish_id FROM main_dish WHERE name='dish1'), (SELECT component_id FROM components WHERE name='component2_fordish1')),
            ((SELECT dish_id FROM main_dish WHERE name='dish1'), (SELECT component_id FROM components WHERE name='component3_fordish12')),
            ((SELECT dish_id FROM main_dish WHERE name='dish2'), (SELECT component_id FROM components WHERE name='component3_fordish12')),
            ((SELECT dish_id FROM main_dish WHERE name='dish2'), (SELECT component_id FROM components WHERE name='component4_fordish2')),
            ((SELECT dish_id FROM main_dish WHERE name='dish1'), (SELECT component_id FROM components WHERE name='component5_fordish31')),
            ((SELECT dish_id FROM main_dish WHERE name='dish3'), (SELECT component_id FROM components WHERE name='component5_fordish31')),
            ((SELECT dish_id FROM main_dish WHERE name='dish3'), (SELECT component_id FROM components WHERE name='component6_fordish3')),
            ((SELECT dish_id FROM main_dish WHERE name='dish3'), (SELECT component_id FROM components WHERE name='component8_forsoup1fordish3'));



INSERT INTO soup_components(soup_id, component_id)
    VALUES ((SELECT soup_id FROM soup WHERE name='soup1'), (SELECT component_id FROM components WHERE name='component7_forsoup12')),
           ((SELECT soup_id FROM soup WHERE name='soup2'), (SELECT component_id FROM components WHERE name='component7_forsoup12')),
           ((SELECT soup_id FROM soup WHERE name='soup1'), (SELECT component_id FROM components WHERE name='component8_forsoup1fordish3'));

INSERT INTO units (name)
    VALUES ('szt'),
           ('kg'),
           ('l')