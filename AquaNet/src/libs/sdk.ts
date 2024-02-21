import { AQUA_HOST } from "./config";

interface RequestInitWithParams extends RequestInit {
  params?: { [index: string]: string }
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

export async function post(endpoint: string, params: any, init?: RequestInitWithParams): Promise<any> {
  // Add token if exists
  const token = localStorage.getItem('token')
  if (token && !('token' in params)) params = { ...(params ?? {}), token }

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
    throw new Error(`${text}`)
  }

  return res.json()
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

async function confirmEmail(token: string) {
  return await post('/api/v2/user/confirm-email', { token })
}

export const USER = {
  register,
  login,
  confirmEmail,
  isLoggedIn: () => !!localStorage.getItem('token')
}