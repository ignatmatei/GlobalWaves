import {createApp} from 'vue'
import App from './App.vue'
import PrimeVue from 'primevue/config'
import router from './router'
const app = createApp(App)
app.use(PrimeVue)
   .use(router)
app.mount('#app')

// Path: fe/GlobalWaves/src/App.vue