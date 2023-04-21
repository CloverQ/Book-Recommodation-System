<template>
  <a-layout-header class="header">
    <a-menu :theme="theme" mode="horizontal" :style="{ lineHeight: '64px' }">
      <a-menu-item key="" disabled>
        Book Recommodation
      </a-menu-item>
      <a-menu-item key="/">
        <router-link to="/">Home</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/book" v-if="user.userRole === 'admin'">
        <router-link to="/admin/book">Book Manage</router-link>
      </a-menu-item>
      <a-menu-item key="/recommodation" v-if="user.userRole === 'user'">
        <router-link to="/recommodation">Recommendation</router-link>
      </a-menu-item>
      <a-menu-item key="/exact">
        <router-link to="/exact">Exact Search</router-link>
      </a-menu-item>
      <a-menu-item>
        <div class="certain-category-search-wrapper" style="width: 250px">
          <a-auto-complete class="certain-category-search" size="large" style="width: 100%" v-model:value="input_value"
            @keyup.enter="search">
            <a-input placeholder="">
              <template #suffix><search-outlined class="certain-category-icon" /></template>
            </a-input>
          </a-auto-complete>
        </div>
      </a-menu-item>
      <a-popconfirm title="Confirm logout?" ok-text="Yes" cancel-text="No" @confirm="logout()">
        <a class="login-menu" v-if="user.userId">
          <span>Logout</span>
        </a>
      </a-popconfirm>
      <a-switch :checked="theme === 'dark'" @change="changeTheme" />
      <a class="login-menu" v-if="user.userId" @click="toProfile">
        <span>Hi, {{ user.userName }}</span>
      </a>
      <a class="login-menu" v-if="!user.userId" @click="showLoginModal">
        <span>Login</span>
      </a>
      <a class="login-menu" v-if="!user.userId" @click="showRegisterModal">
        <span>Register</span>
      </a>
    </a-menu>
    <a-modal title="Login" v-model:visible="loginModalVisible" :confirm-loading="loginModalLoading" @ok="login">
      <a-form :model="loginUser" :label-col="{ span: 7 }" :wrapper-col="{ span: 14 }">
        <a-form-item label="UserName">
          <a-input id="input" v-model:value="loginUser.userName" />
        </a-form-item>
        <a-form-item label="Password">
          <a-input id="input" v-model:value="loginUser.password" type="password" />
        </a-form-item>
      </a-form>
    </a-modal>
    <a-modal title="Register" v-model:visible="registerModalVisible" :confirm-loading="registerModalLoading" @ok="register">
      <a-form :model="registerUser" :label-col="{ span: 7 }" :wrapper-col="{ span: 14 }">
        <a-form-item label="UserName">
          <a-input id="input" v-model:value="registerUser.userName" />
        </a-form-item>
        <a-form-item label="Password">
          <a-input id="input" v-model:value="registerUser.password" type="password" />
        </a-form-item>
      </a-form>
    </a-modal>
  </a-layout-header>
</template>

<script lang="ts">
import { defineComponent, ref, computed, reactive, toRefs  } from 'vue';
import axios from 'axios';
import store from '@/store';
import { message } from 'ant-design-vue';

declare let hexMd5: any;
declare let KEY: any;

export default defineComponent({
  name: 'the-header',
  setup() {
    const input_value = ref("")
    const state = reactive({
      theme: 'dark',
    });
    document.documentElement.setAttribute('theme', 'dark')
    const changeTheme = (checked: boolean) => {
      if(checked) {
        document.documentElement.setAttribute('theme', 'dark')
        state.theme = 'dark'
      }
      else {
        document.documentElement.setAttribute('theme', 'light')
        state.theme = 'light'
      }
    };
    const search = () => {
      console.log(input_value.value)
      let url = 'http://localhost:8081/search/search='
      url += input_value.value
      window.location.href = url
      // window.location.href = 'https://www.baidu.com'; 
    }
    // 登录后保存
    const user = computed(() => store.state.user);

    // 用来登录
    const loginUser = ref({
      userName: "test",
      password: "test"
    });
    const registerUser = ref({
      userName: "test",
      password: "test"
    });
    const loginModalVisible = ref(false);
    const loginModalLoading = ref(false);
    const registerModalVisible = ref(false);
    const registerModalLoading = ref(false);
    const showLoginModal = () => {
      loginModalVisible.value = true;
    };
    const showRegisterModal = () => {
      registerModalVisible.value = true;
    };

    // 登录
    const login = () => {
      console.log("login start ...");
      loginModalLoading.value = true;
      // loginUser.value.password = hexMd5(loginUser.value.password + KEY);
      axios.post('/users/login', loginUser.value).then((response) => {
        loginModalLoading.value = false;
        const data = response.data;
        if (data.success) {
          loginModalVisible.value = false;
          message.success("Login Success!");
          store.commit("setUser", data.content);
          console.log("user id: !!", store.state.user.userId)
        } else {
          message.error("Username and password are not match!");
        }
      });
    };
    const register = () => {
      console.log("register start ...");
      registerModalLoading.value = true;
      // loginUser.value.password = hexMd5(loginUser.value.password + KEY);
      axios.post('/users/save', registerUser.value).then((response) => {
        registerModalLoading.value = false;
        const data = response.data;
        if (data.success) {
          registerModalVisible.value = false;
          message.success("Register Success!");
        } else {
          message.error(data.message)
        }
      });
    }

    // 退出登录
    const logout = () => {
      console.log("logout start ...");
      axios.get('/users/logout/' + user.value.token).then((response) => {
        const data = response.data;
        if (data.success) {
          message.success("Logout Success!");
          store.commit("setUser", {});
        } else {
          message.error(data.message);
        }
      });
    };

    const toProfile = () => {
      let url = 'http://localhost:8081/user/profile/id='
      url += store.state.user.userId
      window.location.href = url
    }

    return {
      input_value,
      search,
      loginModalVisible,
      loginModalLoading,
      registerModalVisible,
      registerModalLoading,
      showLoginModal,
      showRegisterModal,
      loginUser,
      registerUser,
      login,
      register,
      user,
      logout,
      toProfile,
      changeTheme,
      ...toRefs(state),
    }
  }
});
</script>

<style>
#logo_text {
  color: #000000
}

.login-menu {
  float: right;
  color: white;
  padding-left: 20px;
}

#input {
  margin-left: 20px;
}
</style>