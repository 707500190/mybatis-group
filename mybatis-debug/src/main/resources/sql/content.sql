
CREATE TABLE `content`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公众号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `version` int UNSIGNED NULL DEFAULT 0,
  `time_stamp` timestamp NULL DEFAULT NULL,
  `date_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_title`(`title` ASC) USING BTREE,
  INDEX `index_account_title`(`account` ASC, `title` ASC) USING BTREE,
  INDEX `index_version`(`version` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES (1, '这是第9000账号', 'this is a test title', 0, '2023-05-19 15:34:58', '2023-05-19 15:35:01');
INSERT INTO `content` VALUES (2, '这是第9000账号', 'this is a test title', 0, '2023-05-19 15:35:03', '2023-05-19 15:35:06');
