use rentalpostmanagement_db;
-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('ROLE_ADMIN');
INSERT INTO `role` VALUES ('ROLE_OWN');
INSERT INTO `role` VALUES ('ROLE_TENANT');


-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('admin', b'0', '$2a$10$hbxecwitQQ.dDT4JOFzQAulNySFwEpaFLw38jda6Td.Y/cOiRzDFu');
INSERT INTO `account` VALUES ('nguoichothue1', b'0', '$2a$10$vj2HdQNmbmDnoHowsn881.H9Ny15dS71/7qZ.lqRSRxAaqagR8.Ai');
INSERT INTO `account` VALUES ('nguoithue1', b'0', '$2a$10$8P/sjA0ROIojtl86majCVOWQXHrMJvP5um/ScexvLo0zYTsOn7OTS');


-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO `users_roles` VALUES ('nguoichothue1', 'ROLE_OWN');
INSERT INTO `users_roles` VALUES ('nguoithue1', 'ROLE_TENANT');

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'null', '2023/03/04 ', 'le cuong', 'le cuong', b'1', '+84865243855', '2023/03/04 ', 0, 'admin');
INSERT INTO `users` VALUES (21, 'n3 dien bien phu', '2023/06/25 20:21:17', 'nguoithue1@gmail.com', 'le cuong', b'1', '0366457384', '2023/06/25 20:21:17', 0, 'nguoithue1');
INSERT INTO `users` VALUES (22, '333 cong hoa p25', '2023/06/25 20:22:12', 'nguoichothue1@gmail.com', 'le hue', b'1', '0366938456', '2023/06/25 20:37:20', 0, 'nguoichothue1');

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Nhà trọ');
INSERT INTO `category` VALUES (2, 'Chung cư');
INSERT INTO `category` VALUES (3, 'Nhà nguyên căn');

-- ----------------------------
-- Records of district
-- ----------------------------
INSERT INTO `district` VALUES (1, 'Quận 1');
INSERT INTO `district` VALUES (2, 'Quận 2');
INSERT INTO `district` VALUES (3, 'Quận 3');
INSERT INTO `district` VALUES (4, 'Quận 4');
INSERT INTO `district` VALUES (5, 'Quận 5');
INSERT INTO `district` VALUES (6, 'Quận 6');
INSERT INTO `district` VALUES (7, 'Quận 7');
INSERT INTO `district` VALUES (8, 'Quận 8');
INSERT INTO `district` VALUES (9, 'Quận 10');
INSERT INTO `district` VALUES (10, 'Quận 11');
INSERT INTO `district` VALUES (11, 'Quận Tân Bình');
INSERT INTO `district` VALUES (12, 'Quận Tân Phú');
INSERT INTO `district` VALUES (13, 'Quận Phú Nhuận');
INSERT INTO `district` VALUES (14, 'Quận Bình Thạnh');
INSERT INTO `district` VALUES (15, 'Quận Gò Vấp');
INSERT INTO `district` VALUES (16, 'Quận 9');
INSERT INTO `district` VALUES (17, 'Quận 12');
INSERT INTO `district` VALUES (18, 'Quận Thủ Đức');
INSERT INTO `district` VALUES (19, 'Quận Bình Tân');
INSERT INTO `district` VALUES (20, 'Huyện Hóc Môn');
INSERT INTO `district` VALUES (21, 'Huyện Bình Chánh');
INSERT INTO `district` VALUES (22, 'Huyện Nhà Bè');
INSERT INTO `district` VALUES (23, 'Huyện Củ Chi');
INSERT INTO `district` VALUES (24, 'Huyện Nhà Bè');

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (21, 50, 'abc', '2023/06/25 20:34:27', b'1', b'0', b'1', '2023/06/25 20:36:00', 8, 'acs', 'cho thue tro quan 10 gia re', 1, 9, 22);
INSERT INTO `post` VALUES (22, 50, 'abc', '2023/06/25 20:36:36', b'1', b'0', b'1', '2023/06/25 20:36:36', 8, 'acs', 'cho thue tro quan 12 gia re', 1, 17, 22);
INSERT INTO `post` VALUES (23, 50, 'abc', '2023/06/25 20:42:07', b'1', b'0', b'1', '2023/06/25 20:43:29', 8, 'acs', 'cho thue tro quan binh thanh gia re', 1, 14, 22);

-- ----------------------------
-- Records of image
-- ----------------------------

INSERT INTO `image` VALUES (84, '99a2b1529e214f00a013e4891d806d79.jpg', 21);
INSERT INTO `image` VALUES (85, '9f4d97289bc446f591a214d82b445e3a.jpg', 21);
INSERT INTO `image` VALUES (86, '54f2ef780b554c0d929c46adeebb329c.jpg', 21);
INSERT INTO `image` VALUES (87, 'aa61f714f5bb4fde9c17d686abfb5d1d.jpg', 21);
INSERT INTO `image` VALUES (88, '304b70c8acbd4497b4ecfde54fe73e9b.jpg', 21);
INSERT INTO `image` VALUES (89, 'c2bc42f63e9a4a46baac837a29d7a604.jpg', 21);
INSERT INTO `image` VALUES (90, 'a53f0cdabb574ef1993d51059906c96e.jpg', 22);
INSERT INTO `image` VALUES (91, 'e2dd272acc934629aba67e36a7a8ebb2.jpg', 22);
INSERT INTO `image` VALUES (92, '440516336d244a36aac47583c543baf1.jpg', 22);
INSERT INTO `image` VALUES (93, '86840097659a4710aa9abf8d9b20592c.jpg', 23);
INSERT INTO `image` VALUES (94, '7a612e8fa8ed4ff695f24053ed7a1aa4.jpg', 23);
INSERT INTO `image` VALUES (95, 'f3f58b484976409591ade76484e52ad4.jpg', 23);
INSERT INTO `image` VALUES (96, '272ad2236fac4ab2879491bee2880e4d.jpg', 23);
INSERT INTO `image` VALUES (97, '0ffd40a83e7a487282f106e6a3b0f222.jpg', 23);
INSERT INTO `image` VALUES (98, '9eeff1956a604a7893ad3f7a9de07e59.jpg', 23);
-- ----------------------------

-- ----------------------------
-- Records of notification
-- ----------------------------

INSERT INTO `notification` VALUES (22, '2023/06/25 20:38:43', 'Bài đăng của bạn đã được duyệt!', b'1', 22, 22);
INSERT INTO `notification` VALUES (23, '2023/06/25 20:38:58', 'Bài đăng của bạn đã được duyệt!', b'1', 21, 22);
INSERT INTO `notification` VALUES (24, '2023/06/25 20:42:33', 'Bài đăng của bạn chưa được duyệt, vui lòng chỉnh sửa lại thông tin!', b'1', 23, 22);
INSERT INTO `notification` VALUES (25, '2023/06/25 20:43:40', 'Bài đăng của bạn đã được duyệt!', b'1', 23, 22);
INSERT INTO `notification` VALUES (26, '2023/06/25 20:45:47', 'Bài đăng của bạn bị báo cáo và xác thực có hành vi sai phạm. Tài khoản của bạn sẽ bị khóa nếu sai phạm 3 lần!', b'1', 21, 22);
INSERT INTO `notification` VALUES (27, '2023/06/25 20:47:14', 'Bài đăng của bạn bị báo cáo và xác thực có hành vi sai phạm. Tài khoản của bạn sẽ bị khóa nếu sai phạm 3 lần!', b'0', 22, 22);
INSERT INTO `notification` VALUES (28, '2023/06/25 20:47:15', '', b'0', 23, 22);

-- ----------------------------
-- Records of report_post
-- ----------------------------
INSERT INTO `report_post` VALUES ('sai thong tin ve gia thue phong', '2023/06/25 20:45:17', '1278f3ddc1dc4e2b840801f13a58e6b4', b'0', 21, 21);
INSERT INTO `report_post` VALUES ('sai thong tin ve gia thue phong', '2023/06/25 20:45:22', '0a35b8a39cc4440d8d67853e3537d002', b'0', 21, 22);
INSERT INTO `report_post` VALUES ('sai thong tin ve gia thue phong', '2023/06/25 20:45:25', 'd7a926e48c924b86a38fe586ddf0d8cb', b'0', 21, 23);


-- Records of accomodation
-- ----------------------------
INSERT INTO `accomodation` VALUES (24, '  123 p 25', b'1', b'1', 5000, b'1', b'1', b'1', b'1', 3000, 21);
INSERT INTO `accomodation` VALUES (25, '   123 p 25', b'1', b'1', 5000, b'1', b'1', b'1', b'1', 3000, 22);
INSERT INTO `accomodation` VALUES (26, '    123 nguyen xi p 25', b'1', b'1', 5000, b'1', b'1', b'1', b'1', 3000, 23);