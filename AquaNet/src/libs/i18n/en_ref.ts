export const EN_REF_USER = {
  'UserHome.ServerRank': 'Server Rank',
  'UserHome.DXRating': 'DX Rating',
  'UserHome.Rating': 'Rating',
  'UserHome.Statistics': 'Statistics',
  'UserHome.Accuracy': 'Accuracy',
  'UserHome.DXScore': 'DX Score',
  'UserHome.PlayActivity': ' Play Activity',
  'UserHome.Plays': 'Plays',
  'UserHome.PlayTime': 'Play Time',
  'UserHome.FirstSeen': 'First Seen',
  'UserHome.LastSeen': 'Last Seen',
  'UserHome.Version': 'Last Version',
  'UserHome.RecentScores': 'Recent Scores',
  'UserHome.NoData': 'No data in the past ${days} days',
  'UserHome.Game.Mai2': "Mai",
  'UserHome.Game.Chu3': "Chuni",
  'UserHome.Game.Ongeki': "Ongeki",
  'UserHome.UnknownSong': "(unknown song)",
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
