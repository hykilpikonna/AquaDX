import { defineConfig } from 'vite'
import { svelte } from '@sveltejs/vite-plugin-svelte'
import UnoCSS from '@unocss/svelte-scoped/vite'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    UnoCSS(),
    svelte()
  ],
  define: {
    APP_VERSION: JSON.stringify(process.env.npm_package_version),
  },
})
