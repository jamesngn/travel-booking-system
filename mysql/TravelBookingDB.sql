create table flights
(
    id              bigint auto_increment
        primary key,
    available_seats int          not null,
    departure_time  datetime(6)  not null,
    destination     varchar(255) not null,
    origin          varchar(255) not null,
    price           double       not null
);

create table hotels
(
    id              bigint       not null
        primary key,
    available_rooms int          null,
    location        varchar(255) null,
    name            varchar(255) null
);

create table rooms
(
    id        bigint auto_increment
        primary key,
    available bit          not null,
    name      varchar(255) null,
    price     double       not null,
    type      varchar(255) null,
    hotel_id  bigint       null,
    constraint FKp5lufxy0ghq53ugm93hdc941k
        foreign key (hotel_id) references hotels (id)
);

create table users
(
    id       bigint auto_increment
        primary key,
    email    varchar(255) null,
    password varchar(255) null,
    username varchar(255) null
);

create table bookings
(
    id           bigint auto_increment
        primary key,
    booking_date date         null,
    status       varchar(255) null,
    user_id      bigint       null,
    constraint FKeyog2oic85xg7hsu2je2lx3s6
        foreign key (user_id) references users (id)
);

create table flight_bookings
(
    id           bigint auto_increment
        primary key,
    booking_date date   null,
    booking_id   bigint null,
    flight_id    bigint null,
    constraint FKhxnetdmvuk9rbjavrkajmuwhf
        foreign key (flight_id) references flights (id),
    constraint FKj675sy24doa091qwj5971gar8
        foreign key (booking_id) references bookings (id)
);

create table hotel_bookings
(
    id             bigint auto_increment
        primary key,
    check_in_date  date   null,
    check_out_date date   null,
    booking_id     bigint null,
    hotel_id       bigint null,
    constraint FK1ari5wjhhhrq5qlrl9s5euhmv
        foreign key (booking_id) references bookings (id),
    constraint FK8r7xq1018oerk42vjgqy7bmr3
        foreign key (hotel_id) references hotels (id)
);

create table passengers
(
    id                bigint auto_increment
        primary key,
    email             varchar(255) null,
    name              varchar(255) null,
    passport_number   varchar(255) null,
    flight_booking_id bigint       null,
    constraint FK4054aai3f5dp9uba5gmg2doa3
        foreign key (flight_booking_id) references flight_bookings (id)
);

create table room_bookings
(
    id               bigint auto_increment
        primary key,
    check_in_date    date   null,
    check_out_date   date   null,
    hotel_booking_id bigint null,
    room_id          bigint null,
    constraint FK6d98tj4ooqp84m8lx5tdhjy1q
        foreign key (hotel_booking_id) references hotel_bookings (id),
    constraint FK8wb395es6guf2c5cq0j6w2i72
        foreign key (room_id) references rooms (id)
);

-- Insert sample users
INSERT INTO `users` (email, password, username) VALUES
                                                    ('john@example.com', 'password123', 'john_doe'),
                                                    ('jane@example.com', 'password456', 'jane_smith');

-- Insert sample hotels
INSERT INTO `hotels` (id, name, location, available_rooms) VALUES
                                                               (1, 'Hilton Hotel', 'Melbourne', 200),
                                                               (2, 'Sheraton Hotel', 'Sydney', 150),
                                                               (3, 'Marriott Hotel', 'Brisbane', 100);

-- Insert sample rooms
INSERT INTO `rooms` (id, available, name, price, type, hotel_id) VALUES
                                                                     (1, 1, 'Single Room', 100, 'SINGLE', 1),
                                                                     (2, 1, 'Double Room', 200, 'DOUBLE', 1),
                                                                     (3, 1, 'Single Room', 100, 'SINGLE', 2),
                                                                     (4, 1, 'Double Room', 200, 'DOUBLE', 2),
                                                                     (5, 1, 'Single Room', 100, 'SINGLE', 3),
                                                                     (6, 1, 'Double Room', 200, 'DOUBLE', 3);


-- Insert sample flights
INSERT INTO  `flights` (id, available_seats, departure_time, destination, origin, price) VALUES
                                                                                             (1, 100, '2023-07-01 08:00:00', 'Sydney', 'Melbourne', 200),
                                                                                             (2, 150, '2023-07-10 10:00:00', 'Brisbane', 'Sydney', 300),
                                                                                             (3, 200, '2023-07-15 12:00:00', 'Melbourne', 'Brisbane', 400);

-- Insert sample bookings
INSERT INTO `bookings` (booking_date, status, user_id) VALUES
                                                           ('2023-06-01', 'CONFIRMED', 1),
                                                           ('2023-06-05', 'PENDING', 2);

-- Insert sample hotel bookings
INSERT INTO `hotel_bookings` (booking_id, hotel_id, check_in_date, check_out_date) VALUES
                                                                                       (1, 1, '2023-07-01', '2023-07-05'),
                                                                                       (2, 2, '2023-07-10', '2023-07-15');

-- Insert sample room bookings
INSERT INTO `room_bookings` (hotel_booking_id, room_id, check_in_date, check_out_date) VALUES
                                                                                           (1, 1, '2023-07-01', '2023-07-05'),
                                                                                           (1, 2, '2023-07-01', '2023-07-05'),
                                                                                           (2, 4, '2023-07-10', '2023-07-15');

-- Insert sample flight bookings
INSERT INTO `flight_bookings` (booking_id, flight_id, booking_date) VALUES
                                                                        (1, 1, '2023-06-01'),
                                                                        (2, 2, '2023-06-05');

-- Insert sample passengers
INSERT INTO `passengers` (flight_booking_id, name, email, passport_number) VALUES
                                                                               (1, 'John Doe', 'john@example.com', 'PA1234'),
                                                                               (1, 'Jane Smith', 'jane@example.com', 'PA5678'),
                                                                               (2, 'Alice Johnson', 'alice@example.com', 'PA9012');