INSERT INTO `MJ_GENRES` (`id`, `name`, `icon_url`) VALUES
(1001, 'POP', 'http://betamixjuke.mopita.com/web/resources/genre/pop.jpg'),
(1002, 'ROCK', 'http://betamixjuke.mopita.com/web/resources/genre/ROCK.jpg'),
(1003, 'BAND', 'http://betamixjuke.mopita.com/web/resources/genre/BAND.Alternative.jpg'),
(1004, 'SINGER SONG WRITER', 'http://betamixjuke.mopita.com/web/resources/genre/SINGER-SONG-WRITER.jpg'),
(1005, 'PUNK/HARDCORE', 'http://betamixjuke.mopita.com/web/resources/genre/PUNK.HARDCORE.jpg'),
(1006, 'POP.PUNK/EMOTIONAL CORE', 'http://betamixjuke.mopita.com/web/resources/genre/POP.PUNKEMOTIONAL-CORE.jpg'),
(1007, 'HEAVY/LOUD ROCK', 'http://betamixjuke.mopita.com/web/resources/genre/HEAVY.LOUD-ROCK.jpg'),
(1008, 'HEAVY METAL/HARD ROCK', 'http://betamixjuke.mopita.com/web/resources/genre/HEAVY-METAL.HARD-ROCK.jpg'),
(1009, 'ART ROCK', 'http://betamixjuke.mopita.com/web/resources/genre/ART-ROCK.jpg');

INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(11111111, 'Madona', 'Madona', 'Madona', null, 'Madona II', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(22222222, 'Michael Jacson', 'Michael Jacson', 'artist', null, 'Michael', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(33333333, '小阪由佳', 'kosakayuka', 'ｺｻｶﾕｶ', 'ap-1', 'こさか,ゆか', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(44444444, 'The Foundations', 'foundations', 'ﾌｧｳﾝﾃﾞｰｼｮﾝｽﾞ', null, 'ふぁうんでーしょんず', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(55555555, '沖野雅代&野口時男', 'asanomasayotonogutitokio', 'ｵｷﾉﾏｻﾖﾄﾉｸﾞﾁﾄｷｵ', null, '沖野雅代&野口時男,ｵｷﾉﾏｻﾖﾄﾉｸﾞﾁﾄｷｵ,asanomasayotonogutitokio', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(66666666, 'Sandy Nelson', 'Sandy Nelson', 'artist', null, 'NOT', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(77777777, 'Artist7', 'Artist7', 'Artist7', null, 'Madona', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(88888888, 'Artist8', 'Artist8', 'Artist8', null, 'NOT', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(99999999, 'Artist9', ' Artist9 ', 'Artist9', null, 'unknown', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(88889999, 'Artist 89', 'Artist 89', 'Artist 89', null, 'unknown', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(12121212, 'Artist 12', 'Artist 12', 'Artist 12', null, 'unknown', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(13131313, 'サカナクション', 'Artist 13', 'Artist 13', null, 'unknown', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(14141414, 'ｲｿﾔﾏｻﾔｶ', 'ｲｿﾔﾏｻﾔｶ', 'Artist 14', null, 'unknown', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(15151515, '磯山さやか', 'Artist 15', '磯山さやか', null, 'unknown', 0);
INSERT INTO `MJ_ARTISTS` (`id`, `name`, `name_alpha`, `name_kana`, `image_url`, `freeword`, `expose_flag`) VALUES
(16161616, 'ﾃﾞｨｯｷｰ･ﾘｰ', 'Artist 16', 'Artist 16', null, 'unknown', 0);


INSERT INTO `MJ_USERS` (`mjuid`, `uid`, `name`, `gender`, `birthday`, `avatar_url`, `member_status`, `share_profile`, `devid`, `unit_acc`,`free_subscribe_dt`) VALUES
(2001000, '200100', 'User1', 1, '1983-01-01 00:00:00', 'http://somewhere/avator/00003.jpg', 2, 0, NULL,1, '2013-10-01 10:30:26');
INSERT INTO `MJ_USERS` (`mjuid`, `uid`, `name`, `gender`, `birthday`, `avatar_url`, `member_status`, `share_profile`, `devid`, `unit_acc`,`free_subscribe_dt`) VALUES
(2001020, '200102', 'User2', 1, '1983-01-01 00:00:00', 'http://somewhere/avator/00003.jpg', 3, 0, NULL,1, '2013-10-01 10:30:26');
INSERT INTO `MJ_USERS` (`mjuid`, `uid`, `name`, `gender`, `birthday`, `avatar_url`, `member_status`, `share_profile`, `devid`, `unit_acc`,`free_subscribe_dt`) VALUES
(2001030, '200103', 'User3', 1, '1983-01-01 00:00:00', 'http://somewhere/avator/00003.jpg', 1, 0, NULL,1, '2011-2-01 10:30:26');
INSERT INTO `MJ_USERS` (`mjuid`, `uid`, `name`, `gender`, `birthday`, `avatar_url`, `member_status`, `share_profile`, `devid`, `unit_acc`,`free_subscribe_dt`) VALUES
(2001040, '200104', 'User4', 1, '1983-01-01 00:00:00', 'http://somewhere/avator/00003.jpg', 1, 0, NULL,1, '2013-12-12 12:30:26');
INSERT INTO `MJ_USERS` (`mjuid`, `uid`, `name`, `gender`, `birthday`, `avatar_url`, `member_status`, `share_profile`, `devid`, `unit_acc`,`free_subscribe_dt`) VALUES
(90009, '90009', 'User4', 1, '1983-01-01 00:00:00', 'http://somewhere/avator/00003.jpg', 0, 0, NULL,1, '2013-12-12 12:30:26');


INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002000', 'Song1', 'Song1', 'Song1', '01', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002002', 'Song2', 'Song2', 'Song2', '02', 90, 'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`,  `file_name`,`length`) VALUES
('10002003', 'Song3', 'Song3', 'Song3', '03', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002004', 'Song4', 'Song4', 'Song4', '04', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002005', 'Song5', 'Song5', 'Song5', '05', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002006', 'Song6', 'Song6', 'Song6', '06', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002007', 'Song7', 'Song7', 'Song7', '07', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002008', 'Song8', 'Song8', 'Song8', '08', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002009', 'Song9', 'Song9', 'Song9', '09', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002010', 'Song10', 'Song10', 'Song10', '10', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002011', 'Song11', 'Song11', 'Song11', '11', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002012', 'Song12', 'Song12', 'Song12', '12', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002013', 'Song13', 'Song13', 'Song13', '13', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002014', 'Song14', 'Song14', 'Song14', '14', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002015', 'Song15', 'Song15', 'Song15', '15', 90, 'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`,  `file_name`,`length`) VALUES
('10002016', 'Song16', 'Song16', 'Song16', '16', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`, `file_name`,`length`) VALUES
('10002017', 'Song17', 'Song17', 'Song17', '17', 90, 'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`,  `file_name`,`length`) VALUES
('10002018', 'Song18', 'Song18', 'Song18', '18', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`,  `file_name`,`length`) VALUES
('10002019', 'Song19', 'Song19', 'Song15', '19', 90,  'abc',0);
INSERT INTO `MJ_SONGS` (`pid`, `title`, `title_kana`, `title_alpha`, `product_id`, `trial_length`,  `file_name`,`length`) VALUES
('10002020', 'Song20', 'Song20', 'Song20', '20', 90, 'abc',0);

INSERT INTO `MJ_STR_DL` (`str_prod_id`,`str_region_id`,`dl_prod_id`,`dl_region_id`) VALUES ('01','10002000','01','10002000');
INSERT INTO `MJ_STR_DL` (`str_prod_id`,`str_region_id`,`dl_prod_id`,`dl_region_id`) VALUES ('02','10002002','02','10002002');
INSERT INTO `MJ_STR_DL` (`str_prod_id`,`str_region_id`,`dl_prod_id`,`dl_region_id`) VALUES ('03','10002003','03','10002003');
INSERT INTO `MJ_STR_DL` (`str_prod_id`,`str_region_id`,`dl_prod_id`,`dl_region_id`) VALUES ('04','10002004','04','10002004');
INSERT INTO `MJ_STR_DL` (`str_prod_id`,`str_region_id`,`dl_prod_id`,`dl_region_id`) VALUES ('05','10002005','05','10002005');
INSERT INTO `MJ_STR_DL` (`str_prod_id`,`str_region_id`,`dl_prod_id`,`dl_region_id`) VALUES ('06','10002006','06','10002006');
INSERT INTO `MJ_STR_DL` (`str_prod_id`,`str_region_id`,`dl_prod_id`,`dl_region_id`) VALUES ('07','10002007','07','10002007');
INSERT INTO `MJ_STR_DL` (`str_prod_id`,`str_region_id`,`dl_prod_id`,`dl_region_id`) VALUES ('08','10002008','08','10002008');
INSERT INTO `MJ_STR_DL` (`str_prod_id`,`str_region_id`,`dl_prod_id`,`dl_region_id`) VALUES ('09','10002009','09','10002009');
INSERT INTO `MJ_STR_DL` (`str_prod_id`,`str_region_id`,`dl_prod_id`,`dl_region_id`) VALUES ('10','10002010','10','10002010');

INSERT INTO `MJ_ALBUMS` (`album_id`, `title`, `title_kana`, `jacket_img`,`sale_date`) VALUES
(2201, 'Album1', 'Album1', 'image00001.png','2013-10-01 12:30:26');
INSERT INTO `MJ_ALBUMS` (`album_id`, `title`, `title_kana`, `jacket_img`,`sale_date`) VALUES
(2202, 'Album2', 'Album2', 'image00002.png','2012-10-5 7:30:26');
INSERT INTO `MJ_ALBUMS` (`album_id`, `title`, `title_kana`, `jacket_img`,`sale_date`) VALUES
(2203, 'Album3', 'Album3', 'image00003.png','2013-10-6 5:30:26');
INSERT INTO `MJ_ALBUMS` (`album_id`, `title`, `title_kana`, `jacket_img`,`sale_date`) VALUES
(2204, 'Album4', 'Album4', 'image00004.png','2013-10-4 12:30:26');
INSERT INTO `MJ_ALBUMS` (`album_id`, `title`, `title_kana`, `jacket_img`,`sale_date`) VALUES
(2205, 'Album5', 'Album5', 'image00005.png','2013-2-01 4:30:26');
INSERT INTO `MJ_ALBUMS` (`album_id`, `title`, `title_kana`, `jacket_img`,`sale_date`) VALUES
(2206, 'Album6', 'Album6', 'image00006.png','2013-2-01 1:30:26');
INSERT INTO `MJ_ALBUMS` (`album_id`, `title`, `title_kana`, `jacket_img`,`sale_date`) VALUES
(2207, 'Album7', 'Album7', 'image00007.png','2013-1-01 12:00:26');
INSERT INTO `MJ_ALBUMS` (`album_id`, `title`, `title_kana`, `jacket_img`,`sale_date`) VALUES
(2208, 'Album8', 'Album8', 'image00008.png','2013-1-01 12:00:26');
INSERT INTO `MJ_ALBUMS` (`album_id`, `title`, `title_kana`, `jacket_img`,`sale_date`) VALUES
(2209, 'Album9', 'Album9', 'image00009.png','2014-2-01 12:30:12');

INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(200111, 2001000, 11111111, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(200444, 2001020, 44444444, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(200555, 2001000, 22222222, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(200666, 2001000, 55555555, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(200222, 2001020, 22222222, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(200333, 2001020, 33333333, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(200777, 2001000, 66666666, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(200888, 2001000, 77777777, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(200999, 2001020, 88888888, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(201000, 2001030, 99999999, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(201001, 2001030, 16161616, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(201002, 2001030, 15151515, '1998-01-01 00:00:01');
INSERT INTO `MJ_FAVORITE_ARTIST` (`id`, `uid`, `aid`, `add_datetime`) VALUES
(201003, 2001030, 88888888, '1998-01-01 00:00:01');

INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(200111, 2001000, '10002000', 1, '1998-01-01 00:00:01');
INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(200333, 2001000, '10002002', 2, '1998-01-01 00:00:01');
INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(200222, 2001000, '10002003', 2, '1998-01-01 00:00:01');
INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(200666, 2001020, '10002004', 2, '1998-01-01 00:00:01');
INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(200777, 2001020, '10002000', 1, '1998-01-01 00:00:01');
INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(200444, 2001030, '10002000', 2, '1998-01-01 00:00:01');
INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(200555, 2001000, '10002004', 2, '1998-01-01 00:00:01');
INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(200888, 2001030, '10002003',1, '1998-01-01 00:00:01');
INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(200999, 2001020, '10002003',2, '1998-01-01 00:00:01');
INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(201000, 2001000, '10002005',2, '1998-01-01 00:00:01');
INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(201001, 2001000, '10002006',2, '1998-01-01 00:00:01');
INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
(201002, 2001000, '10002007',2, '1998-01-01 00:00:01');
--INSERT INTO `MJ_RATING` (`id`, `uid`, `sid`, `sflag`, `update_datetime`) VALUES
--(201003, 2001000, '10002008',2, '1998-01-01 00:00:01');

INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(220111, '10002000', 11111111, 2209);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(220222, '10002000', 22222222, 2202);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(220333, '10002002', 11111111, 2201);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(220444, '10002000', 33333333, 2201);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(220555, '10002002', 33333333, 2203);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(220666, '10002007', 66666666, 2203);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(220777, '10002006', 55555555, 2204);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(220888, '10002005', 11111111, 2201);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(220999, '10002004', 44444444, 2201);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221000, '10002012', 44444444, 2208);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221001, '10002014', 44444444, 2206);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221002, '10002008', 55555555, 2201);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221003, '10002009', 22222222, 2206);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(2210012, '10002010',66666666, 2207);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221004, '10002011', 77777777, 2201);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221005, '10002012', 88888888, 2203);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221006, '10002013', 99999999, 2203);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221007, '10002015', 12121212, 2204);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221008, '10002016', 13131313, 2204);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221009, '10002017', 12121212, 2204);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221010, '10002018', 12121212, 2206);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221011, '10002019', 33333333, 2207);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221012, '10002007', 14141414, 2207);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221013, '10002007', 15151515, 2207);
INSERT INTO `MJ_PERFORMANCE` (`id`, `sid`, `aid`, `album_id`) VALUES
(221014, '10002007', 16161616, 2207);

INSERT INTO `MJ_GROUPS` (`id`, `name`, `gid`) VALUES
(1000,"POP1",1001);
INSERT INTO `MJ_GROUPS` (`id`, `name`, `gid`) VALUES
(1001,"POP2",1001);
INSERT INTO `MJ_GROUPS` (`id`, `name`, `gid`) VALUES
(1002,"ROCK1",1002);
INSERT INTO `MJ_GROUPS` (`id`, `name`, `gid`) VALUES
(1003,"ROCK2",1002);
INSERT INTO `MJ_GROUPS` (`id`, `name`, `gid`) VALUES
(1004,"ROCK3",1002);
INSERT INTO `MJ_GROUPS` (`id`, `name`, `gid`) VALUES
(1005,"BAND1",1003);
INSERT INTO `MJ_GROUPS` (`id`, `name`, `gid`) VALUES
(1006,"SINGER1",1004);
INSERT INTO `MJ_GROUPS` (`id`, `name`, `gid`) VALUES
(1007,"SINGER2",1004);
INSERT INTO `MJ_GROUPS` (`id`, `name`, `gid`) VALUES
(1008,"SINGER3",1004);
INSERT INTO `MJ_GROUPS` (`id`, `name`, `gid`) VALUES
(1009,"SINGER4",1004);

INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(1, 11111111,1000);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(2, 66666666,1000);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(3, 77777777,1000);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(4, 11111111,1001);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(5, 22222222,1000);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(6, 11111111,1002);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES 
(7, 22222222,1003);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(8, 33333333,1002);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(9, 44444444,1004);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(12, 55555555,1004);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(13, 88888888,1004);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(14, 99999999,1004);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(15, 12121212,1004);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(16, 13131313,1004);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(17, 14141414,1004);
INSERT INTO `MJ_ARTIST_GROUP` (`id`, `artist_id`, `group_id`) VALUES
(18, 22222222,1004);

INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002000', '2013-12-02 12:01:04', 2, 1, 1, '2013-3-02 10:01:04', '2014-12-02 10:01:04', 1, 1, 1, 1, 1, '2012-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002002', '2012-12-02 1:01:04', 3, 0, 1, '2013-12-02 3:01:04', '2014-1-02 10:01:04', 1, 1, 0, 1, 1, '2013-3-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002003', '2014-12-02 3:01:04', 2, 1, 1, '2012-12-02 10:01:04', '2014-4-02 10:01:04', 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002004', '2015-12-02 3:01:04', 6, 1, 1, '2012-12-02 10:01:04', '2012-4-02 10:01:04', 1, 1, 0, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002005', '2011-12-02 3:01:04', 2, 1, 1, '2012-12-02 10:01:04', '2014-4-02 10:01:04', 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002006', '2014-12-02 3:01:04', 10, 0, 1, '2012-12-02 10:01:04', '2014-4-02 10:01:04', 0, 0, 0, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002007', '2011-12-02 3:01:04', 2, 1, 1, '2012-12-02 10:01:04', '2012-4-02 10:01:04', 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002008', '2011-12-02 3:01:04', 5, 0, 1, '2012-12-02 10:01:04', '2014-4-02 10:01:04', 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002009', '2015-12-02 3:01:04', 5, 1, 1, '2012-12-02 10:01:04', null, 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002010', '2011-12-02 3:01:04', 5, 1, 1, '2012-12-02 10:01:04', '2012-4-02 10:01:04', 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002011', '2012-12-02 3:01:04', 5, 1, 1, '2012-12-02 10:01:04', '2014-4-02 10:01:04', 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002012', '2011-12-02 3:01:04', 5, 0, 1, '2012-12-02 10:01:04', '2014-4-02 10:01:04', 1, 1, 0, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002013', '2011-12-02 3:01:04', 5, 0, 1, '2012-12-02 10:01:04', '2012-4-02 10:01:04', 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002014', '2013-12-02 3:01:04', 7, 1, 1, '2012-12-02 10:01:04', '2014-4-02 10:01:04', 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002015', '2011-12-02 3:01:04', 5, 0, 1, '2012-12-02 10:01:04', '2012-4-02 10:01:04', 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002016', '2013-12-02 3:01:04', 5, 1, 1, '2012-12-02 10:01:04', '2014-4-02 10:01:04', 1, 0, 0, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002017', '2011-12-02 3:01:04', 6, 1, 1, '2012-12-02 10:01:04', '2012-4-02 10:01:04', 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002018', '2011-12-02 3:01:04', 5, 1, 1, '2012-12-02 10:01:04', '2014-4-02 10:01:04', 1, 0, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002019', '2016-12-02 3:01:04', 5, 0, 1, '2012-12-02 10:01:04', '2014-4-02 10:01:04', 1, 1, 1, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);
INSERT INTO `MJ_STR_AUTH` (`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`, `android_search_flg`, `android_release_start_datetime`, `android_release_end_datetime`, `android_free_enable_flg`, `android_trial_enable_flg`, `android_paying_enable_flg`, `ios_delivery_enable_flg`, `ios_search_flg`, `ios_release_start_datetime`, `ios_release_end_datetime`, `ios_free_enable_flg`, `ios_trial_enable_flg`, `ios_paying_enable_flg`) 
VALUES ('10002020', '2011-12-02 3:01:04', 5, 1, 1, '2012-12-02 10:01:04', '2014-4-02 10:01:04', 1, 1, 0, 1, 1, '2010-12-02 10:01:04', '2013-12-02 10:01:04', 1, 1, 1);


-- Run many times to create data test for [#4586 Archive old MDL data]
--insert into mj_streaming_log(`id`, `uid`, `play_session_id`, `streaming_dt`, `device_cd`, `imei_no`, `device_nm`, `unit_acc`, 
--`prod_id`, `play_sec`, `course_id`, `log_type`, `app_streaming_dt`, `upd_nm`, `upd_pg`, `upd_dt`)
--select null as id,`uid`, `play_session_id`, `streaming_dt`, `device_cd`, `imei_no`, `device_nm`, `unit_acc`, 
--`prod_id`, `play_sec`, `course_id`, `log_type`, `app_streaming_dt`, `upd_nm`, `upd_pg`, `upd_dt` from mj_streaming_log;
