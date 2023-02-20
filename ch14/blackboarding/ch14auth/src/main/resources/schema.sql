CREATE TABLE `oauth_access_token` (
	`token_id` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_general_ci',
	`token` BLOB NOT NULL,
	`authentication_id` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`user_name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`client_id` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`authentication` BLOB NOT NULL,
	`refresh_token` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	PRIMARY KEY (`token_id`) USING BTREE
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `oauth_refresh_token` (
	`token_id` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_general_ci',
	`token` BLOB NOT NULL,
	`authentication` BLOB NOT NULL,
	PRIMARY KEY (`token_id`) USING BTREE
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

