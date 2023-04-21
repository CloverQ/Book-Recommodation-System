import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import Recommodation from '../views/Recommodation.vue'
import AdminBook from '../views/admin/admin-book.vue'
import BookInfo from '../views/BookInfo.vue'
import Search from '../views/Search.vue'
import Exact from '../views/Exact.vue'
import Test from '../views/Test.vue'
import Profile from '../views/Profile.vue'
import Edit from '../views/Edit.vue'
import Wishlist from '../views/Wishlist.vue'
import OtherProfile from '../views/OthersProfile.vue'
import ResetPwd from '../views/ResetPwd.vue'
import { SearchOutlined } from '@ant-design/icons-vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/recommodation',
    name: 'Recommodation',
    component: Recommodation
  },
  {
    path: '/admin/book',
    name: 'AdminBook',
    component: AdminBook
  },
  {
    path: '/books/:id',
    name: 'BookInfo',
    component: BookInfo,
  },
  {
    path: '/search/:search',
    name: 'Search',
    component: Search
  },
  {
    path: '/exact',
    name: 'Exact',
    component: Exact
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  {
    path: '/user/profile/:id',
    name: 'Profile',
    component: Profile
  },
  {
    path: '/user/other/:id',
    name: 'OtherProfile',
    component: OtherProfile
  },
  {
    path: '/user/edit/:id',
    name: 'Edit',
    component: Edit
  },
  {
    path: '/user/wishlist/:id',
    name: 'Wishlist',
    component: Wishlist
  },
  {
    path: '/user/reset/:id',
    name: 'ResetPwd',
    component: ResetPwd
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
