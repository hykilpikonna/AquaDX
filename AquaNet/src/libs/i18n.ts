import { EN_REF, type LocalizedMessages } from "./i18n/en_ref";
import { ZH } from "./i18n/zh";

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

export function t(key: keyof LocalizedMessages, variables?: { [index: string]: string }) {
  if (variables) {
    return msgs[lang][key].replace(/\${(.*?)}/g, (_: string, v: string | number) => variables[v])
  }
  return msgs[lang][key]
}
