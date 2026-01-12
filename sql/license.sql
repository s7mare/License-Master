/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.17 : Database - license
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `license_company_info` */

DROP TABLE IF EXISTS `license_company_info`;

CREATE TABLE `license_company_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'å…¬å¸id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'å…¬å¸åç§°',
  `code` varchar(128) DEFAULT NULL COMMENT 'å…¬å¸ç¼–ç ',
  `contact_person` varchar(128) DEFAULT NULL COMMENT 'è”ç³»äºº',
  `contact_phone` varchar(128) DEFAULT NULL COMMENT 'è”ç³»ç”µè¯',
  `contact_email` varchar(128) DEFAULT NULL COMMENT 'è”ç³»é‚®ç®±',
  `address` varchar(128) DEFAULT NULL COMMENT 'å…¬å¸åœ°å€',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'æ˜¯å¦åˆ é™¤',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='å…¬å¸ä¿¡æ¯è¡¨';

/*Data for the table `license_company_info` */

insert  into `license_company_info`(`id`,`name`,`code`,`contact_person`,`contact_phone`,`contact_email`,`address`,`deleted`) values 
(1,'æœªæ¥å…¬å¸','10001','èƒ¡å›¾å›¾','15819248968','3121321@163.com','ç¿»æ–—èŠ±å›­3æ ‹601',0),
(2,'è¯¦ç»†','13','2','','','',0);

/*Table structure for table `license_creator_param` */

DROP TABLE IF EXISTS `license_creator_param`;

CREATE TABLE `license_creator_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL COMMENT 'å…¬å¸id',
  `system_id` bigint(20) NOT NULL COMMENT 'ç³»ç»Ÿid',
  `subject` varchar(256) NOT NULL COMMENT 'è¯ä¹¦ä¸»é¢˜',
  `private_alias` varchar(256) NOT NULL COMMENT 'ç§é’¥åˆ«å',
  `key_pass` varchar(256) NOT NULL COMMENT 'ç§é’¥',
  `store_pass` varchar(256) NOT NULL COMMENT 'å…¬é’¥',
  `license_path` varchar(256) NOT NULL COMMENT 'è¯ä¹¦ç”Ÿæˆè·¯å¾„',
  `private_key_store_path` varchar(256) NOT NULL COMMENT 'ç§é’¥å­˜å‚¨è·¯å¾„',
  `issued_time` timestamp NOT NULL COMMENT 'å¼€å§‹æ—¶é—´',
  `expiry_time` timestamp NOT NULL COMMENT 'è¿‡æœŸæ—¶é—´',
  `consumer_type` varchar(128) DEFAULT NULL COMMENT 'ç”¨æˆ·ç±»å‹',
  `consumer_amount` int(11) DEFAULT NULL COMMENT 'ç”¨æˆ·æ€»æ•°',
  `descrption` varchar(512) DEFAULT NULL COMMENT 'æè¿°',
  `ip_address` json DEFAULT NULL COMMENT 'å…è®¸çš„ipåœ°å€åˆ—è¡¨',
  `mac_address` json DEFAULT NULL COMMENT 'å…è®¸çš„macåœ°å€åˆ—è¡¨',
  `cpu_serial` varchar(256) DEFAULT NULL COMMENT 'å…è®¸çš„cpuåºåˆ—å·',
  `main_board_serial` varchar(256) DEFAULT 'Default' COMMENT 'å…è®¸çš„ä¸»æ¿åºåˆ—å·',
  `deleted` tinyint(4) DEFAULT '0' COMMENT 'æ˜¯å¦åˆ é™¤ï¼Œ0ï¼šå¦ï¼Œ1ï¼›æ˜¯',
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='è¯ä¹¦ç”Ÿæˆå‚æ•°è¡¨';

/*Data for the table `license_creator_param` */

insert  into `license_creator_param`(`id`,`company_id`,`system_id`,`subject`,`private_alias`,`key_pass`,`store_pass`,`license_path`,`private_key_store_path`,`issued_time`,`expiry_time`,`consumer_type`,`consumer_amount`,`descrption`,`ip_address`,`mac_address`,`cpu_serial`,`main_board_serial`,`deleted`) values 
(1,1,1,'ccx-models','privateKey','private_password1234','public_password1234','license.lic','C:/Users/admin/Desktop/license_demo/privateKeys.keystore','2018-04-26 14:48:12','2025-12-31 12:00:00','User',1,NULL,'[]','[]','','Default',0),
(2,1,3,'License for æœªæ¥å…¬å¸','privateKey','private_password1234','public_password1234','C:/Users/admin/Desktop/license_demo/temp.lic','C:/Users/admin/Desktop/license_demo/privateKeys.keystore','2025-12-01 00:00:00','2025-12-29 00:00:00','user',1,NULL,'[]','[]','','',0);

/*Table structure for table `license_generation_record` */

DROP TABLE IF EXISTS `license_generation_record`;

CREATE TABLE `license_generation_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ç”Ÿæˆè®°å½•id',
  `data` blob COMMENT 'è¯ä¹¦æ•°æ®',
  `param_id` bigint(20) NOT NULL COMMENT 'å‚æ•°id',
  `company_id` bigint(20) NOT NULL COMMENT 'å…¬å¸id',
  `company_name` varchar(128) DEFAULT NULL COMMENT 'å…¬å¸åç§°',
  `system_id` bigint(20) NOT NULL COMMENT 'ç³»ç»Ÿid',
  `system_name` varchar(128) DEFAULT NULL COMMENT 'ç³»ç»Ÿåç§°',
  `user_id` bigint(20) NOT NULL COMMENT 'ç”¨æˆ·id',
  `operator` varchar(128) DEFAULT NULL COMMENT 'æ“ä½œè€…åç§°',
  `issued_time` timestamp NOT NULL COMMENT 'ç”Ÿæ•ˆæ—¶é—´',
  `end_time` timestamp NOT NULL COMMENT 'ç»“æŸæ—¶é—´',
  `create_time` timestamp NULL DEFAULT NULL COMMENT 'æ“ä½œæ—¶é—´',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='è®¸å¯è¯ç”Ÿæˆè®°å½•è¡¨';

/*Data for the table `license_generation_record` */

insert  into `license_generation_record`(`id`,`data`,`param_id`,`company_id`,`company_name`,`system_id`,`system_name`,`user_id`,`operator`,`issued_time`,`end_time`,`create_time`) values 
(1,'~\ZjÓWçÚ€”¹+Ÿÿ`ÑÜ©ÆªáA§ÿµÖêäQ\0Ã\Zù=ş(j:\'ŒÁÛDZ=Ë~\n˜ç£Úšğ9Ê.ıh<~§ë.Ê2ñ>™¼Išb™.WoÌ‚¢qÏÂŒ“‹¶2Eú˜W‡MÁAq®èş¡_`~Hn^•E§<ÙÆ¢~Ew6ºîšª×YÏdzõŞf\'C7ØÖ°h<{8Ìà²µ°P“›²f™Zcæ(Ö9\\\'8L—	§Ñ€g@Üté@Õ!\Zg‘EçĞ›û¹1¶{ŸÖİ.T/!ªßnèD³6…€$w0:;ù½ÃzzL7%ÏŠàõÃ/96ï®Æ÷¹Ö‹_|º×˜g9EßöEö}İÂ‰§¨r\rEe³—}ÉS,énó¯ô\'Û0\'bq2ö¶Ûè`k“¤nû% —~UMk~?(	®´ãŞ]Ÿ<¸º-„ÕR°Ii¯G(+p´yw,¹´níİ±£‘ò˜c6\ZNh5†1|hî£”pŸ¡[#Ô6‚S(vĞ¸ÙsÄ.6Ô^ĞcĞ$ -9´‰¹MÙ™S¿ìw?Î3£¹ÿ¸x¨ÊØNÜÉuŠ„ÁdKñW¨M³*‚`QèÔıõ&İ[zB.üZìÔ¿ë0¬»tÖ¡ƒFr_\\›©t>é4ğ’1áºÔ€bìê\Z¦mãæÓ¢4Ä«9|Ğ/S7:õÆèÜÍ™.Æò‰š.™^RŸm[Ï+&ºr‚÷Û#“Zá}H´GFû±¦åpÉ×2™¬0‘^\n­¼AÌ­_luL/¼ªà…yˆzŒÔŠ8&êöÃ­‰y\0¡›<Ü$å­½vár*bEú¥Yla1¬ƒxpŞQLûÉá}®Şá´#GãĞÚÅ\näå <L*É‹˜Ò\0+ª*cğ²…¦t',1,1,'æœªæ¥å…¬å¸',1,'ç´æˆ¿é¢„çº¦ç³»ç»Ÿ',1,'ç°å¤ªç‹¼xx','2018-04-26 14:48:12','2026-01-20 12:00:00','2025-12-17 12:11:12'),
(2,'~\ZjÓWçiüö]˜V·ßÃ+ÁÇÕÌ^ˆNãQ5‹Ó€Ø´\rÛweËQ‹=Tï6UŸhOI¼şª*,Ù´ì\n¦£™¹C´:×£?ñBæƒÉ *çıfôåœl†:®«u¡ÏÔ!9v$¥)ı¤aôó$=£l‰7JP{34?­Å]Ä‹‡x“\r–^åU3öÔ«[˜°6\0÷qaÿèñ½K]-õtÏ®@\Z}by0ğ†­(jp|ı85%tZÌş€¥k‹‘Ò!àŸ/Ìô Å‹+å3Æ>\0­D¨ÿÎ°	}Ik\r :¦\'\'’$eP0›ßË²±Uêi•U<•ñè\r	aUEh¿ útDy˜¹_œàg,vâşE°rùØ\0ƒ°ç›\nı¹+şîKKŸ£7Qÿ{y a\nWN3Ğ–Gkø’Ğ9z^_­¥½‚›‰\'R—&§şïÃDÑío èÁùì*5lÅ¡µÆ1††dÍÃ{Î¢QÈ‘o eÚ9Ì—>$f×œ¥\"c^!–Uª9Ú»l¢\nËa\\YîœM’£òüpúì‘V6|VeúhNf©¿o~æ‘ÓÔ¼!&¹(}´†\0š\"÷LXWƒãVbŠßÇTª$×İ¨°İµÁ!0.ÎÏNs6:My+¿,MC›çÔ\0ÓáœZóºßÖzkr¿Øú‘¾¾„„ÂãÚN‰yr«¤O¢Nm©xk®ˆb-8Š¤¯æ{˜®¤?ÛÄÄ9P<4á>QæRÎuı¬i2qYjáOƒ«¨ÕS†U³¨¨O[¶¥Ğe/ÁæÌ2§\rv1ÄÜä53-:°ôo½>ÊF	]ıŸ†İd<}~=K•’òÙvÑÄ®/òüx¡$ı4\0!Š²ò€yi¯yMœ=•D$¼ Ø|3Û/”©Iiš\rQ†ªÔåô9ë˜Å®>¤N®tì“½',1,1,'æœªæ¥å…¬å¸',1,'ç´æˆ¿é¢„çº¦ç³»ç»Ÿ',1,'ç°å¤ªç‹¼xx','2018-04-26 14:48:12','2026-01-20 12:00:00','2025-12-17 14:58:35'),
(3,'~\ZjÓWç˜}}Ê]ïİ%£ú:}—æ;WDÉÔQDÜB¢¡ãİ%b6*…—›ü`ÿ âõE?âê#wònõÉŒÓ£²õV`Ê”ä†ØYüÎ7Ô£şy¥¬¾N	­ŞX­ÂZBi„¬L\r4ğÀêcLÕÛ)q1®Ÿ¼x¨Š©§$»“ã_á¿±t•W}BİêÛğ,Ó9èm†±–·yŒaomãs`u×ˆM*ß/%r1ù#‰¢ŞCÒ‰3Ñ²GVÜh}	!²ë m‰L5¶¤\0­\04]­•ç\Z¦Ÿáâ¡ø¨+F}‘ŞföşkY } \nıUÎÍs‰\Z\ZSLÌa½awTÿE	<áÜÇãÅ³çâOXºÖïæ#‡ù5ã©¦•§ğ‘‚*¨ÇE±8ÿ+4-±¶Ã<ûêÆ\'Wˆ ÔônA¸tÄÕûIIv	bÃX°É7WâˆË)˜•ø;LİOh,A£Afı$ÉT Ÿì¶[%ŸcNÀó‹«–³	K³3ı(‚j¯	u¢G{[şÑöµ}NF?Á}dêÛdş=†{“v¬§4=,‹îó qªh”›d¨eïSš@ÙIL›|\\ÅÀ[;FäaºÓ~ú½Û¤ÌÊ)M|qPÓ Îo5Œ¡¶OÓy %	ÿîmÿª¬Ç.J¨á\0„he,¤ª¸ôÖï²¥µ’W#oÚŸZîÚëQû¹/ÚÆ‚&½Wnµ§F„øA(/}r’Xæ°-—¢ÿlö× cÃŠ¶Tx«l8ZB(”pô¶¼Ä¤*>E2,\'›¡ÙI”\0ùrQ¬ Oıjã\Z¬I±›Éêø\' 3ÌÿW°-é{}ÙŸz:¸[yØ­Ë²iP0\rtNèã\\mI½ğíkÓÎü^ÅcœMû\\y‹y»´0ú´',1,1,'æœªæ¥å…¬å¸',1,'ç´æˆ¿é¢„çº¦ç³»ç»Ÿ',1,'ç°å¤ªç‹¼xx','2018-04-26 14:48:12','2025-12-31 12:00:00','2025-12-17 16:40:09'),
(5,'~\ZjÓWç˜}}Ê]ï]UO4A§õÙ.jÔ‹X±T§ĞÓ9nñ]¥rÔ?ù.Ï²%&’µƒ[bôƒ˜…ÚTDÊJ+é¡K2ÅdÍÉaMÎHSD<”Uõ•JÃÑ6îÙy\\±RoÛŒî1³}µËîKôb–ø†ÚZ•_9>|‡d«eö½R{ÁÜ™K~Åª¢´eÙúúöÌI’gXRÕm·wõ¸Z¹¢Û¤ÄÛP¼qclut|ñ¦Æèyîeå§|T˜¯ª¸¹cÕIcçW17ÉLyÊxûtE1?…¯dEè\0K\'À“­rNîaQpÆõØ²fÅû˜¤jj9Œê+ü8‡8	&™Ó¡Ç<ÎğBhš¹,	èÊ¨aIò‘‚ïÒè‰á¥kDø¹IÓC¦ªêØåf\0ïãoPI²£©ğÁ@ÿÙÈ	„ĞuY˜V Ùé9dUÂÖ ‘š>¯1ÜÅÀ±uåŒkêÀÂõb<¿¥,TÉ}Á6Ûµm^Øà\r ÉVãXvz®€ê´Öö®¡)Ï&™\0Á‰	ß3gz^Ô”è¬Ë{|kTòµ²I«H¦}GqñppY€GÆÑsª\'ëğU»<¬ÓTLbÿ}˜fo1ËSK\nƒëÂÃíG	Ê&!öØÖS±\'½8å\'ãi=[!tÔçìè,H·…ÖŒÔŞb\\íù{ÑæØ9Ïªal ı$\'½,x¨\\ã“fVÖ„`g+ÀäÅ-útªNƒç€uîs¨Â£X@=/g4…8†ÁŠ˜ ñË×Ãiü5ÖÁFiP FHø6¿_!<0k\Z´ô¸9t‘Pq«c?r³	°AbGq9Ã—ıÓ=¥}V66t&A9M»7z*Şó]P÷ÜCƒ­‡m÷£‹î(BSÑ\0Çñ‘N6ÇöF\Zº˜ÏÄ’‹ë;RÎ;—¦Ò¡Ôèø!',1,1,'æœªæ¥å…¬å¸',1,'ç´æˆ¿é¢„çº¦ç³»ç»Ÿ',1,'ç°å¤ªç‹¼xx','2018-04-26 14:48:12','2025-12-31 12:00:00','2025-12-18 10:47:55'),
(6,'~\ZjÓWç\"JCç@‡$4c­V¯TWıòUDnpi&Qğ_MÜl£^âi¤¯Ä‹¨ŞÓü»IÀ3º*év@ú¸6yä\"`ù“Ë¤UøÛÌëYGAÓ(6çR*\ZÈJWHøYõ¦¡Uå¨,eú¬•]paÀ•L¹W0Ä‰ô€!©çfíÕY†Tø ’ªR4¦ğÆ(«Âãu6\0ÅÕ^x\"¼çº>[Ï¡×¹½‹¢\\“I	w¿4jSéJñŞÿDĞÙS„|JÉF{]%Šûœş‘¶\rn“ê”dg!ˆÃ=YaxU »ÌYĞ^%:ó®\'íË¤qèÙ&õóŒä‡iü± ı óªWÏ1@	\"Îç,İŠà@ĞjÇ_©4})áÉg÷ÍËFšˆ\rü	Ôš¦É¾›š6;¬ÇUFñ\"BåRO=<BBôİŞ\"‘®G¬@±İ¾»\'Èô[¢«Ú/¦Ch†xÏÑ~›Ó‘`Ò6yXé‹¯Îöİèyglì•Â›oøÍŒ-íÑÀ‹m×5ƒI4×èTóqGû3gÕª±ı	¶¡«p	¤€µXñ\'ÿ S€JJÊè6ëÀy[Ó¥Á¤‚’ˆğã›m2ùÀŸ¿ê¤W%ó½IÑ³}–äaq\nÙ\Z÷Éy›6ÌŸ}gî¶v’6ad:ö¼ÇLÕÁ©ïj…„¾ûhË‘ØÈ=È:Ö§L¡I&ª¯~\r8Ää¥\"pzFwsQÓè\'lÃ–Îx´4D\"4g~üw¶ƒXºæ³‰–$P‰zâ>ç&3eúo•íÁ*j·4Î~“ ½0€—ÆTî nGIìyiP3t[ö6õûd)Bù¥şç*rpz¾ûúk—OHÌxAWÕ)]FNßA £X¢|Y¾kßˆõ Ø;\ZíÎùë¬v”çFÕcøäáj2­¨pÚš¤‹-ßœ',2,1,'æœªæ¥å…¬å¸',3,'aiè¾…åŠ©åŒ»ç–—ç³»ç»Ÿ',1,'ç°å¤ªç‹¼xx','2025-12-01 00:00:00','2025-12-29 00:00:00','2025-12-18 10:48:28'),
(7,'~\ZjÓWçuÒif—[?Éû‰Põ\'§\0HæD)*¸$6%xbÃMvOBƒt?¾°Ø{€\r[U×)KoĞV´¼†Ï×­_U÷\n¦=U¿ûÿe~À†5É…Éq÷PvkKë×¡=%\'ƒĞñ%Dãÿ•û Ò63«Ê÷­ºï ô¤f¤òÂH¯u3âÑ}Î‚åŒTÓpÙã±‰}À0$,8¶	Á —îê\r\rš–,ÿr´ÁIÓşYo¥‚sÚT¯´oe%	ÛŞ˜k’Bç\\[pÑ¢:íô»–;ĞØCGAàšı\\â%;îªô!ùYó„†HuÉRéXİ^!<\'¬Ğ¶Ä˜pßÊâOú.‹¥\"øã1Œ]jÂL—$HDßoNp\'ğLÚ©ësŠ0[Ît±•–[`{ƒ´4øg†\r_ò«JZ¿zÍô0ŠµXÅ¬º¼\'¶³i\n )aÇkûœH†X?ˆq@ñ6ú¾&Î‘QbåPs]íTV?Ä€\r_ÛÁıÖ¥t¨ğgzÉPC~+ˆÃ÷—;~È´3»´:I’&\'¶İ,Ë(ÕÃûğIÿ8íík^Ç0\\ìbïŸ‘Ãñzt?‡Òî<øL£`z-Al¢²ÔÙ³%yC\Zô©¿ÚÕPÑ&¸š04©?{LX›Ã^o¦$(Ç|ÈˆDAG¨ÆX¦.=Åê9¥\'kyù«şt»•ÑF mÔ;Ñ;üO?…Å&u±ôœHK+2Îs»©­oëVWö¦Z¢V¬¿ÜßP˜ğb¶²ÿ]„ºXõ4ÀŞ:JtÀğòüŸo!Œ”9¥?*qÌ(»¤‹ŒQ­rü—õÔjŠS·C×‡Í\Z°*•k_‰õ»‚y\\ST,Şß¼gì‡‹9„œüVÀû\r;c²ƒW5\Z˜0Ls¡GN&xêCkÀóªõâf¹I¤¢CA§qè\0”ø×<]=\"Ï!–Aœˆ3¹‡N',2,1,'æœªæ¥å…¬å¸',3,'aiè¾…åŠ©åŒ»ç–—ç³»ç»Ÿ',1,'ç°å¤ªç‹¼xx','2025-12-01 00:00:00','2025-12-29 00:00:00','2025-12-18 14:17:58'),
(8,'~\ZjÓWç…€éœäINôù¨ê¤£ƒaX}¦Ioˆìt(ÕaI»gïøéÁ¿ÎH¨éQáM]»{xÙ­ÄÚ#Àş‚	/V-ä¾Zâ2¸©™…Ùş×\"?³ª@QÀc—$¡ú2§(ËŒ†Ckö!‘İ©1‚SÒd³fó4Oóˆé[HŒ;SûgÁ ş}+{¿eÆ]Qx[#ò¡zÈë—@ú7`XDÀ‚ZV+¢®£ÆÔì¥21İœi¥£ÚÊf¢ıŒ[}¹ø{TÈ{]à}Jeqk2U¥Û©ë¯Š¥Z­ y!§È&ğ¿G–ÖìDù/ÊŞŒm]ŞA·™ô£€6={QêÊn¤¸Š²PÃòYxA‘{‡Ü†Ó®òYäşÊ«é+‡İ8ë5ÌÜE%ün1,1˜SK]©wTà)\\äˆğ÷‰ó&nÂd©Öáì)Ø©Öxı„†)X‡\0*×(ıx£Eyé×\0uÈsÈñ9ÆŒ®R;^~v/±¦\"AÂ±…\0ó¶6vñóHïAÕó8c\\†Ì:³÷¯L,qeH-/ÖW…=d\0ûb ÷P»€Ç{eª]šÒ›\r(É´Ä@ÛA[4¸\0€Óêéep}ï¥­]K›Ãdú¾.ğÙ@$*á…\'âÑ¾ŸgÃxìÄ“Ÿ6èHX¦b„Ê ñÊÆ¼{\Z_w#Rî)¨¡ı·ÎwZş(iBàó¯[ØD­|ÈWî¼ıGc›¹@²±Ê©>,N„n}¶‹\0V«íX3Húå£gõJÊÁ¨¬D2ØçÛ{à§?òbğfûÿÜ`*(.,æg“©\r³?±SêB\0†¬U’Eh^.%m­ÒaÓŒm=¸è‡Ép®XÔ®31q¼\0²ˆ\"„!	ı¶Üøš¶}®±Æ|äÁz«púWÊ)¯nßŞôı@Æ¦W(8©§ğƒÒ¹¤G¦›è°•4p£\rA˜Ç©\n',2,1,'æœªæ¥å…¬å¸',3,'aiè¾…åŠ©åŒ»ç–—ç³»ç»Ÿ',1,'ç°å¤ªç‹¼xx','2025-12-01 00:00:00','2025-12-29 00:00:00','2025-12-18 15:16:38'),
(9,'~\ZjÓWçiüö]˜V·ßLÌ¼	ã»ípÚúu²tõâ!=·%‚ªg¾a(·í¡0]ËGİA«Û‘>¦r—~\Z—²!Ç`Â|iHã?C¶hìšõlœR+ó»âZ±RÎØA0¡Ã9Üš;-ôK;xÌ-_÷½fán	Ÿ{isYT|c-bÇàÊŸ!ëz¬I5}\rˆ}Q¤…|ë^cvÌa¼Î†%W@Ë©eQKd%ÏéNÌRØŠ¦îM¦ù¬	z/Å´¥òåø8;y\nØÃ:ÚÑ–GÌpA\Zƒø=ĞX‹ÿØbx—´»İºbí°og]¥#Š°udö©D|Õ\0Š:†üCkß…_ÿ×·`Åİ8ÒZ‰‚Û¼oø\n;bsßuåÔ[IDL6vº3*™Qú‚Q2üÆùCåG`ôİÍ,ß^·>C‘©ÇG¹èÖ±…ª:FÉB½\r….2Ô“täìõ|KUÓÖ#Á«ÀŒQ`‘ı-	3œÙj|{.4NAÅ-\rg‚–èCÎÔ0»:?ÄÇûÂêã\Z¶ªw²°Î=¥ç£7SÈ†—4:Ô(+‘ü‰DÏ¨úÕüF!Á¯ª#Ì pãi,º9ÈVúXÈíØ”o?Ãî½bL/¸·#’Ìš•á§^6/zd=Î·×âNˆVé)ZO45\\=×ñ5{PêQú@‘	Ïz]<]cA5~\0Á*ö\rê,€\\ëÊÿdrÁÒW*éXN3¢±\nôCRï.v¤ûh¬„—êAlöFÉfI¥Î‡o·W¬{±‚+ÛàË0|Éâ¸DÉEy¡øâRZø8Œ;ò&“º£3ôn;¾	[É¦·,œNôËÙÖ®ãKéI¸ë¡U¾‰lòŸ=kÄ¾øŸM®NÌ]~ÿq:÷œòûraŒ×73Òôi,VI³˜\Zq½r^',1,1,'æœªæ¥å…¬å¸',1,'ç´æˆ¿é¢„çº¦ç³»ç»Ÿ',1,'ç°å¤ªç‹¼xx','2018-04-26 14:48:12','2025-12-31 12:00:00','2025-12-18 16:52:58'),
(10,'~\ZjÓWçiüö]˜V·ß³‹âE€³:¡L#jA5ÄÚıÈaùM£¨á–©ããmí`öB„¥7Ä¬\'X8²Ò3=>²ü`X7ÀúûoÚs¥\r.ØªÄ%G_Äæ¾¹3,fH¸×Å×{Éó®ÎÈŞ)Yµ›?((½÷}VAŒñi9Ö\rË•­KÃä·Ú|Ic¯ÏÛ{÷÷cy¢”Vë?\\…®TC¹Ùßa“¤¿Û~×%À<q–ÕŒËY–q{| İ‹…µ›€ñídÜâÁ_ù„õ®]È3ƒVj¿ópmîÉİëjÓ°şñ‡ò‰ú¼øu’¤§ß6¡ã¼ãY¥g&d‰•ƒa®8›°ıGc6©¥/(¶qÛ/¡Ï¨›ÅkÕ2hæšHYÚçÂf>7ö™³°‰“\'S”tèD5WÑó¹íœH Yá9N¼˜9Â‡72_\0÷&¬÷Ë…Õou¨t3DNÁ)Q/õJ^ïèÿ/¬‘s7|å‰=7D(Ş¨À\Z,×IÌõô0pô)û—Çµ €ãäéo½3‹½Bâ]íé7y^ 	‚·wD¢ÆAÂÏš§½§äaô¸o‚òf>¹“ÿ*÷\\ØdM‡z»âuš¸Óİ”D¬É¥TîÔó²Š$m1Aî¼ÅwjØ‚†~†ABjÔ×K\"óÂ¸ˆßÆâÆ\0¡«[­–ĞCjŠŞQ#%Éò2‡DrÍ\Zš(éë9ğ¯İ¼›±È4 òZ=Wİ-*ŞJ§/Ñ÷NAxO™†%à~²2=\'Of”\'ÃEo}3šĞ„à¢ÁÉq\"ÁU\"³ŒN\Z¬½ç“ÃÔãáâˆ&|.Î‘ğñ®“ÿá<¥Èû‚¦™\nu†O½‘qÓrûwÀä§‘¼º%i²;Ldº	Xó(9Ôs^Æ_ZÏ¶ƒèg÷¶yti“	¿R>İ³İ®ù‰×r',1,1,'æœªæ¥å…¬å¸',1,'ç´æˆ¿é¢„çº¦ç³»ç»Ÿ',1,'ç°å¤ªç‹¼xx','2018-04-26 14:48:12','2025-12-31 12:00:00','2025-12-19 14:10:47'),
(11,'~\ZjÓWçïu„Íc8ë‚\'Ö_Àìğæí:)Íí²&\'mÇõ§:§?ÀÆK©ö}vıÏ¿´»ÈJâ\Z|xBÖÂ+ÖÔOİç“\rñı]ó\'º6„$Ø×[ì)LåØ)Şû†z+\r©¥ï}„|n÷ôu¶l‡UòªàF\0B`ÑÛÚ=]TòØÕíU™†ô]ãŞ¾%—üÓ\'	UG¡CÊã&Êp×3¯>‹-™šnÚÑÒG%’5x÷¥îá—2…*|äb#FïT*\nAäW´êñ`Æ\rÜ²<¡ÚpHO†XĞR#q4	d@\Z@©|$V=W+ò	Õ‹òXğëÌT¦‰¯£?8>…rVÀ‘òü¸µ\ní‹¹\ZZ ;ÛîóŠBè0È·Şß¨mGV†;§ç]ÙÀ\rËÉ‹+#–˜rëÏë#WöB–<vÛÅ²éT8cŠRØ‚ğ\reî˜ 7û™Ç7’2&2d‰.<A°£Õ„\'ıŒ÷“¼v¤áİœÂ(úŞ<lìÍ”å©m¦ôåkÃæ›;§ô.ÌHW²âíâ¼…\Z×G\"\"f1æ8\n¯Yqr÷íÁgBq’.-Ç¨TX&k®iÙÿåÈğ¤•Rø‹Á6ŒªŞÅeÓ†\'‚6Æ(ÙéTìg<_Iì{§btûŸ¥PZêlr×–ûŸ:§5Èì?Pö¡Ç| ê¹öyhõ ¨Vj¼êO ÷ÿ`ş	P)vŸ,õĞ¡ã|îˆÀÊ°róñØzŸTd¼?h|P+Õ/§Ö§?mòAış²ÿˆB»µÊPã.FÌæxh`Rí›uÕ¾3‹{Fÿ¾ Æ·¸FŞäÔ,ÚÏ%‹ld<Ãñ¦H`›|Xå˜=ºë¥•D™£ÆÇ@;\Zø&EáT1åÏÚxM½ÿ¨DSe–`4æÖEî÷ßp	2¬çW4Sj&¹öz%‘àË',1,1,'æœªæ¥å…¬å¸',1,'ç´æˆ¿é¢„çº¦ç³»ç»Ÿ',1,'ç°å¤ªç‹¼xx','2018-04-26 14:48:12','2025-12-31 12:00:00','2025-12-19 17:38:04');

/*Table structure for table `license_system_info` */

DROP TABLE IF EXISTS `license_system_info`;

CREATE TABLE `license_system_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `version` varchar(64) DEFAULT NULL COMMENT 'ç‰ˆæœ¬å·',
  `description` varchar(512) DEFAULT NULL COMMENT 'æè¿°',
  `deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='éƒ¨ç½²ç³»ç»Ÿä¿¡æ¯è¡¨';

/*Data for the table `license_system_info` */

insert  into `license_system_info`(`id`,`name`,`version`,`description`,`deleted`) values 
(1,'ç´æˆ¿é¢„çº¦ç³»ç»Ÿ','1.0','ä¸€ä¸ªåŒ…å«ç½‘é¡µç®¡ç†ç«¯ã€appå°ç¨‹åºçš„ç³»ç»Ÿ',0),
(2,'ç—…ç†æ•™å­¦ç³»ç»Ÿ','1.0','ä¸€ä¸ªåœ¨çº¿æ•™è‚²ç³»ç»Ÿ\n',0),
(3,'aiè¾…åŠ©åŒ»ç–—ç³»ç»Ÿ','1.0','é€šè¿‡è®¡ç®—æœºå›¾åƒè¯†åˆ«æŠ€æœ¯ã€aiã€å¤§æ•°æ®ç­‰æŠ€æœ¯å¸®åŠ©åŒ»ç”Ÿæ‰¾å‡ºç—…å› ',0);

/*Table structure for table `license_user_info` */

DROP TABLE IF EXISTS `license_user_info`;

CREATE TABLE `license_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ç”¨æˆ·id',
  `username` varchar(128) NOT NULL COMMENT 'ç”¨æˆ·å',
  `nick_name` varchar(128) DEFAULT NULL COMMENT 'æ˜µç§°æˆ–çœŸå®å§“å',
  `password` varchar(128) NOT NULL COMMENT 'å¯†ç ',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'æ˜¯å¦åˆ é™¤',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `constraint_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ç”¨æˆ·ä¿¡æ¯è¡¨';

/*Data for the table `license_user_info` */

insert  into `license_user_info`(`id`,`username`,`nick_name`,`password`,`deleted`) values 
(1,'admin','ç°å¤ªç‹¼xx','123456',0),
(2,'test2','test2','123456',1),
(4,'aa','aa','123456',0),
(5,'bb','bb','123456',0),
(6,'cc','cc','123456',0),
(7,'dd','dd','123456',0),
(8,'ee','ee','123456',0),
(9,'aqaa','2321323','34434432',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
