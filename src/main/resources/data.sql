/*
    SQL file to add some db items.

    Author: Dana Clemmer
    File: data.sql
    Date: 11/4/2021
*/

/*
 Admin username: dclemmer
 pass: adminPass
 */
INSERT INTO user (city, first_name, last_name, password, state, username) VALUES ("Kent", "Dana", "Clemmer", "$2a$10$4AxptFnw3b3EFV7fkFpa5.3EwZUJcvyl7tC0bJYrpVZYQQ0eR6lV2", "WA", "dclemmer");
INSERT INTO permission (role, user_id) VALUES ("ROLE_ADMIN", 1);


INSERT INTO pnw_adventures (beach_included, camp_included, destination, hike_included, itinerary, region, state,
                       waterfall_included, image_path, user_id)
VALUES (true, false, 'Seattle', false,
        'Alki Beach, Pike Place Market, Kerry Park, Gasworks Park, Chihuly Garden and Glass, Seattle Aquarium, Cherry Blossoms at UW (Spring)', 'Puget Sound', 'WA', false, "/images/seattle.jpg", 1);

INSERT INTO pnw_adventures (beach_included, camp_included, destination, hike_included, itinerary, region, state,
                       waterfall_included, image_path, user_id)
VALUES (false, true, 'Diablo/Ross Lake', true,
        'Camp, Hike the Thunder Knob Trail, Diablo Lake Overlook, Paddle-board, Campfire, Fish', 'North Cascades', 'WA', false, "/images/DiabloLake.jpg", 1);

INSERT INTO pnw_adventures (beach_included, camp_included, destination, hike_included, itinerary, region, state,
                       waterfall_included, image_path, user_id)
VALUES (true, false, 'Northwest Oregon Coast', false,
        'Seaside, Ecola State Park, Cannon Beach, Hug Point State Recreation Site, Manzanita, Rockaway Beach, Tillamook Creamery, Cape Kiwanda State Natural Area', 'Oregon Coast', 'OR', false, "/images/cannonBeach.jpg", 1);

INSERT INTO pnw_adventures (beach_included, camp_included, destination, hike_included, itinerary, region, state,
                       waterfall_included, image_path, user_id)
VALUES (true, false, 'Northern Olympic Peninsula', true,
        'Hurricane Ridge, Rialto Beach, Salt Creek Recreation Area, Ruby Beach, Lake Crescent, Mt. Storm King Hike, Fat Smittys Restaurant, A Lavender Field in Sequim', 'Olympic Peninsula', 'WA', false, "/images/holeInWall.jpg", 1);

INSERT INTO pnw_adventures (beach_included, camp_included, destination, hike_included, itinerary, region, state,
                       waterfall_included, user_id)
VALUES (true, false, 'Route 20', false, 'Diablo Lake Overlook, Washington Pass Overlook, Explore Winthrop', 'North Cascades', 'WA', false, 1);

INSERT INTO pnw_adventures (beach_included, camp_included, destination, hike_included, itinerary, region, state,
                       waterfall_included, image_path, user_id)
VALUES (false, true, 'Mount Rainier', true, 'Tolmie Peak Lookout, Paradise Hikes, Sunrise Hikes, Camp on Mount. Rainier', 'South Cascades', 'WA', true, "/images/rainier.jpg", 1);

INSERT INTO pnw_adventures (beach_included, camp_included, destination, hike_included, itinerary, region, state,
                       waterfall_included, image_path, user_id)
VALUES (false, false, 'Southern WA/Northern OR', true,
        'Ape Caves Mount St. Helens, Beacon Rock State Park, Portland, Voodoo Doughnut, Crown Point, Multnomah Falls, Latourell Falls, Bridgeside Restaurant by Bridge of the Gods', 'Columbia River Gorge', 'OR', true, "/images/gorge.jpg", 1);

INSERT INTO pnw_adventures (beach_included, camp_included, destination, hike_included, itinerary, region, state,
                       waterfall_included, image_path, user_id)
VALUES (false, false,
        'US 2 to Leavenworth', true, 'Heybrook Lookout, 59er Diner, Explore Leavenworth, Hike Colchuck Lake', 'Central Cascades', 'WA', false, "/images/colchuck.jpg", 1);


INSERT INTO hikes (name, difficulty, miles, adventure_id) VALUES ('Hike the Thunder Knob Trail', 2.5, 3.3, 2);

INSERT INTO hikes (name, difficulty, miles, adventure_id) VALUES ('Hurricane Ridge Trails', 2, 2, 4);

INSERT INTO hikes (name, difficulty, miles, adventure_id) VALUES ('Mt. Storm King Hike', 5, 4.5, 4);

INSERT INTO hikes (name, difficulty, miles, adventure_id) VALUES ('Tolmie Peak Lookout', 3.5, 5.6, 6);

INSERT INTO hikes (name, difficulty, miles, adventure_id) VALUES ('Panorama Point via Paradise', 3.5, 5, 6);

INSERT INTO hikes (name, difficulty, miles, adventure_id) VALUES ('Naches Peak Loop Trail via Sunrise', 2, 3.4, 6);

INSERT INTO hikes (name, difficulty, miles, adventure_id) VALUES ('Ape Caves Mount St. Helens', 1, 1.4, 7);

INSERT INTO hikes (name, difficulty, miles, adventure_id) VALUES ('Beacon Rock Trail', 3, 1.5, 7);

INSERT INTO hikes (name, difficulty, miles, adventure_id) VALUES ('Multnomah Falls Trail', 2, 2.4, 7);

INSERT INTO hikes (name, difficulty, miles, adventure_id) VALUES ('Latourell Falls', 2, 3, 7);

INSERT INTO hikes (name, difficulty, miles, adventure_id) VALUES ('Colchuck Lake Hike', 4.5, 8, 8);

