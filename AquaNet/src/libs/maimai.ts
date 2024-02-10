import {aqua_host} from "./config";
import type {TrendEntry} from "./generalTypes";


const multTable = [
    [100.5, 22.4, "SSSp"],
    [100, 21.6, "SSS"],
    [99.5, 21.1, "SSp"],
    [99, 20.8, "SS"],
    [98, 20.3, "Sp"],
    [97, 20, "S"],
    [94, 16.8, "AAA"],
    [90, 15.2, "AA"],
    [80, 13.6, "A"]
]


export function getMult(achievement: number) {
    achievement /= 10000
    for (let i = 0; i < multTable.length; i++) {
        if (achievement >= (multTable[i][0] as number)) return multTable[i]
    }
    return [0, 0, 0]
}


export async function getMaimai(endpoint: string, params: any) {
    return await fetch(`${aqua_host}/Maimai2Servlet/${endpoint}`, {
        method: "POST",
        body: JSON.stringify(params)
    }).then(res => res.json())
}

export async function getMaimaiApi(endpoint: string, params: any) {
    let url = new URL(`${aqua_host}/api/game/maimai2new/${endpoint}`)
    Object.keys(params).forEach(key => url.searchParams.append(key, params[key]))
    return await fetch(url).then(res => res.json())
}

export async function getMaimaiTrend(userId: number): Promise<TrendEntry[]> {
    return await getMaimaiApi("trend", {userId})
}