-- --- USERS (Password is 'password' for all) ---
INSERT INTO users (id, name, email, password_hash, role) VALUES 
(100, 'Arjun Singh', 'arjun@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQyS', 'ATHLETE'),
(101, 'Ishaan Khatri', 'ishaan@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQyS', 'ATHLETE'),
(102, 'Rohan Mehra', 'rohan@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQyS', 'ATHLETE'),
(103, 'Siddharth Nair', 'sid@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQyS', 'ATHLETE'),
(104, 'Kabir Verma', 'kabir@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQyS', 'ATHLETE'),
(105, 'Aditya Reddy', 'aditya@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQyS', 'ATHLETE'),
(106, 'Aryan Khan', 'aryan@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQyS', 'ATHLETE'),
(107, 'Vihaan Joshi', 'vihaan@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQyS', 'ATHLETE'),
(108, 'Devansh Gupta', 'dev@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQyS', 'ATHLETE'),
(109, 'Pranav Rao', 'pranav@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQyS', 'ATHLETE'),
(200, 'Senior Scout', 'scout@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQyS', 'SCOUT');

-- --- ATHLETES (Map coordinates set to major Indian cities) ---
INSERT INTO athletes (id, user_id, sport, age_group, city, state, bio, latitude, longitude, created_at) VALUES 
(100, 100, 'Football', 'U19', 'Mumbai', 'Maharashtra', 'Elite winger with clinical finishing.', 19.0760, 72.8777, CURRENT_TIMESTAMP),
(101, 101, 'Cricket', 'U19', 'Delhi', 'Delhi', 'Right-arm fast bowler, hitting 140ks.', 28.6139, 77.2090, CURRENT_TIMESTAMP),
(102, 102, 'Basketball', 'U23', 'Bangalore', 'Karnataka', 'Versatile forward with high IQ playmaking.', 12.9716, 77.5946, CURRENT_TIMESTAMP),
(103, 103, 'Badminton', 'U17', 'Hyderabad', 'Telangana', 'Aggressive smash player with lightning reflex.', 17.3850, 78.4867, CURRENT_TIMESTAMP),
(104, 104, 'Football', 'SENIOR', 'Kolkata', 'West Bengal', 'Solid center-back, dominant in the air.', 22.5726, 88.3639, CURRENT_TIMESTAMP),
(105, 105, 'Cricket', 'U19', 'Chennai', 'Tamil Nadu', 'Top-order batsman, specialized in red ball.', 13.0827, 80.2707, CURRENT_TIMESTAMP),
(106, 106, 'Athletics', 'U16', 'Chandigarh', 'Punjab', '100m sprinter, currently 10.8s personal best.', 30.7333, 76.7794, CURRENT_TIMESTAMP),
(107, 107, 'Football', 'U19', 'Kochi', 'Kerala', 'Creative midfielder with vision and passing.', 9.9312, 76.2673, CURRENT_TIMESTAMP),
(108, 108, 'Cricket', 'U23', 'Jaipur', 'Rajasthan', 'Leg-spinner with a dangerous googly.', 26.9124, 75.7873, CURRENT_TIMESTAMP),
(109, 109, 'Badminton', 'U19', 'Pune', 'Maharashtra', 'Strategic defensive player, high stamina.', 18.5204, 73.8567, CURRENT_TIMESTAMP);

-- --- PERFORMANCE METRICS (Mock tactical data) ---
INSERT INTO performance_metrics (athlete_id, metric_type, metric_value, timestamp) VALUES 
(100, 'Top Speed', 33.5, CURRENT_TIMESTAMP),
(100, 'Goals', 12.0, CURRENT_TIMESTAMP),
(101, 'Fastest Ball', 142.0, CURRENT_TIMESTAMP),
(101, 'Wickets', 45.0, CURRENT_TIMESTAMP),
(103, 'Smash Speed', 320.0, CURRENT_TIMESTAMP),
(106, '100m Dash', 10.8, CURRENT_TIMESTAMP);
