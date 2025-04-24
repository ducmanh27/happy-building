INSERT INTO `building` (`address`, `longitude`, `latitude`, `created_at`, `created_by`) VALUES
                                                                                            ('Hanoi Medical University Hospital', 105.82, 21.00, CURRENT_DATE, 'admin'),
                                                                                            ('456 Le Loi, Ho Chi Minh City', 106.70, 10.77, CURRENT_DATE, 'admin'),
                                                                                            ('789 Tran Phu, Da Nang', 108.22, 16.07, CURRENT_DATE, 'admin');

INSERT INTO `room` (`building_id`, `floor_number`, `room_number`,`created_at`, `created_by`) VALUES
                                                                      (1, 1, 101,CURRENT_DATE, 'admin'),
                                                                      (1, 2, 202, CURRENT_DATE, 'admin'),
                                                                      (2, 3, 305, CURRENT_DATE, 'admin'),
                                                                      (2, 4, 410, CURRENT_DATE, 'admin'),
                                                                      (3, 5, 502, CURRENT_DATE, 'admin');