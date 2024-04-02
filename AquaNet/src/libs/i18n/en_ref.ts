export const EN_REF_USER = {
  'UserHome.ServerRank': 'Server Rank',
  'UserHome.DXRating': 'DX Rating',
  'UserHome.Rating': 'Rating',
  'UserHome.Statistics': 'Statistics',
  'UserHome.Accuracy': 'Accuracy',
  'UserHome.MaxCombo': 'Max Combo',
  'UserHome.FullCombo': 'Full Combo',
  'UserHome.AllPerfect': 'All Perfect',
  'UserHome.DXScore': 'DX Score',
  'UserHome.Score': 'Score',
  'UserHome.PlayActivity': ' Play Activity',
  'UserHome.Plays': 'Plays',
  'UserHome.PlayTime': 'Play Time',
  'UserHome.FirstSeen': 'First Seen',
  'UserHome.LastSeen': 'Last Seen',
  'UserHome.Version': 'Last Version',
  'UserHome.RecentScores': 'Recent Scores',
  'UserHome.NoData': 'No data in the past ${days} days',
  'UserHome.UnknownSong': "(unknown song)",
  'UserHome.Settings': 'Settings',
  'UserHome.NoValidGame': "The user hasn't played any game yet.",
  'UserHome.ShowRanksDetails': "Click to show details",
  'UserHome.RankDetail.Title': 'Achievement Details',
  'UserHome.RankDetail.Level': "Level",
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

export const EN_REF_LEADERBOARD = {
  'Leaderboard.Title': 'Server Leaderboard',
  'Leaderboard.Rank': 'Rank',
  'Leaderboard.Rating': 'Rating',
  'Leaderboard.Accuracy': 'Accuracy',
  'Leaderboard.FC': 'FC',
  'Leaderboard.AP': 'AP',
}

export const EN_REF_GENERAL = {
  'game.mai2': "Mai",
  'game.chu3': "Chuni",
  'game.ongeki': "Ongeki",
  'game.wacca': "Wacca",
  'status.error': "Error",
  'status.error.hint': 'Something went wrong, please try again later or ',
  'status.error.hint.link': 'join our discord for support.',
  'status.detail': 'Detail: ${detail}',
  'action.refresh': 'Refresh',
  'action.cancel': 'Cancel',
  'action.confirm': 'Confirm',
}

export const EN_REF_HOME = {
  'home.nav.portal': 'Portal',
  'home.nav.link-card': 'Link Card',
  'home.nav.game-setup': 'Game Setup',
  'home.manage-cards': 'Manage Cards',
  'home.manage-cards-description': 'Link, unlink, and manage your cards.',
  'home.link-card': 'Link Card',
  'home.link-cards-description': 'Link your Amusement IC / Aime card to play games.',
  'home.join-discord': 'Join Discord',
  'home.join-discord-description': 'Join our Discord server to chat with other players and get help.',
  'home.setup': 'Setup Connection',
  'home.setup-description': 'If you own a cab or arcade setup, begin setting up the connection.',
}

export const EN_REF_SETTINGS = {
  'settings.title': 'Settings',
  'settings.tabs.profile': 'Profile',
  'settings.tabs.game': 'Game',
}

export const EN_REF = { ...EN_REF_USER, ...EN_REF_Welcome, ...EN_REF_GENERAL,
  ...EN_REF_LEADERBOARD, ...EN_REF_HOME, ...EN_REF_SETTINGS }

export type LocalizedMessages = typeof EN_REF
