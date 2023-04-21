<template>
  <div>
    <div class="components-page-header-demo-content" id="profile1">
      <a-page-header :title="users.nickName" class="site-page-header" :sub-title="users.userName"
        :avatar="{ src: users.avatar }" :breadcrumb="{ routes }">
        <template #tags>
          <a-tag color="blue">{{ users.userRole }}</a-tag>
        </template>
        <template #extra>
          <a-dropdown key="more">
            <a-button :style="{ border: 'none', padding: 0 }">
              <EllipsisOutlined :style="{ fontSize: '20px', verticalAlign: 'top' }" />
            </a-button>
          </a-dropdown>
        </template>
        <a-descriptions bordered>
          <a-descriptions-item label="NickName">{{ users && users.nickName }}</a-descriptions-item>
          <a-descriptions-item label="UserName">{{ users && users.userName }}</a-descriptions-item>
          <a-descriptions-item label="Email">{{ users && users.email }}</a-descriptions-item>
          <a-descriptions-item label="gender">{{ users && users.gender }}</a-descriptions-item>
          <a-descriptions-item label="Date of Birth">{{ users && users.dob }}</a-descriptions-item>
        </a-descriptions>
      </a-page-header>
    </div>
    <h3 id="wl">WishList</h3>
    <a-list item-layout="horizontal" :data-source="wishlist" id="wishlist1">
      <template #renderItem="{ item }">
        <a-list-item>
          <a-list-item-meta>
            <template #title>
              <a @click="toBookInfo(item.bookId)">{{ item.bookTitle }}</a>
            </template>
            <template #avatar>
              <a-avatar :src="item.imgUrl" />
            </template>
          </a-list-item-meta>
        </a-list-item>
      </template>
    </a-list>
  </div>
</template>

<script>
import { defineComponent, reactive, toRefs, watch, onMounted, ref } from 'vue';
import axios from 'axios'
import store from '@/store';
import TheMenu from '@/components/the-menu.vue'
import { MenuFoldOutlined, MenuUnfoldOutlined, PieChartOutlined, MailOutlined, DesktopOutlined, InboxOutlined, AppstoreOutlined } from '@ant-design/icons-vue';
export default defineComponent({
  components: {
    TheMenu
  },
  data() {
    return {
      users: {},
      wishlist: [],
    }
  },
  methods: {
        toBookInfo(idx) {
            let url = "http://localhost:8081/books/id="
            url += idx
            window.location.href = url;
        }
    },
  mounted() {
    var url = `/users/list?userId=${this.$route.params.id}`
    url = url.replace("id=", "");
    axios.get(url)
      .then(response => {
        this.users = response.data.content.list[0]
      })
      .catch(error => {
        console.log(error)
      })
    url = `/users/favorite?userId=${this.$route.params.id}`
    url = url.replace("id=", "");
    axios.get(url)
      .then(response => {
        this.wishlist = response.data.content.list
      })
      .catch(error => {
        console.log(error)
      })
  }
});
</script>

<style>
#profile1 {
  border: 1px solid #dddddd;
  width: 1280px;
  float: left;
}

#menu {
  float: left;
}

#wishlist1 {
  margin-left: 30px;
  width: 1100px;
}

#wl {
  color: #888888;
  margin-left: 30px;
  font-size: 20px;
}
</style>