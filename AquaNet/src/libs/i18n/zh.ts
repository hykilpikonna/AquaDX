import { EN_REF_GENERAL, EN_REF_HOME, EN_REF_LEADERBOARD, EN_REF_USER, type EN_REF_Welcome } from "./en_ref";

const zhUser: typeof EN_REF_USER = {
  'UserHome.ServerRank': '服务器排名',
  'UserHome.DXRating': 'DX B50',
  'UserHome.Rating': '评分',
  'UserHome.Statistics': '统计数据',
  'UserHome.Accuracy': '准确率',
  'UserHome.MaxCombo': '最大连击',
  'UserHome.FullCombo': '全连曲目',
  'UserHome.AllPerfect': '完美曲目',
  'UserHome.DXScore': 'DX 总分',
  'UserHome.Score': '总分',
  'UserHome.PlayActivity': '游戏活动',
  'UserHome.Plays': '出勤次数',
  'UserHome.PlayTime': '游玩时间',
  'UserHome.FirstSeen': '发现新大陆',
  'UserHome.LastSeen': '上次出勤',
  'UserHome.Version': '游戏版本',
  'UserHome.RecentScores': '成绩',
  'UserHome.NoData': '过去 ${days} 天内没有玩过',
  'UserHome.UnknownSong': "(未知曲目)",
  'UserHome.Settings': '设置',
  'UserHome.NoValidGame': "用户还没有玩过游戏",
  'UserHome.ShowRanksDetails': "点击显示评分详细",
  'UserHome.RankDetail.Title': '评分详细',
  'UserHome.RankDetail.Level': "等级",
}

const zhWelcome: typeof EN_REF_Welcome = {
  'back': '返回',
  'email': '邮箱',
  'password': '密码',
  'username': '用户名',
  'welcome.btn-login': '登录',
  'welcome.btn-signup': '注册',
  'welcome.email-password-missing': '邮箱和密码必须填哦',
  'welcome.username-missing': '用户名/邮箱必须填哦',
  'welcome.waiting-turnstile': '正在验证网络环境...',
  'welcome.turnstile-error': '验证网络环境出错了，请关闭VPN后重试',
  'welcome.turnstile-timeout': '验证网络环境超时了，请重试',
  'welcome.verification-sent': '验证邮件已发送至 ${email}，请翻翻收件箱',
  'welcome.verify-state-0': '您还没有验证邮箱哦！验证邮件一分钟内刚刚发到您的邮箱，请翻翻收件箱',
  'welcome.verify-state-1': '您还没有验证邮箱哦！我们在过去的24小时内已经发送了3封验证邮件，所以我们不会再发送了，请翻翻收件箱',
  'welcome.verify-state-2': '您还没有验证邮箱哦！我们刚刚又发送了一封验证邮件，请翻翻收件箱',
  'welcome.verifying': '正在验证邮箱...请稍等',
  'welcome.verified': '您的邮箱已经验证成功！您现在可以登录了',
  'welcome.verification-failed': '验证失败：${message}。请重试',
}

const zhLeaderboard: typeof EN_REF_LEADERBOARD = {
  'Leaderboard.Title': '排行榜',
  'Leaderboard.Rank': '排名',
  'Leaderboard.Rating': '评分',
  'Leaderboard.Accuracy': '准确率',
  'Leaderboard.FC': 'FC',
  'Leaderboard.AP': 'AP',
}

const zhGeneral: typeof EN_REF_GENERAL = {
  'game.mai2': "舞萌",
  'game.chu3': "中二",
  'game.ongeki': "音击",
  'game.wacca': "Wacca",
  "status.error": "发生错误",
  "status.error.hint": "出了一些问题，请稍后刷新重试或者",
  "status.error.hint.link": "加我们的 Discord 群问一问",
  "status.detail": "详细信息：${detail}",
  "action.refresh": "刷新",
  "action.cancel": "取消",
  "action.confirm": "确认",
}

const zhHome: typeof EN_REF_HOME = {
  'home.nav.portal': "主页",
  'home.nav.link-card': "绑卡",
  'home.nav.game-setup': "连接设置",
  'home.manage-cards': '管理游戏卡',
  'home.manage-cards-description': '绑定、解绑、管理游戏数据卡',
  'home.link-card': '绑定游戏卡',
  'home.link-cards-description':'绑定游戏数据卡 (Amusement IC 或 Aime 卡) 后才可以访问游戏存档哦',
  'home.join-discord': '加入 Discord',
  'home.join-discord-description': '加入我们的 Discord 群，与其他玩家聊天、获取帮助',
  'home.setup': '连接私服',
  'home.setup-description': '如果您有街机框体或者手台，点击这里设置服务器的连接',
}

export const ZH = { ...zhUser, ...zhWelcome, ...zhGeneral,
  ...zhLeaderboard, ...zhHome }
