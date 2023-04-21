<template>
    <div>
        <the-menu></the-menu>
        <div class="components-page-header-demo-content" id="profile">
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
            users: {}
        }
    },
    mounted() {
        var url = '/users/list?userId='
        url += store.state.user.userId
        axios.get(url)
            .then(response => {
                this.users = response.data.content.list[0]
            })
            .catch(error => {
                console.log(error)
            })
    }
});
</script>

<style>
#profile {
    border: 1px solid #dddddd;
    width: 85%;
    float: left;
}

#menu {
    float: left;
}
</style>