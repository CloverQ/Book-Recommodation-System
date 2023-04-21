<template>
    <div style="width: 168px;" id="menu">
        <a-menu v-model:openKeys="openKeys" v-model:selectedKeys="selectedKeys" mode="inline" theme="dark"
            :inline-collapsed="collapsed">
            <a-menu-item key="/">
                <template #icon>
                    <PieChartOutlined />
                </template>
                <router-link to="/">Home</router-link>
            </a-menu-item>
            <a-menu-item key="2">
                <template #icon>
                    <DesktopOutlined />
                </template>
                <a @click="toProfile">Profile</a>
            </a-menu-item>
            <a-menu-item key="3">
                <template #icon>
                    <InboxOutlined />
                </template>
                <a @click="toWishlist">Wishlist</a>
            </a-menu-item>
            <a-menu-item key="4">
                <template #icon>
                    <InboxOutlined />
                </template>
                <a @click="toRestPwd">Reset Password</a>
            </a-menu-item>
            <a-menu-item key="5">
                <template #icon>
                    <InboxOutlined />
                </template>
                <a @click="toEdit">Edit Profile</a>
            </a-menu-item>
            <a-menu-item key="6" disabled>
                <template #icon>
                    <InboxOutlined />
                </template>
                <span></span>
            </a-menu-item>
            <a-menu-item key="7" disabled>
                <template #icon>
                    <InboxOutlined />
                </template>
                <span></span>
            </a-menu-item>
            <a-menu-item key="8" disabled>
                <template #icon>
                    <InboxOutlined />
                </template>
                <span></span>
            </a-menu-item>
            <a-menu-item key="9" disabled>
                <template #icon>
                    <InboxOutlined />
                </template>
                <span></span>
            </a-menu-item>
            <a-menu-item key="10" disabled>
                <template #icon>
                    <InboxOutlined />
                </template>
                <span></span>
            </a-menu-item>
            <a-menu-item key="11" disabled>
                <template #icon>
                    <InboxOutlined />
                </template>
                <span></span>
            </a-menu-item>
            <a-menu-item key="12" disabled>
                <template #icon>
                    <InboxOutlined />
                </template>
                <span></span>
            </a-menu-item>

        </a-menu>
    </div>
</template>

<script>
import { defineComponent, reactive, toRefs, watch, onMounted, ref } from 'vue';
import axios from 'axios'
import store from '@/store';
import { MenuFoldOutlined, MenuUnfoldOutlined, PieChartOutlined, MailOutlined, DesktopOutlined, InboxOutlined, AppstoreOutlined } from '@ant-design/icons-vue';
export default defineComponent({
    components: {
        MenuFoldOutlined,
        MenuUnfoldOutlined,
        PieChartOutlined,
        MailOutlined,
        DesktopOutlined,
        InboxOutlined,
        AppstoreOutlined,
    },
    setup() {
        const state = reactive({
            collapsed: false,
            selectedKeys: ['1'],
            openKeys: ['sub1'],
            preOpenKeys: ['sub1'],
        });
        watch(() => state.openKeys, (_val, oldVal) => {
            state.preOpenKeys = oldVal;
        });
        const toggleCollapsed = () => {
            state.collapsed = !state.collapsed;
            state.openKeys = state.collapsed ? [] : state.preOpenKeys;
        };
        const toProfile = () => {
            let url = 'http://localhost:8081/user/profile/id='
            url += store.state.user.userId
            window.location.href = url
        }
        const toWishlist = () => {
            let url = 'http://localhost:8081/user/wishlist/id='
            url += store.state.user.userId
            window.location.href = url
        }
        const toRestPwd = () => {
            let url = 'http://localhost:8081/user/reset/id='
            url += store.state.user.userId
            window.location.href = url
        }
        const toEdit = () => {
            let url = 'http://localhost:8081/user/edit/id='
            url += store.state.user.userId
            window.location.href = url
        }
        return {
            ...toRefs(state),
            toggleCollapsed,
            toProfile,
            toWishlist,
            toRestPwd,
            toEdit
        };
    },
});
</script>
<style>
#profile {
    border: 1px solid #dddddd;
    width: 1108px;
    float: left;
}

#menu {
    float: left;
}
</style>