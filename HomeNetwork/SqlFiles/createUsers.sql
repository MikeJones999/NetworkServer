INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
 ('Admin', 'Admin@Server', 1),
 ('user', 'user@Server', 1);


INSERT INTO `user_roles` (`user_role_id`, `username`, `ROLE`) VALUES
 (1, 'Admin', 'ROLE_Admin'),
 (2, 'user', 'ROLE_USER');
