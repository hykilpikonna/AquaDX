import { defineConfig } from 'vite'
import { svelte } from '@sveltejs/vite-plugin-svelte'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [svelte()],
  define: {
    APP_VERSION: JSON.stringify(process.env.npm_package_version),
  },
})
