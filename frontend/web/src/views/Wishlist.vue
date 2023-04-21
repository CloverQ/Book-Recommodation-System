<template>
    <div>
        <the-menu></the-menu>
        <a-list item-layout="horizontal" :data-source="wishlist" id="wishlist">
            <template #renderItem="{ item }">
                <a-list-item id="wishlist">
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
            wishlist: []
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
        var url = '/users/favorite?userId='
        url += store.state.user.userId
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
#wishlist {
    margin-left: 110px;
}
</style>