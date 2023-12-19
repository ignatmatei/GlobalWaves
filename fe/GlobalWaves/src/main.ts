import {createApp} from 'vue'
import App from './App.vue'
import PrimeVue from 'primevue/config'
import {songs} from './assets/songs'
const app = createApp(App)

app.use(PrimeVue)
app.mount('#app')

// Path: fe/GlobalWaves/src/App.vue