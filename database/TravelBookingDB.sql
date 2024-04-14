-- Insert sample users
INSERT INTO users (email, password, username) VALUES
                                                  ('john@example.com', 'password123', 'john_doe'),
                                                  ('jane@example.com', 'password456', 'jane_smith');

-- Insert sample hotels
INSERT INTO hotels (id, name, location, available_rooms) VALUES
                                                             (1, 'Hilton Hotel', 'Melbourne', 200),
                                                             (2, 'Sheraton Hotel', 'Sydney', 150),
                                                             (3, 'Marriott Hotel', 'Brisbane', 100);

-- Insert sample rooms
INSERT INTO rooms (id, available, name, price, type, hotel_id) VALUES
                                                                   (1, true, 'Single Room', 100, 'SINGLE', 1),
                                                                   (2, true, 'Double Room', 200, 'DOUBLE', 1),
                                                                   (3, true, 'Single Room', 100, 'SINGLE', 2),
                                                                   (4, true, 'Double Room', 200, 'DOUBLE', 2),
                                                                   (5, true, 'Single Room', 100, 'SINGLE', 3),
                                                                   (6, true, 'Double Room', 200, 'DOUBLE', 3);


-- Insert sample flights
INSERT INTO  flights (id, available_seats, departure_time, destination, origin, price) VALUES
                                                                                           (1, 100, '2023-07-01 08:00:00', 'Sydney', 'Melbourne', 200),
                                                                                           (2, 150, '2023-07-10 10:00:00', 'Brisbane', 'Sydney', 300),
                                                                                           (3, 200, '2023-07-15 12:00:00', 'Melbourne', 'Brisbane', 400);
