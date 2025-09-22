CREATE TABLE `inicial_assessments` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `created_at` DATETIME(6) NOT NULL,
    `sistolic_blood_pressure` INT NOT NULL,
    `diastolic_blood_pressure` INT NOT NULL,
    `max_heart_rate` INT NOT NULL,
    `resting_heart_rate` INT NOT NULL,
    `sedentary` TINYINT(1) NOT NULL,
    `smoker` TINYINT(1) NOT NULL,
    PRIMARY KEY (`id`)
);
