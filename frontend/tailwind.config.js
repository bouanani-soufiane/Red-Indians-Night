import fluid, { extract } from 'fluid-tailwind'
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
    extract
  ],
  theme: {
    extend: {},
  },
  plugins: [
    fluid
  ],
}

