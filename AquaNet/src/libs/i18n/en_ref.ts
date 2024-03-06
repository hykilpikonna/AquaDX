export const EN_REF_USER = {
  'UseHome.ServerRank': 'Server Rank',
  'UseHome.Ratting': 'DX Ratting',
  'UseHome.Statistics': 'Statistics',
  'UseHome.Accuracy': 'Accuracy',
  'UseHome.DXScore': 'DX Score',
  'UseHome.PlayActivity': ' Play Activity',
  'UseHome.Plays': 'Plays',
  'UseHome.PlayTime': 'Play Time',
  'UseHome.FirstSeen': 'First Seen',
  'UseHome.LastSeen': 'Last Seen',
  'UseHome.Version': 'Last Version',
  'UseHome.RecentScores': 'Recent Scores'
}

export const EN_REF_Welcome = {
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

export const EN_REF = { ...EN_REF_USER, ...EN_REF_Welcome }

export type LocalizedMessages = typeof EN_REF
