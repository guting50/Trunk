package com.zhiluo.android.yunpu.sms.jsonbean;

import java.util.List;

/**
 * Created by Cheng on 2017/2/14.
 */

public class SMSJsonBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"tempClassList":[{"GID":"94575fa9-1296-11e6-9174-1c872c481c7f","TC_ClassName":"新货到了","TC_ClassCode":"023","TC_Creator":null,
     * "TC_Update":null,"TC_CreatorTime":null,"TC_UpdateTime":null,"TC_Group":3},{"GID":"945eb157-1296-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"买赠满送","TC_ClassCode":"024","TC_Creator":null,"TC_Update":null,"TC_CreatorTime":null,"TC_UpdateTime":null,"TC_Group":3},
     * {"GID":"946b58af-1296-11e6-9174-1c872c481c7f","TC_ClassName":"降价了","TC_ClassCode":"025","TC_Creator":null,"TC_Update":null,
     * "TC_CreatorTime":null,"TC_UpdateTime":null,"TC_Group":3},{"GID":"9472fb65-1296-11e6-9174-1c872c481c7f","TC_ClassName":"三八妇女节",
     * "TC_ClassCode":"026","TC_Creator":null,"TC_Update":null,"TC_CreatorTime":null,"TC_UpdateTime":null,"TC_Group":3},
     * {"GID":"b1206cd0-1296-11e6-9174-1c872c481c7f","TC_ClassName":"端午节","TC_ClassCode":"027","TC_Creator":null,"TC_Update":null,
     * "TC_CreatorTime":null,"TC_UpdateTime":null,"TC_Group":3},{"GID":"b12774e1-1296-11e6-9174-1c872c481c7f","TC_ClassName":"中秋佳节",
     * "TC_ClassCode":"028","TC_Creator":null,"TC_Update":null,"TC_CreatorTime":null,"TC_UpdateTime":null,"TC_Group":3},
     * {"GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福","TC_ClassCode":"018","TC_Creator":null,"TC_Update":null,
     * "TC_CreatorTime":null,"TC_UpdateTime":null,"TC_Group":3},{"GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福",
     * "TC_ClassCode":"019","TC_Creator":null,"TC_Update":null,"TC_CreatorTime":null,"TC_UpdateTime":null,"TC_Group":3},
     * {"GID":"da076d47-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日促销","TC_ClassCode":"020","TC_Creator":null,"TC_Update":null,
     * "TC_CreatorTime":null,"TC_UpdateTime":null,"TC_Group":3},{"GID":"da1dbf25-10da-11e6-9174-1c872c481c7f","TC_ClassName":"流失顾客挽回",
     * "TC_ClassCode":"022","TC_Creator":null,"TC_Update":null,"TC_CreatorTime":null,"TC_UpdateTime":null,"TC_Group":3}],
     * "tempManagerList":[{"GID":"3b292aa6-1299-11e6-9174-1c872c481c7f","TC_GID":"9472fb65-1296-11e6-9174-1c872c481c7f","TC_ClassName":"三八妇女节",
     * "TC_ClassCode":"026","TM_Name":null,"TM_Content":"妇女节到，祝福魅力无敌，聪明伶俐；神采飞扬，常带笑意；端庄漂亮，青春靓丽；聪明赛孔明，英明如上帝的你，妇女节开心又快乐！","TM_Creator":null,
     * "TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"3b2ea93d-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"9472fb65-1296-11e6-9174-1c872c481c7f","TC_ClassName":"三八妇女节","TC_ClassCode":"026","TM_Name":null,
     * "TM_Content":"微微的春风，轻轻的思念，淡淡的问候，处处的三八，远远的朋友，真诚的祝福，祝福久违的姐妹生活快乐，三八妇女节快乐！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"3b3a9c36-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"9472fb65-1296-11e6-9174-1c872c481c7f","TC_ClassName":"三八妇女节","TC_ClassCode":"026","TM_Name":null,
     * "TM_Content":"在三八妇女节来临之际，短信致以诚挚的问候：长期奋斗在厨房，奋斗在关心孩子，奋斗在工作阵地上的女性同胞，你们辛苦了，祝你们三八节快乐！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"3b496f3b-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"9472fb65-1296-11e6-9174-1c872c481c7f","TC_ClassName":"三八妇女节","TC_ClassCode":"026","TM_Name":null,
     * "TM_Content":"妇女节快乐女同胞们，走出家门，摆脱烦脑，尽情地潇潇洒一回，享受一回吧！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,
     * "CY_GID":null,"TM_Group":3},{"GID":"3b507e5f-1299-11e6-9174-1c872c481c7f","TC_GID":"9472fb65-1296-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"三八妇女节","TC_ClassCode":"026","TM_Name":null,
     * "TM_Content":"古往今来，后宫佳丽三千，杨贵妃却只有一个；印度能歌善舞才女无数，泰姬陵也只有一个。世间美女数不胜数，你却只有一个！提前祝你三八妇女节快乐！","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"3b64a0e5-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"9472fb65-1296-11e6-9174-1c872c481c7f","TC_ClassName":"三八妇女节","TC_ClassCode":"026","TM_Name":null,
     * "TM_Content":"女人如山，山清水秀，女人如水，水波潋滟，女人如风，风过无痕，女人如花，花开不败。3.8妇女节到了，祝你美丽无敌，青春永驻！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"3b6e5c42-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"9472fb65-1296-11e6-9174-1c872c481c7f","TC_ClassName":"三八妇女节","TC_ClassCode":"026","TM_Name":null,
     * "TM_Content":"三八妇女节，一个专属于你们的节日到了，祝福你们身手矫健，工作顺利，心情愉快，生活舒爽，家庭幸福，万事如意！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"3b76922b-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"9472fb65-1296-11e6-9174-1c872c481c7f","TC_ClassName":"三八妇女节","TC_ClassCode":"026","TM_Name":null,
     * "TM_Content":"三八三八，女生如花，尽情欢笑，快乐到家。魅力加倍，迷倒一把，温柔聪明，人见人夸。开心过节，你是老大，一生富贵，幸福如花！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"5447f7fb-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福","TC_ClassCode":"018","TM_Name":null,
     * "TM_Content":"一周的忙忙碌碌，暂时告一段落，简简单单的生活，今天开始翻翻闲置的书卷，走访久违的朋友，守候家里的温馨。在此，送上我淡淡的问候，祝您周末快乐！","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"544e58fe-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福","TC_ClassCode":"018","TM_Name":null,
     * "TM_Content":"工作辛苦，多休息。听听音乐，玩玩游戏。多吃蔬菜，少发脾气。要是无聊，来这happy。周末到了，祝你事事顺利！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"5453d1aa-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福","TC_ClassCode":"018","TM_Name":null,
     * "TM_Content":"收拾起忙碌的心情，整理好久违的记忆，牢锁住工作的搅扰，忘记掉所有的压力。对花儿微笑，对小鸟问好。又一个周 末来到，短信一条，祝周末愉快！ 别忘了来嗨皮哦~~ ","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"5459abfe-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福","TC_ClassCode":"018","TM_Name":null,"TM_Content":"也许祝福只是一种形式,
     * 但却能给心灵带来温馨,我们都把关心发给彼此,一样的日子一样的心声:周末愉快!","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,
     * "TM_Group":3},{"GID":"545e70fb-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福",
     * "TC_ClassCode":"018","TM_Name":null,"TM_Content":"别因太多的忙碌冷淡了温柔,别因太多的追求湮没了享受,工作不是人生的全部,停停匆匆的脚步,请享受生活的赐福!周末愉快!","TM_Creator":null,
     * "TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"5468b059-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福","TC_ClassCode":"018","TM_Name":null,
     * "TM_Content":"生活工作忙和累，周末到来多睡睡。放松身心把梦追，身体充电别浪费。轻轻祝福敞心 扉，健康快乐都加倍！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"5470be67-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福","TC_ClassCode":"018","TM_Name":null,
     * "TM_Content":"感谢天，感谢地，感谢命运让我们相遇；你的情，你的意，我将永远铭记在心里；提前祝福你周末满满的好心情。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54771e27-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福","TC_ClassCode":"018","TM_Name":null,
     * "TM_Content":"感谢您一直以来对#店铺名称#的支持，值此周末之际，祝您和您的家人周末愉快！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,
     * "CY_GID":null,"TM_Group":3},{"GID":"547d960e-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"周末祝福","TC_ClassCode":"018","TM_Name":null,"TM_Content":"时间因祝福而流光溢彩，空气因祝福而芬芳袭人，心情因祝福而花开灿烂，当你打开信息时，愿祝福让你开心此时此刻！周末愉快！",
     * "TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},
     * {"GID":"548577c6-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福","TC_ClassCode":"018",
     * "TM_Name":null,"TM_Content":"别因太多的忙碌冷淡了温柔,别因太多的追求湮没了享受,工作不是人生的全部,停停匆匆的脚步,请享受生活的赐福!周末愉快!","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"548bbabd-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福","TC_ClassCode":"018","TM_Name":null,
     * "TM_Content":"轻松问个好，千里迢迢，尽显真诚关照。如果你笑了，祝你心情好的目的达到；如果你没笑，说明短信初步没见效。还得常聊，这样感情牢靠，心情更好！ ","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54928cba-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9eb0e3b-10da-11e6-9174-1c872c481c7f","TC_ClassName":"周末祝福","TC_ClassCode":"018","TM_Name":null,
     * "TM_Content":"偶尔的繁忙，不代表淡忘；新一天的到来，愿你心情舒畅；曾落下的问候，这次一起补偿；所有的关心，凝聚成这条短信，愿你每一个明天都比今天幸福 ","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"549c4b6c-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"以后每年的这一天，我都会待在你身边，除了送上实惠，还要说一句真诚的祝福：生日快乐！愿新的一岁好运不断，天天开心！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54a2df0f-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"青春、阳光、欢笑，这是属于你的日子。不管距离是近是远，不管生活是忙是闲，我的祝福又一次准时到来，祝你生日快乐！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54ad6e14-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"每一年，我都会圈着日历上这一页，为你，我的朋友，献上祝福，生日快乐！我为你准备了一个小礼物，本周进店即可领取。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54b3f07e-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"当我把神灯擦三下，灯神问我想许什么愿？我说：我想让你帮我保佑正在看这条讯息的人，希望那人生日快乐，永远幸福！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54ba7c4b-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"在这个阳光灿烂的日子里，祝您身体健康，心情愉快！在这个特别的日子里，为您送上最真挚的祝福，祝您：生日快乐!家庭幸福!事业辉煌！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54c47a39-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"年年有今朝，岁岁乐陶陶。愿你一直保持嘴角30度的微笑弧度，永远拥有内心100度的青春热度。请感受来自#店铺名称#120度真诚的祝福温度：生日快乐！ ","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54c9f118-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"不是每个清晨都有朝阳，不是每个夜晚都有清凉，不是每个帆船都能远航，不是每个人生都有辉煌，但是每个生日都有我的祝福永不忘，愿你快乐永健康，笑容常挂在脸上。 ","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54d1b5e6-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"春天和你漫步在盛开的花丛间，夏天夜晚陪你一起看星星眨眼，秋天黄昏与你徜徉在金色麦田，冬天雪花飞舞有你更加温暖。怎么样，看到这几句话不自觉的唱了起来了吗？！在您生日的今天，#店铺名称#的祝福就像这个旋律一样，根本停不下来！祝您生日快乐。 ",
     * "TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},
     * {"GID":"54d81ae0-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019",
     * "TM_Name":null,"TM_Content":"因为你的降临，这一天成了一个美丽的日子，从此世界便多了一抹诱人的色彩，#店铺名称#也将因为你的光临蓬荜生辉。祝你生日快乐！","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54dfb739-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"祝您生日快乐，更多好运。真诚感谢您对#店铺名称#的支持和厚爱。祝您的人生、事业如皓月当空，天地无限！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54e55c74-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"圣旨到！奉天承运，皇帝诏曰：#会员名称#寿辰已至，特赐短信一条。内有平安一生，快乐一 世，幸福一辈子。#会员名称#执此圣旨可前往#店铺名称#领取寿礼一份。钦此！ ","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54eeab7d-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"送你一份温馨，两份问候，三份美好，四份懵懂，五份高贵，六份前程，七份典雅，八份柔情，九份财运，十份真诚。生日快乐！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"54f93aeb-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"d9f44446-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日祝福","TC_ClassCode":"019","TM_Name":null,
     * "TM_Content":"大海啊他全是水，蜘蛛啊他全是腿，辣椒啊他真辣嘴，认识你啊真不后悔。#店铺名称#祝你生日快乐，天天开怀合不拢嘴。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"5507b726-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日促销","TC_ClassCode":"020","TM_Name":null,
     * "TM_Content":"如果能与您一起度过这个特别的日子，#店铺名称#将倍感荣幸，我们真心祝您生日快乐，并为您准备了小礼物，期待您的光临！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"550da7a1-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日促销","TC_ClassCode":"020","TM_Name":null,
     * "TM_Content":"#店铺名称#衷心祝您生日快乐，我们给您准备了一份精美的小礼物，生日当天在本店消费专属8折特惠哦，期待您的光临！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"5513fcbb-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日促销","TC_ClassCode":"020","TM_Name":null,
     * "TM_Content":"#店铺名称#全体员工祝您生日快乐！我们为您准备了专属的生日小礼物，快来进店领取吧~","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,
     * "CY_GID":null,"TM_Group":3},{"GID":"551a5a35-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"生日促销","TC_ClassCode":"020","TM_Name":null,"TM_Content":"亲爱的#会员名称#，明天是您的生日，祝您好运！#店铺名称#已给您发送了20元生日礼金，赶快进店买份礼物吧",
     * "TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},
     * {"GID":"5520ccb6-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日促销","TC_ClassCode":"020",
     * "TM_Name":null,"TM_Content":"尊敬的#会员名称#，您生日之际#店铺名称#衷心祝您生日快乐，生日当日指定消费5倍积分及精美礼物，祝您生活幸福！","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"552a91f4-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日促销","TC_ClassCode":"020","TM_Name":null,
     * "TM_Content":"尊敬的#会员名称#：值此您生日之际，#店铺名称#衷心祝您生日快乐，阖家幸福！温馨提示，生日当天来店消费可获积分翻倍！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"5531b311-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日促销","TC_ClassCode":"020","TM_Name":null,
     * "TM_Content":"#店铺名称#所有工作人员祝您生日快乐！本周进店专享8折，只属于你的秘密优惠，我们还为您准备了精美的生日礼物，期待光临！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"55382afe-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日促销","TC_ClassCode":"020","TM_Name":null,
     * "TM_Content":"感谢您长期以来对#店铺名称#的支持，在这个特别的日子，我们为您准备了精美的生日礼物，本周内可领取，期待您的光临！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"553e6c25-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日促销","TC_ClassCode":"020","TM_Name":null,"TM_Content":"亲爱的#会员名称#(先生/女士)
     * ：#店铺名称#以短信的形式向您送上最实惠的生日礼物\u2014\u2014本周内凭此短信消费专享8折优惠。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,
     * "CY_GID":null,"TM_Group":3},{"GID":"554753ab-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"生日促销","TC_ClassCode":"020","TM_Name":null,
     * "TM_Content":"有句话一直没敢对你说，可是你生日的时候再不说就没机会了：本周来#店铺名称#消费，专属8折优惠，私密专享，并有精美小礼物送上，#店铺名称#全体工作人员祝您生日快乐！","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"554de075-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日促销","TC_ClassCode":"020","TM_Name":null,
     * "TM_Content":"感谢您长期以来对#店铺名称#的支持，今天是个特别的日子，我们为您准备了一份生日礼物，本周内进店即领，恭候您的光临！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"555484ac-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da076d47-10da-11e6-9174-1c872c481c7f","TC_ClassName":"生日促销","TC_ClassCode":"020","TM_Name":null,
     * "TM_Content":"这是#店铺名称#陪伴您度过的第X个生日，感谢您长期以来的支持，生日当天您可专享店内商品8折优惠，还有精美礼物等您拿！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"55b18614-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da1dbf25-10da-11e6-9174-1c872c481c7f","TC_ClassName":"流失顾客挽回","TC_ClassCode":"022","TM_Name":null,"TM_Content":"亲爱的#会员名称#(先生/女士)
     * ：上次一别已经两个月未见，欢迎进店小酌咖啡一杯，#店铺名称#希望聆听您最近发生的故事。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,
     * "TM_Group":3},{"GID":"55b7b65b-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"da1dbf25-10da-11e6-9174-1c872c481c7f","TC_ClassName":"流失顾客挽回",
     * "TC_ClassCode":"022","TM_Name":null,"TM_Content":"亲爱的#会员名称#(先生/女士)：本周#店铺名称#5年店庆，是您的支持和信任让我们不断发展，老朋友感恩回馈，全 场8折，期待本周与您重逢！","TM_Creator":null,
     * "TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"55c1a62a-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da1dbf25-10da-11e6-9174-1c872c481c7f","TC_ClassName":"流失顾客挽回","TC_ClassCode":"022","TM_Name":null,"TM_Content":"亲爱的#会员名称#(先生/女士)
     * ：您已经3个月没来#店铺名称#了，我们全体店员都非常想念您，近期大量新品上架，一定有您喜欢的，期待您再次光临。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,
     * "CY_GID":null,"TM_Group":3},{"GID":"55c86a9a-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"da1dbf25-10da-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"流失顾客挽回","TC_ClassCode":"022","TM_Name":null,"TM_Content":"#店铺名称#怀念与您相处的每一个瞬间，为了回馈您一直以来对本店的关照。8.1\u20148
     * .7期间，老客户进店消费专享8折，部分商品折上再折，期待您再次莅临本店！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,
     * "TM_Group":3},{"GID":"55d0e271-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"da1dbf25-10da-11e6-9174-1c872c481c7f","TC_ClassName":"流失顾客挽回",
     * "TC_ClassCode":"022","TM_Name":null,"TM_Content":"亲爱的#会员名称#(先生/女士)：您已经3个月没来#店铺名称#了，我们全体店员都非常想念您，近期大量新品上架，一定有您喜欢的，期待您再次光临。",
     * "TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},
     * {"GID":"55d82a13-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"da1dbf25-10da-11e6-9174-1c872c481c7f","TC_ClassName":"流失顾客挽回","TC_ClassCode":"022",
     * "TM_Name":null,"TM_Content":"亲爱的#会员名称#(先生/女士)：您已经3个月没来#店铺名称#了，如果我们的工作有不到位的地方，还请您指正，真诚希望向您提供最优质的产品和服务！","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"55dea627-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da1dbf25-10da-11e6-9174-1c872c481c7f","TC_ClassName":"流失顾客挽回","TC_ClassCode":"022","TM_Name":null,"TM_Content":"亲爱的#会员名称#(先生/女士)
     * ：本周#店铺名称#5年店庆，是您的支持和信任让我们不断发展，老朋友感恩回馈，全场8折，期待本周与您重逢！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,
     * "CY_GID":null,"TM_Group":3},{"GID":"55e69f6f-0dd4-11e6-b99f-1c872c481c7f","TC_GID":"da1dbf25-10da-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"流失顾客挽回","TC_ClassCode":"022","TM_Name":null,
     * "TM_Content":"#店铺名称#希望向您提供最优质的服务，如果能得到您的宝贵意见，我们将倍感荣幸，X月X日-X月X日进店\u201c找茬\u201d享受折上8折的感恩优惠，不止是生意。","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"55ee91fd-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da1dbf25-10da-11e6-9174-1c872c481c7f","TC_ClassName":"流失顾客挽回","TC_ClassCode":"022","TM_Name":null,
     * "TM_Content":"感谢向我们提出的建议，#店铺名称#已经积极做出相应改进，希望为您创造更优质的购物体验，下次进店凭此短信享受3倍积分，本短信有效期1个月。","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"55f51f7d-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da1dbf25-10da-11e6-9174-1c872c481c7f","TC_ClassName":"流失顾客挽回","TC_ClassCode":"022","TM_Name":null,
     * "TM_Content":"感恩Party温暖你我！X月X日来#店铺名称#参与消费抽奖，中奖率100%，最高可获得200元代金券，幸运即将眷顾！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"55fccd75-0dd4-11e6-b99f-1c872c481c7f",
     * "TC_GID":"da1dbf25-10da-11e6-9174-1c872c481c7f","TC_ClassName":"流失顾客挽回","TC_ClassCode":"022","TM_Name":null,
     * "TM_Content":"温馨提示：您在#店铺名称#的3000积分即将过期，我们一直用心为您保管价值100元的小礼物，X月X日前进店即可免费兑换。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"646e934c-1298-11e6-9174-1c872c481c7f",
     * "TC_GID":"945eb157-1296-11e6-9174-1c872c481c7f","TC_ClassName":"买赠满送","TC_ClassCode":"024","TM_Name":null,
     * "TM_Content":"满100，减40！#店铺名称#2013年巨惠开卖！错过一天，后悔一年，所有实惠，只为回馈您长期以来的关照，期待光临。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"647dcda3-1298-11e6-9174-1c872c481c7f",
     * "TC_GID":"945eb157-1296-11e6-9174-1c872c481c7f","TC_ClassName":"买赠满送","TC_ClassCode":"024","TM_Name":null,
     * "TM_Content":"#店铺名称#7月火热大放送，清凉价格冰爽降温，全场满200减50，满300减100，活动截止日期：X月X日。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"64850c28-1298-11e6-9174-1c872c481c7f",
     * "TC_GID":"945eb157-1296-11e6-9174-1c872c481c7f","TC_ClassName":"买赠满送","TC_ClassCode":"024","TM_Name":null,
     * "TM_Content":"X月X日至X月X日#店铺名称#全场满300减100，更推出10种99元特价商品，凑单购物乐享划算！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"648eba61-1298-11e6-9174-1c872c481c7f",
     * "TC_GID":"945eb157-1296-11e6-9174-1c872c481c7f","TC_ClassName":"买赠满送","TC_ClassCode":"024","TM_Name":null,
     * "TM_Content":"#店铺名称#最实惠赠品清凉来袭！X月X日至X月X日购物满100元即送50元代金券，下次消费即可使用，超值划算不容错过。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"649a1a28-1298-11e6-9174-1c872c481c7f",
     * "TC_GID":"946b58af-1296-11e6-9174-1c872c481c7f","TC_ClassName":"降价了","TC_ClassCode":"025","TM_Name":null,
     * "TM_Content":"#店铺名称#新品价位创新低，全场3折起低至99元，仅限本周，让你在炎热的夏日冰爽一\u201c夏\u201d。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"64a8d873-1298-11e6-9174-1c872c481c7f",
     * "TC_GID":"946b58af-1296-11e6-9174-1c872c481c7f","TC_ClassName":"降价了","TC_ClassCode":"025","TM_Name":null,
     * "TM_Content":"冰点降价，凉爽来袭！X月X日至X月X日#店铺名称#单笔订单直降30元，你来或者不来，实惠就在那里，你买或不买，优惠就在那里。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"64b05a3a-1298-11e6-9174-1c872c481c7f",
     * "TC_GID":"946b58af-1296-11e6-9174-1c872c481c7f","TC_ClassName":"降价了","TC_ClassCode":"025","TM_Name":null,
     * "TM_Content":"#店铺名称#店主拍胸脯保证全市最优惠，会员进店即享折上9折，更有3倍积分，快快买起来！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,
     * "CY_GID":null,"TM_Group":3},{"GID":"64bb1356-1298-11e6-9174-1c872c481c7f","TC_GID":"946b58af-1296-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"降价了","TC_ClassCode":"025","TM_Name":null,"TM_Content":"比淘宝还便宜！#店铺名称#X月X日至X月X日新品3折起，逾期立即恢复原价，实惠疯抢刻不容缓！","TM_Creator":null,
     * "TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"64c66ac0-1298-11e6-9174-1c872c481c7f",
     * "TC_GID":"946b58af-1296-11e6-9174-1c872c481c7f","TC_ClassName":"降价了","TC_ClassCode":"025","TM_Name":null,
     * "TM_Content":"#店铺名称#一向很贵，除了今天！只要199元享受原价399元完美体验，妞儿，再不疯抢真没了！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,
     * "CY_GID":null,"TM_Group":3},{"GID":"64d13468-1298-11e6-9174-1c872c481c7f","TC_GID":"946b58af-1296-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"降价了","TC_ClassCode":"025","TM_Name":null,"TM_Content":"#店铺名称#，您身边的优品秒杀。X月X日至X月X日每天推出一款商品，肯定有你喜欢的，只要1元，立刻开抢！",
     * "TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},
     * {"GID":"64dd4a8e-1298-11e6-9174-1c872c481c7f","TC_GID":"946b58af-1296-11e6-9174-1c872c481c7f","TC_ClassName":"降价了","TC_ClassCode":"025",
     * "TM_Name":null,"TM_Content":"暑假大放送，整月不打烊，全场底价狂欢，学生党进店即享新品5折！再不买就老了！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"64e944a4-1298-11e6-9174-1c872c481c7f",
     * "TC_GID":"946b58af-1296-11e6-9174-1c872c481c7f","TC_ClassName":"降价了","TC_ClassCode":"025","TM_Name":null,
     * "TM_Content":"X月X日至X月X日#店铺名称#推出10样特价商品，原价389元的XXX，现在只要189！更多惊喜欢迎进店了解，现在不抢，后悔一年！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"64f21615-1298-11e6-9174-1c872c481c7f",
     * "TC_GID":"946b58af-1296-11e6-9174-1c872c481c7f","TC_ClassName":"降价了","TC_ClassCode":"025","TM_Name":null,
     * "TM_Content":"#店铺名称#也团购！X月X日至X月X日会员卡进店即办，和朋友一起进店消费，总额满1000元，立享8折，实惠不容错过。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"64fabc9a-1298-11e6-9174-1c872c481c7f",
     * "TC_GID":"946b58af-1296-11e6-9174-1c872c481c7f","TC_ClassName":"降价了","TC_ClassCode":"025","TM_Name":null,
     * "TM_Content":"感谢您一直以来对#店铺名称#的支持，实惠是我们最好的谢礼，X月X日至X月X日全市最低价感恩回馈，新品3折起，8折封顶，疯抢开始！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"948401bc-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"b1206cd0-1296-11e6-9174-1c872c481c7f","TC_ClassName":"端午节","TC_ClassCode":"027","TM_Name":null,
     * "TM_Content":"端午到了，我送你一个爱心粽子，第一层，体贴！第二层，关怀！第三层，浪漫！第四层，温馨！中间夹层，甜蜜！祝你天天都有一个好心情！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"949bc31f-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"b1206cd0-1296-11e6-9174-1c872c481c7f","TC_ClassName":"端午节","TC_ClassCode":"027","TM_Name":null,
     * "TM_Content":"一条短信息，祝福一串串：端午节到了，祝快快乐乐，开开心心；健健康康，轻轻松松；团团圆圆，恩恩爱爱；和和美美，红红火火！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"94a42d41-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"b1206cd0-1296-11e6-9174-1c872c481c7f","TC_ClassName":"端午节","TC_ClassCode":"027","TM_Name":null,
     * "TM_Content":"也许祝福是一种形式，但是却能给人的心灵带来温馨，希望我的祝福同样的能给你心灵带来温馨！朋友端午节快乐","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"94ac9e20-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"b1206cd0-1296-11e6-9174-1c872c481c7f","TC_ClassName":"端午节","TC_ClassCode":"027","TM_Name":null,
     * "TM_Content":"端午粽子香，祝福要成双；安康福运送，收到就珍藏；顺心如意送，珍藏就吉祥。端午节将至，愿你的生活甜甜美美，事业顺顺利利！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"94bd1cf2-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"b1206cd0-1296-11e6-9174-1c872c481c7f","TC_ClassName":"端午节","TC_ClassCode":"027","TM_Name":null,
     * "TM_Content":"五月五，是端午，敲敲锣来打打鼓。吃粽子，赛龙舟，唱唱歌来跳跳舞。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,
     * "TM_Group":3},{"GID":"94c7db60-1299-11e6-9174-1c872c481c7f","TC_GID":"b1206cd0-1296-11e6-9174-1c872c481c7f","TC_ClassName":"端午节",
     * "TC_ClassCode":"027","TM_Name":null,"TM_Content":"端午节到了，愿粽子带给你好运！祝你：工作\u201c粽\u201d被领导夸，生活\u201c粽\u201d是多美梦，钱财\u201c粽\u201d是赚不完，朋友\u201c
     * 粽\u201d是很贴心，笑容\u201c粽\u201d是把你恋。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},
     * {"GID":"94d1ba23-1299-11e6-9174-1c872c481c7f","TC_GID":"b1206cd0-1296-11e6-9174-1c872c481c7f","TC_ClassName":"端午节","TC_ClassCode":"027",
     * "TM_Name":null,"TM_Content":"杨梅红、杏儿黄、蒲月初五是端阳，棕叶香、包五粮、剥个棕子裹上糖；艾草芳、龙舟忙，追逐幸福勇向上；夸姣的糊口万年长！祝您端午节快乐！","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"94e69db4-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"b1206cd0-1296-11e6-9174-1c872c481c7f","TC_ClassName":"端午节","TC_ClassCode":"027","TM_Name":null,
     * "TM_Content":"端午节什么祝福最简单最能表达出节日的气氛字数最少，想一想，想一想是什么呢？是：端午节快乐！哈哈简单的问候！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"c3babff2-1297-11e6-9174-1c872c481c7f",
     * "TC_GID":"94575fa9-1296-11e6-9174-1c872c481c7f","TC_ClassName":"新货到了","TC_ClassCode":"023","TM_Name":null,
     * "TM_Content":"来疯抢吧！#店铺名称#7月新品全线上架，X月X日至X月X日新品8折，其余商品最低3折，实惠立刻带回家。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"c3c01c10-1297-11e6-9174-1c872c481c7f",
     * "TC_GID":"94575fa9-1296-11e6-9174-1c872c481c7f","TC_ClassName":"新货到了","TC_ClassCode":"023","TM_Name":null,
     * "TM_Content":"#店铺名称#8折来袭，不是新品8折，不是换季清货8折，实打实的全场8折。千年等一回，等的就是这一回！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"c3c5fc90-1297-11e6-9174-1c872c481c7f",
     * "TC_GID":"94575fa9-1296-11e6-9174-1c872c481c7f","TC_ClassName":"新货到了","TC_ClassCode":"023","TM_Name":null,
     * "TM_Content":"#店铺名称#淘货总动员，百余新品明日上架，感谢您一直以来的关照，8折放送，愿实惠伴您清凉一夏。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"c3ce3b79-1297-11e6-9174-1c872c481c7f",
     * "TC_GID":"94575fa9-1296-11e6-9174-1c872c481c7f","TC_ClassName":"新货到了","TC_ClassCode":"023","TM_Name":null,
     * "TM_Content":"#店铺名称#夏季新品8折首发，更有3种热卖商品5折放送，本优惠截止X月X日，绝对实惠让你清凉一夏。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"c3d53477-1297-11e6-9174-1c872c481c7f",
     * "TC_GID":"94575fa9-1296-11e6-9174-1c872c481c7f","TC_ClassName":"新货到了","TC_ClassCode":"023","TM_Name":null,
     * "TM_Content":"亲们，换季了，该采购了！#店铺名称#百余新货今日统一上架，绝对耳目一新，肯定有你喜欢的，快来淘吧~","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"c3de3a6f-1297-11e6-9174-1c872c481c7f",
     * "TC_GID":"94575fa9-1296-11e6-9174-1c872c481c7f","TC_ClassName":"新货到了","TC_ClassCode":"023","TM_Name":null,
     * "TM_Content":"来抢购啦！X月X日至X月X日#店铺名称#新品上线，全店热促，第一件89元，第二件只要49！双倍实惠带回家。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"c3e4a3ee-1297-11e6-9174-1c872c481c7f",
     * "TC_GID":"94575fa9-1296-11e6-9174-1c872c481c7f","TC_ClassName":"新货到了","TC_ClassCode":"023","TM_Name":null,
     * "TM_Content":"#店铺名称#新货到啦！你期盼已久的XX商品，全市首发，仅99元，仅在#店铺名称#，快来抢！来晚就没了！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"c3eb5f23-1297-11e6-9174-1c872c481c7f",
     * "TC_GID":"94575fa9-1296-11e6-9174-1c872c481c7f","TC_ClassName":"新货到了","TC_ClassCode":"023","TM_Name":null,
     * "TM_Content":"新货到啦~#店铺名称#本年度最大规模上货，就在本周，老店新品，更有8折感恩回馈，恭候光临品鉴。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"ed8d66da-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"b12774e1-1296-11e6-9174-1c872c481c7f","TC_ClassName":"中秋佳节","TC_ClassCode":"028","TM_Name":null,
     * "TM_Content":"祝福中秋佳节快乐，月圆人圆事事圆，愿您过的每一天都象十五的月亮一样成功，祝您一切圆满美好！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,
     * "CY_GID":null,"TM_Group":3},{"GID":"ed9b5220-1299-11e6-9174-1c872c481c7f","TC_GID":"b12774e1-1296-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"中秋佳节","TC_ClassCode":"028","TM_Name":null,
     * "TM_Content":"中秋佳节将来到，百忙之中也要抽出时间过佳节，在这么个团团圆圆的日子，更加需要家人的关心，这样才会有更多的动力，愿您全家幸福快乐，您的生意蒸蒸日上！","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"eda2fc1a-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"b12774e1-1296-11e6-9174-1c872c481c7f","TC_ClassName":"中秋佳节","TC_ClassCode":"028","TM_Name":null,
     * "TM_Content":"中秋祝福：有你月缺也月圆，没你月圆也月缺。希望能够见到你，代表天天都月圆。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,
     * "CY_GID":null,"TM_Group":3},{"GID":"edaefcfa-1299-11e6-9174-1c872c481c7f","TC_GID":"b12774e1-1296-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"中秋佳节","TC_ClassCode":"028","TM_Name":null,"TM_Content":"中秋将至，奉上一个月饼，配料：五克快乐枣，一把关心米，三钱友情水，用幽默扎捆，用手机送达;保质期：农历八月十五前。",
     * "TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},
     * {"GID":"edb74b29-1299-11e6-9174-1c872c481c7f","TC_GID":"b12774e1-1296-11e6-9174-1c872c481c7f","TC_ClassName":"中秋佳节","TC_ClassCode":"028",
     * "TM_Name":null,"TM_Content":"中秋祝愿，愿我的客户事业有月亮般的高度，能力有天空般的广度，薪水有月饼般的弧度，再祝您中秋能够欢度！","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,
     * "TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},{"GID":"edc27dfa-1299-11e6-9174-1c872c481c7f",
     * "TC_GID":"b12774e1-1296-11e6-9174-1c872c481c7f","TC_ClassName":"中秋佳节","TC_ClassCode":"028","TM_Name":null,
     * "TM_Content":"明月当空照，中秋已来到。佳节配美酒，娇妻投怀抱。岁岁都幸福，处处有欢笑。","TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,
     * "CY_GID":null,"TM_Group":3},{"GID":"edcae32c-1299-11e6-9174-1c872c481c7f","TC_GID":"b12774e1-1296-11e6-9174-1c872c481c7f",
     * "TC_ClassName":"中秋佳节","TC_ClassCode":"028","TM_Name":null,"TM_Content":"又到一年中秋时，星星明亮月亮圆，我托月光送祝福，问候跟随悄悄至，愿我们在合作的道路上像月亮一样圆圆满满。中秋节快乐！",
     * "TM_Creator":null,"TM_Update":null,"TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3},
     * {"GID":"edd34abb-1299-11e6-9174-1c872c481c7f","TC_GID":"b12774e1-1296-11e6-9174-1c872c481c7f","TC_ClassName":"中秋佳节","TC_ClassCode":"028",
     * "TM_Name":null,"TM_Content":"心到，想到，得到，看到，闻到，吃到，福到，运到，财到，中秋节还没到，但愿我的祝福第一个到。提前祝你中秋节快乐！天天好心情！","TM_Creator":null,"TM_Update":null,
     * "TM_CreatorTime":null,"TM_UpdateTime":null,"CY_GID":null,"TM_Group":3}]}
     */

    private boolean success;
    private Object code;
    private String msg;
    private DataBean data;

    public boolean isSuccess() { return success;}

    public void setSuccess(boolean success) { this.success = success;}

    public Object getCode() { return code;}

    public void setCode(Object code) { this.code = code;}

    public String getMsg() { return msg;}

    public void setMsg(String msg) { this.msg = msg;}

    public DataBean getData() { return data;}

    public void setData(DataBean data) { this.data = data;}

    public static class DataBean
    {
        private List<TempClassListBean> tempClassList;
        private List<TempManagerListBean> tempManagerList;

        public List<TempClassListBean> getTempClassList() { return tempClassList;}

        public void setTempClassList(List<TempClassListBean> tempClassList) { this.tempClassList = tempClassList;}

        public List<TempManagerListBean> getTempManagerList() { return tempManagerList;}

        public void setTempManagerList(List<TempManagerListBean> tempManagerList) { this.tempManagerList = tempManagerList;}

        public static class TempClassListBean
        {
            /**
             * GID : 94575fa9-1296-11e6-9174-1c872c481c7f
             * TC_ClassName : 新货到了
             * TC_ClassCode : 023
             * TC_Creator : null
             * TC_Update : null
             * TC_CreatorTime : null
             * TC_UpdateTime : null
             * TC_Group : 3
             */

            private String GID;
            private String TC_ClassName;
            private String TC_ClassCode;
            private Object TC_Creator;
            private Object TC_Update;
            private Object TC_CreatorTime;
            private Object TC_UpdateTime;
            private int TC_Group;

            public String getGID() { return GID;}

            public void setGID(String GID) { this.GID = GID;}

            public String getTC_ClassName() { return TC_ClassName;}

            public void setTC_ClassName(String TC_ClassName) { this.TC_ClassName = TC_ClassName;}

            public String getTC_ClassCode() { return TC_ClassCode;}

            public void setTC_ClassCode(String TC_ClassCode) { this.TC_ClassCode = TC_ClassCode;}

            public Object getTC_Creator() { return TC_Creator;}

            public void setTC_Creator(Object TC_Creator) { this.TC_Creator = TC_Creator;}

            public Object getTC_Update() { return TC_Update;}

            public void setTC_Update(Object TC_Update) { this.TC_Update = TC_Update;}

            public Object getTC_CreatorTime() { return TC_CreatorTime;}

            public void setTC_CreatorTime(Object TC_CreatorTime) { this.TC_CreatorTime = TC_CreatorTime;}

            public Object getTC_UpdateTime() { return TC_UpdateTime;}

            public void setTC_UpdateTime(Object TC_UpdateTime) { this.TC_UpdateTime = TC_UpdateTime;}

            public int getTC_Group() { return TC_Group;}

            public void setTC_Group(int TC_Group) { this.TC_Group = TC_Group;}
        }

        public static class TempManagerListBean
        {
            /**
             * GID : 3b292aa6-1299-11e6-9174-1c872c481c7f
             * TC_GID : 9472fb65-1296-11e6-9174-1c872c481c7f
             * TC_ClassName : 三八妇女节
             * TC_ClassCode : 026
             * TM_Name : null
             * TM_Content : 妇女节到，祝福魅力无敌，聪明伶俐；神采飞扬，常带笑意；端庄漂亮，青春靓丽；聪明赛孔明，英明如上帝的你，妇女节开心又快乐！
             * TM_Creator : null
             * TM_Update : null
             * TM_CreatorTime : null
             * TM_UpdateTime : null
             * CY_GID : null
             * TM_Group : 3
             */

            private String GID;
            private String TC_GID;
            private String TC_ClassName;
            private String TC_ClassCode;
            private Object TM_Name;
            private String TM_Content;
            private Object TM_Creator;
            private Object TM_Update;
            private Object TM_CreatorTime;
            private Object TM_UpdateTime;
            private Object CY_GID;
            private int TM_Group;

            public String getGID() { return GID;}

            public void setGID(String GID) { this.GID = GID;}

            public String getTC_GID() { return TC_GID;}

            public void setTC_GID(String TC_GID) { this.TC_GID = TC_GID;}

            public String getTC_ClassName() { return TC_ClassName;}

            public void setTC_ClassName(String TC_ClassName) { this.TC_ClassName = TC_ClassName;}

            public String getTC_ClassCode() { return TC_ClassCode;}

            public void setTC_ClassCode(String TC_ClassCode) { this.TC_ClassCode = TC_ClassCode;}

            public Object getTM_Name() { return TM_Name;}

            public void setTM_Name(Object TM_Name) { this.TM_Name = TM_Name;}

            public String getTM_Content() { return TM_Content;}

            public void setTM_Content(String TM_Content) { this.TM_Content = TM_Content;}

            public Object getTM_Creator() { return TM_Creator;}

            public void setTM_Creator(Object TM_Creator) { this.TM_Creator = TM_Creator;}

            public Object getTM_Update() { return TM_Update;}

            public void setTM_Update(Object TM_Update) { this.TM_Update = TM_Update;}

            public Object getTM_CreatorTime() { return TM_CreatorTime;}

            public void setTM_CreatorTime(Object TM_CreatorTime) { this.TM_CreatorTime = TM_CreatorTime;}

            public Object getTM_UpdateTime() { return TM_UpdateTime;}

            public void setTM_UpdateTime(Object TM_UpdateTime) { this.TM_UpdateTime = TM_UpdateTime;}

            public Object getCY_GID() { return CY_GID;}

            public void setCY_GID(Object CY_GID) { this.CY_GID = CY_GID;}

            public int getTM_Group() { return TM_Group;}

            public void setTM_Group(int TM_Group) { this.TM_Group = TM_Group;}
        }
    }
}
