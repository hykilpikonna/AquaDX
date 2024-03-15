import { EN_REF, type LocalizedMessages } from "./i18n/en_ref";
import { ZH } from "./i18n/zh";
import type { GameName } from "./scoring";

type Lang = 'en' | 'zh'

const msgs: Record<Lang, LocalizedMessages> = {
  en: EN_REF,
  zh: ZH
}


let lang: Lang = 'en'

// Infer language from browser
if (navigator.language.startsWith('zh')) {
  lang = 'zh'
}

export function t(key: keyof LocalizedMessages, variables?: { [index: string]: any }) {
  if (variables) {
    return msgs[lang][key].replace(/\${(.*?)}/g, (_: string, v: string | number) => variables[v] + "")
  }
  return msgs[lang][key]
}
Object.assign(window, { t })

export const GAME_TITLE: { [key in GameName]: string } =
  {chu3: t("game.chu3"), mai2: t("game.mai2"), ongeki: t("game.ongeki")}
