create table flights
(
    id             bigint auto_increment
        primary key,
    availableSeats int          not null,
    departureTime  datetime(6)  not null,
    destination    varchar(255) not null,
    origin         varchar(255) not null,
    price          double       not null
);

INSERT INTO flights (origin, destination, departureTime, availableSeats, price) VALUES
                                                                                    ('Melbourne', 'Sydney', '2024-04-10T08:00:00', 150, 250.00),
                                                                                    ('Sydney', 'Brisbane', '2024-04-11T09:30:00', 120, 180.00),
                                                                                    ('Brisbane', 'Cairns', '2024-04-12T11:00:00', 100, 220.00),
                                                                                    ('Cairns', 'Darwin', '2024-04-13T12:30:00', 80, 280.00),
                                                                                    ('Darwin', 'Perth', '2024-04-14T14:00:00', 90, 320.00),
                                                                                    ('Perth', 'Adelaide', '2024-04-15T15:30:00', 110, 200.00),
                                                                                    ('Adelaide', 'Melbourne', '2024-04-16T17:00:00', 130, 190.00),
                                                                                    ('Melbourne', 'Hobart', '2024-04-17T18:30:00', 70, 150.00),
                                                                                    ('Hobart', 'Sydney', '2024-04-18T20:00:00', 100, 210.00),
                                                                                    ('Sydney', 'Canberra', '2024-04-19T21:30:00', 80, 140.00),
                                                                                    ('Canberra', 'Gold Coast', '2024-04-20T07:00:00', 120, 240.00),
                                                                                    ('Gold Coast', 'Sunshine Coast', '2024-04-21T08:30:00', 90, 120.00),
                                                                                    ('Sunshine Coast', 'Townsville', '2024-04-22T10:00:00', 100, 260.00),
                                                                                    ('Townsville', 'Mackay', '2024-04-23T11:30:00', 80, 160.00),
                                                                                    ('Mackay', 'Rockhampton', '2024-04-24T13:00:00', 70, 130.00),
                                                                                    ('Rockhampton', 'Bundaberg', '2024-04-25T14:30:00', 90, 180.00),
                                                                                    ('Bundaberg', 'Hervey Bay', '2024-04-26T16:00:00', 60, 100.00),
                                                                                    ('Hervey Bay', 'Brisbane', '2024-04-27T17:30:00', 110, 150.00),
                                                                                    ('Brisbane', 'Newcastle', '2024-04-28T19:00:00', 100, 200.00),
                                                                                    ('Newcastle', 'Sydney', '2024-04-29T20:30:00', 90, 140.00),
                                                                                    ('Sydney', 'Albury', '2024-04-30T22:00:00', 80, 190.00),
                                                                                    ('Albury', 'Mildura', '2024-05-01T07:30:00', 70, 160.00),
                                                                                    ('Mildura', 'Melbourne', '2024-05-02T09:00:00', 100, 220.00),
                                                                                    ('Melbourne', 'Launceston', '2024-05-03T10:30:00', 90, 170.00),
                                                                                    ('Launceston', 'Devonport', '2024-05-04T12:00:00', 60, 80.00),
                                                                                    ('Devonport', 'King Island', '2024-05-05T13:30:00', 50, 120.00),
                                                                                    ('King Island', 'Burnie', '2024-05-06T15:00:00', 70, 100.00),
                                                                                    ('Burnie', 'Hobart', '2024-05-07T16:30:00', 80, 130.00),
                                                                                    ('Hobart', 'Melbourne', '2024-05-08T18:00:00', 100, 180.00),
                                                                                    ('Melbourne', 'Canberra', '2024-05-09T19:30:00', 110, 240.00),
                                                                                    ('Melbourne', 'Sydney', '2024-04-10T10:30:00', 120, 240.00),
                                                                                    ('Melbourne', 'Sydney', '2024-04-10T13:00:00', 100, 260.00),
                                                                                    ('Melbourne', 'Sydney', '2024-04-10T15:30:00', 80, 270.00),
                                                                                    ('Melbourne', 'Sydney', '2024-04-10T18:00:00', 90, 280.00),
                                                                                    ('Sydney', 'Brisbane', '2024-04-11T12:00:00', 110, 190.00),
                                                                                    ('Sydney', 'Brisbane', '2024-04-11T14:30:00', 100, 200.00),
                                                                                    ('Sydney', 'Brisbane', '2024-04-11T17:00:00', 90, 210.00),
                                                                                    ('Sydney', 'Brisbane', '2024-04-11T19:30:00', 80, 220.00),
                                                                                    ('Brisbane', 'Cairns', '2024-04-12T13:30:00', 90, 230.00),
                                                                                    ('Brisbane', 'Cairns', '2024-04-12T16:00:00', 80, 240.00),
                                                                                    ('Brisbane', 'Cairns', '2024-04-12T18:30:00', 70, 250.00),
                                                                                    ('Brisbane', 'Cairns', '2024-04-12T21:00:00', 60, 260.00),
                                                                                    ('Cairns', 'Darwin', '2024-04-13T15:00:00', 70, 290.00),
                                                                                    ('Cairns', 'Darwin', '2024-04-13T17:30:00', 60, 300.00),
                                                                                    ('Cairns', 'Darwin', '2024-04-13T20:00:00', 50, 310.00),
                                                                                    ('Cairns', 'Darwin', '2024-04-13T22:30:00', 40, 320.00),
                                                                                    ('Darwin', 'Perth', '2024-04-14T16:30:00', 80, 330.00),
                                                                                    ('Darwin', 'Perth', '2024-04-14T19:00:00', 70, 340.00),
                                                                                    ('Darwin', 'Perth', '2024-04-14T21:30:00', 60, 350.00),
                                                                                    ('Darwin', 'Perth', '2024-04-15T00:00:00', 50, 360.00),
                                                                                    ('Perth', 'Adelaide', '2024-04-15T18:00:00', 100, 210.00),
                                                                                    ('Perth', 'Adelaide', '2024-04-15T20:30:00', 90, 220.00),
                                                                                    ('Perth', 'Adelaide', '2024-04-15T23:00:00', 80, 230.00),
                                                                                    ('Perth', 'Adelaide', '2024-04-16T01:30:00', 70, 240.00);