import { AQUA_HOST, DATA_HOST } from './config'
import type { TrendEntry } from './generalTypes'
import type { MaimaiUserSummaryEntry } from './maimaiTypes'





export async function getMaimai(endpoint: string, params: any) {
  return await fetch(`${AQUA_HOST}/Maimai2Servlet/${endpoint}`, {
    method: 'POST',
    body: JSON.stringify(params)
  }).then(res => res.json())
}

export async function getMaimaiAllMusic(): Promise<{ [key: string]: any }> {
  return fetch(`${DATA_HOST}/maimai/meta/00/all-music.json`).then(it => it.json())
}