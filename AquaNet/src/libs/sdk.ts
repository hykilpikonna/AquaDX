import { AQUA_HOST, DATA_HOST } from "./config";
import type {
  AllMusic,
  Card,
  CardSummary,
  GenericGameSummary,
  GenericRanking,
  MusicMeta,
  TrendEntry,
  UserMe
} from "./generalTypes";
import type { GameName } from "./scoring";

interface RequestInitWithParams extends RequestInit {
  params?: { [index: string]: string }
  localCache?: boolean
}

/**
 * Modify a fetch url
 *
 * @param input Fetch url input
 * @param callback Callback for modification
 */
export function reconstructUrl(input: URL | RequestInfo, callback: (url: URL) => URL | void): RequestInfo | URL {
  let u = new URL((input instanceof Request) ? input.url : input);
  const result = callback(u)
  if (result) u = result
  if (input instanceof Request) {
    // @ts-ignore
    return { url: u, ...input }
  }
  return u
}

/**
 * Fetch with url parameters
 */
export function fetchWithParams(input: URL | RequestInfo, init?: RequestInitWithParams): Promise<Response> {
  return fetch(reconstructUrl(input, u => {
    u.search = new URLSearchParams(init?.params ?? {}).toString()
  }), init)
}

let cache: { [index: string]: any } = {}

export async function post(endpoint: string, params: any, init?: RequestInitWithParams): Promise<any> {
  // Add token if exists
  const token = localStorage.getItem('token')
  if (token && !('token' in params)) params = { ...(params ?? {}), token }

  if (init?.localCache) {
    const cached = cache[endpoint + JSON.stringify(params) + JSON.stringify(init)]
    if (cached) return cached
  }

  let res = await fetchWithParams(AQUA_HOST + endpoint, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    params,
    ...init
  }).catch(e => {
    console.error(e)
    throw new Error('Network error')
  })

  if (!res.ok) {
    const text = await res.text()
    console.error(`${res.status}: ${text}`)

    // If 400 invalid token is caught, should invalidate the token and redirect to signin
    if (text === 'Invalid token') {
      localStorage.removeItem('token')
      window.location.href = '/'
    }

    // Try to parse as json
    let json
    try {
      json = JSON.parse(text)
    } catch (e) {
      throw new Error(text)
    }
    if (json.error) throw new Error(json.error)
  }

  const ret = res.json()
  cache[endpoint + JSON.stringify(params) + JSON.stringify(init)] = ret

  return ret
}

/**
 * aqua.net.UserRegistrar
 *
 * @param user
 */
async function register(user: { username: string, email: string, password: string, turnstile: string }) {
  return await post('/api/v2/user/register', user)
}
async function login(user: { email: string, password: string, turnstile: string }) {
  const data = await post('/api/v2/user/login', user)

  // Put token into local storage
  localStorage.setItem('token', data.token)
}

const isLoggedIn = () => !!localStorage.getItem('token')
const ensureLoggedIn = () => !isLoggedIn() && (window.location.href = '/')

export const USER = {
  register,
  login,
  confirmEmail: (token: string) =>
    post('/api/v2/user/confirm-email', { token }),
  me: (): Promise<UserMe> => {
    ensureLoggedIn()
    return post('/api/v2/user/me', {})
  },
  keychip: (): Promise<string> =>
    post('/api/v2/user/keychip', {}).then(it => it.keychip),
  setting: (key: string, value: string) =>
    post('/api/v2/user/setting', { key: key === 'password' ? 'pwHash' : key, value }),
  isLoggedIn,
  ensureLoggedIn,
}

export const CARD = {
  summary: (cardId: string): Promise<{card: Card, summary: CardSummary}> =>
    post('/api/v2/card/summary', { cardId }),
  link: (props: { cardId: string, migrate: string }) =>
    post('/api/v2/card/link', props),
  unlink: (cardId: string) =>
    post('/api/v2/card/unlink', { cardId }),
}

export const GAME = {
  trend: (username: string, game: GameName): Promise<TrendEntry[]> =>
    post(`/api/v2/game/${game}/trend`, { username }),
  userSummary: (username: string, game: GameName): Promise<GenericGameSummary> =>
    post(`/api/v2/game/${game}/user-summary`, { username }),
  ranking: (game: GameName): Promise<GenericRanking[]> =>
    post(`/api/v2/game/${game}/ranking`, { }),

}

export const DATA = {
  allMusic: (game: GameName): Promise<AllMusic> =>
    fetch(`${DATA_HOST}/d/${game}/00/all-music.json`).then(it => it.json())
}
