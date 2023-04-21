<template>
    <div id="main">
        <div id="set">
            <label>Title: </label>
            <a-auto-complete v-model:value="title" style="width: 200px" placeholder="input here" />
            <label>Author: </label>
            <a-auto-complete v-model:value="author" style="width: 200px" placeholder="input here" />
            <label>Category: </label>
            <a-select v-model:value="category" :size="size" style="width: 200px" :options="categories"></a-select>
        </div>
        <div id="set">
            <label>Rating: </label>
            <a-select v-model:value="rating" :size="size" style="width: 200px" :options="ratings"></a-select>
        </div>
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
import ref from 'vue'

export default {
    data() {
        return {
            books: [],
            pagination: {
                onChange: (page) => {
                    console.log(page);
                },
                pageSize: 9
            },
            title: '',
            author: '',
            category: '',
            rating: 0.0,
            categories: [
                { value: '', label: 'All' },
                { value: 'Young Adult', label: 'Young Adult' },
                { value: 'Fiction', label: 'Fiction' },
                { value: 'Dystopia', label: 'Dystopia' },
                { value: 'Fantasy', label: 'Fantasy' },
                { value: 'Classics', label: 'Classics' },
                { value: 'Historical', label: 'Historical' },
                { value: 'Academic', label: 'Academic' },
                { value: 'School', label: 'School' },
                { value: 'Romance', label: 'Romance' },
                { value: 'Childrens', label: 'Childrens' },
                { value: 'Comedy', label: 'Comedy' },
                { value: 'Humor', label: 'Humor' },
                { value: 'Animals', label: 'Animals' },
                { value: 'Biography', label: 'Biography' },
                { value: 'Horror', label: 'Horror' },
                { value: 'Mystery', label: 'Mystery' },
                { value: 'Uglies', label: 'Uglies' },
                { value: 'Cultural', label: 'Cultural' },
                { value: 'Paranormal', label: 'Paranormal' },
                { value: 'Literature', label: 'Literature' },
                { value: 'Novels', label: 'Novels' },
                { value: 'Drama', label: 'Drama' },
            ],
            ratings: [
                { value: 0.0, label: 'All' },
                { value: 4.0, label: '4.0' },
                { value: 4.1, label: '4.1' },
                { value: 4.2, label: '4.2' },
                { value: 4.3, label: '4.3' },
                { value: 4.4, label: '4.4' },
                { value: 4.5, label: '4.5' },
                { value: 4.6, label: '4.6' },
                { value: 4.7, label: '4.7' },
                { value: 4.8, label: '4.8' },
                { value: 4.9, label: '4.9' },
                { value: 5.0, label: '5.0' },
            ],
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
    },
    computed: {
        filteredItems() {
            return this.books.filter(item => {
                return item.bookTitle.toLowerCase().includes(this.title.toLowerCase()) &&
                item.category.toLowerCase().includes(this.category.toLowerCase()) &&
                item.bookAuthor.toLowerCase().includes(this.author.toLowerCase()) &&
                item.bookRating >= this.rating;

            })
        }
    },
    methods: {
        toDetailPage(idx) {
            let url = "http://localhost:8081/books/id="
            url += idx
            window.location.href = url;
        },
        handleSubmit() {
            console.log(this.category)
        }
    }
}
</script>
<style>
.ant-avatar {
  width: 80px;
  height: 100px;
  line-height: 60px;
  border-radius: 3%;
  margin: 5px 0;
}
#main {
    margin-left: 50px;
}

label {
    cursor: pointer;
    display: inline-block;
    padding: 3px 6px;
    text-align: right;
    width: 150px;
    vertical-align: top;
}

#button {
    margin-left: 600px;
    width: 100px;
}

#set {
    margin-top: 15px;
}
</style>