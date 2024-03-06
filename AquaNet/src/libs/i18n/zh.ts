import { EN_REF_USER, type EN_REF_Welcome } from "./en_ref";

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

const zhUser: typeof EN_REF_USER = {
  'UseHome.ServerRank': '服务器排名',
  'UseHome.Ratting': 'DX B50分',
  'UseHome.Statistics': '统计数据',
  'UseHome.Accuracy': '准确度',
  'UseHome.DXScore': 'DX 得分',
  'UseHome.PlayActivity': '游戏活动',
  'UseHome.Plays': '出勤次',
  'UseHome.PlayTime': '出勤时间',
  'UseHome.FirstSeen': '发现新大陆',
  'UseHome.LastSeen': '上次出勤',
  'UseHome.Version': '最新最热否',
  'UseHome.RecentScores': '我的战绩'
}

export const ZH = { ...zhUser, ...zhWelcome }
