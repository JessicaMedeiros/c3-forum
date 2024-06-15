CREATE TABLE `topico` (
                          `id` BIGINT NOT NULL AUTO_INCREMENT,
                          `titulo` VARCHAR(255) UNIQUE NOT NULL,
                          `mensagem` VARCHAR(255) UNIQUE NOT NULL,
                          `curso` VARCHAR(255) NOT NULL,
                          `autor` VARCHAR(255) NOT NULL,
                          `data_criacao` DATETIME NOT NULL,
                          `status` VARCHAR(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
