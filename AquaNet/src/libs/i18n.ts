const EN_REF_USER = {
  'TEST': 'TEST'
}
const EN_REF_Welcome = {
  'back': 'Back',
  'email': 'Email',
  'password': 'Password',
  'username': 'Username',
  'welcome.btn-login': 'Log in',
  'welcome.btn-signup': 'Sign up',
  'welcome.email-password-missing': 'Email and password are required',
  'welcome.username-missing': 'Username/email is required',
  'welcome.waiting-turnstile': 'Waiting for Turnstile to verify your network environment...',
  'welcome.turnstile-error': 'Error verifying your network environment. Please turn off your VPN and try again.',
  'welcome.turnstile-timeout': 'Network verification timed out. Please try again.',
  'welcome.verification-sent': 'A verification email has been sent to ${email}. Please check your inbox!',
  'welcome.verify-state-0': 'You haven\'t verified your email. A verification email had been sent to your inbox less than a minute ago. Please check your inbox!',
  'welcome.verify-state-1': 'You haven\'t verified your email. We\'ve already sent 3 emails over the last 24 hours so we\'ll not send another one. Please check your inbox!',
  'welcome.verify-state-2': 'You haven\'t verified your email. We just sent you another verification email. Please check your inbox!',
  'welcome.verifying': 'Verifying your email... please wait.',
  'welcome.verified': 'Your email has been verified! You can now log in now.',
  'welcome.verification-failed': 'Verification failed: ${message}. Please try again.',
}

const zhWelcome ={
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
const zhUser={
  'test':'test'
}


type LocalizationMessages = {
  [key: string]: string;
};

type Lang = 'en' | 'zh'

const allI18n: Record<Lang, LocalizationMessages> = {
  en: { ...EN_REF_USER, ...EN_REF_Welcome },
  zh: { ...zhUser, ...zhWelcome }
};


const msgs: Record<Lang, LocalizationMessages> = {
  en: allI18n.en,
  zh: allI18n.zh
}


let lang: Lang = 'en'

// Infer language from browser
if (navigator.language.startsWith('zh')) {
  lang = 'zh'
}

export function t(key: keyof LocalizationMessages, variables?: { [index: string]: string }) {
  if (variables) {
    return msgs[lang][key].replace(/\${(.*?)}/g, (_:string, v: string | number) => variables[v])
  }
  return msgs[lang][key]
}
