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

-- Insert sample bookings
INSERT INTO bookings (booking_date, status, user_id) VALUES
                                                         ('2023-06-01', 'CONFIRMED', 1),
                                                         ('2023-06-05', 'PENDING', 2);

-- Insert sample hotel bookings
INSERT INTO hotel_bookings (booking_id, hotel_id, check_in_date, check_out_date) VALUES
                                                                                     (1, 1, '2023-07-01', '2023-07-05'),
                                                                                     (2, 2, '2023-07-10', '2023-07-15');

-- Insert sample room bookings
INSERT INTO room_bookings (hotel_booking_id, room_id) VALUES
                                                          (1, 1),
                                                          (1, 2),
                                                          (2, 4);

-- Insert sample flight bookings
INSERT INTO flight_bookings (booking_id, flight_id, booking_date) VALUES
                                                                      (1, 1, '2023-06-01'),
                                                                      (2, 2, '2023-06-05');

-- Insert sample passengers
INSERT INTO passengers (flight_booking_id, name, email, passport_number) VALUES
                                                                             (1, 'John Doe', 'john@example.com', 'PA1234'),
                                                                             (1, 'Jane Smith', 'jane@example.com', 'PA5678'),
                                                                             (2, 'Alice Johnson', 'alice@example.com', 'PA9012');