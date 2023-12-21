import { createRouter, createWebHistory } from 'vue-router'
import EntryPoint from '../pages/EntryPoint.vue'
import SignUpForm from '../pages/SignUpForm.vue'
import LogInForm from '../pages/LogInForm.vue'
import HomePage from '../pages/HomePage.vue'
import FavouriteSongs from '../pages/FavouriteSongs.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'entry-point',
      component: EntryPoint
    },
    {
      path: '/sign-up',
      name: 'sign-up',
      component: SignUpForm
    },
    {
      path: '/log-in',
      name: 'log-in',
      component: LogInForm
    },
    {
      path: '/home',
      name: 'home',
      component: HomePage
    },
    {
      path: '/favourite-songs',
      name: 'favourite-songs',
      component: FavouriteSongs
    }
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // }
  ]
})

export default router
