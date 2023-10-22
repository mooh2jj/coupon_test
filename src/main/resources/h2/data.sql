INSERT INTO coupons (code, end_date, name, start_date, status, type, created_at, modified_at) values ('TEST', '2021-12-31', 'Test Coupon', '2021-01-01', 'PUBLIC', 'DISCOUNT', '2021-01-01 00:00:00', '2021-01-01 00:00:00');
INSERT INTO coupons (code, end_date, name, start_date, status, type, created_at, modified_at) values ('TEST2', '2021-12-31', 'Test Coupon 2', '2021-01-01', 'PUBLIC', 'DISCOUNT', '2021-01-01 00:00:00', '2021-01-01 00:00:00');
INSERT INTO coupons (code, end_date, name, start_date, status, type, created_at, modified_at) values ('TEST3', '2021-12-31', 'Test Coupon 3', '2021-01-01', 'PUBLIC', 'DISCOUNT', '2021-01-01 00:00:00', '2021-01-01 00:00:00');

INSERT INTO `users` (`name`, `email`, `password`, `role`, `created_at`, `modified_at`) VALUES ('Custom User', 'custom@example.com', 'hashed_password_here', 'USER', '2023-10-22 12:00:00', '2023-10-23 14:30:00');
INSERT INTO `users` (`name`, `email`, `password`, `role`, `created_at`, `modified_at`) VALUES ('Another User', 'another@example.com', 'another_hashed_password', 'USER', '2023-10-24 09:15:00', '2023-10-25 16:45:00');
INSERT INTO `users` (`name`, `email`, `password`, `role`, `created_at`, `modified_at`) VALUES ('New User', 'newuser@example.com', 'new_user_password', 'USER', '2023-10-26 14:30:00', NULL);




