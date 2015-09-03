INSERT INTO `users` (`username`, `password`, `enabled`,`securitycount`) VALUES
 ('Admin', '$2a$10$9LsMSKicT4hlEfcMp75Kb.ATP87BqcXQkhD42Y0uHsJqu17zjai0G', 1, 0);


INSERT INTO `user_roles` (`user_role_id`, `username`, `ROLE`) VALUES
 (1, 'Admin', 'ROLE_ADMIN');
