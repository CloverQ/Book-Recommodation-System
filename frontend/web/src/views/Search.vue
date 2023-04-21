<template>
    <div>
        <a-list id="search" item-layout="vertical" size="large" :grid="{ gutter: 20, column: 3 }" :pagination="pagination"
            :data-source="filteredItems">
            <template #renderItem="{ item }">
                <a-list-item key="item.bookTitle">
                    <template #actions>
                        <span v-for="{ type, text } in actions" :key="type">
                            <component v-bind:is="type" style="margin-right: 8px" />
                            {{ text }}
                        </span>
                    </template>
                    <a-list-item-meta :description="item.bookAuthor">
                        <template #title>
                            <a :href="item.href" @click="toDetailPage(item.bookId)">{{ item.bookTitle }} </a>
                        </template>
                        <template #avatar><a-avatar :src="item.imgUrl" shape="square" size="large" id="avatar" /></template>
                    </a-list-item-meta>
                    ISBN: {{ item.bookIsbn }}<br /> Publish Date: {{ item.publicationDate }}<br />
                    Publisher: {{ item.publisher }}<br /> Book Rating: {{ item.bookRating }}<br />
                </a-list-item>
            </template>
        </a-list>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            books: [],
            query: '',
            pagination: {
                onChange: (page) => {
                    console.log(page);
                },
                pageSize: 9
            },
        }
    },
    created() {
        var url = `/book/list`
        axios.get(url)
            .then(response => {
                this.books = response.data.content.list
            })
            .catch(error => {
                console.log(error)
            })
        this.query = this.$route.params.search
        this.query = this.query.replace("search=", "");
        console.log(this.query)
    },
    computed: {
        filteredItems() {
            return this.books.filter(item => {
                return item.bookTitle.toLowerCase().includes(this.query.toLowerCase()) || 
                item.bookAuthor.toLowerCase().includes(this.query.toLowerCase()) || 
                item.bookIsbn.toLowerCase() == this.query.toLowerCase()
            })
        }
    },
    methods: {
        toDetailPage(idx) {
            let url = "http://localhost:8081/books/id="
            url += idx
            window.location.href = url;
        }
    }
}
</script>
<style>
#search {
    margin-left: 40px;
    margin-top: 40px;
}
#avatar {
  width: 80px;
  height: 100px;
  line-height: 60px;
  border-radius: 3%;
  margin: 5px 0;
}
</style>