<template>
  <a-layout>
    <a-layout style="padding: 0 24px 24px;">
      <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
        <a-carousel autoplay>
          <div><img src="../assets/book1.png"></div>
          <div><img src="../assets/book2.png"></div>
          <div><img src="../assets/book3.png"></div>
          <div><img src="../assets/book4.png"></div>
        </a-carousel>
        <a-list id="list" item-layout="vertical" size="large" :grid="{ gutter: 20, column: 3 }" :pagination="pagination"
          :data-source="books">
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
                <template #avatar><a-avatar :src="item.imgUrl" shape="square" size="large" id="avatar"/></template>
              </a-list-item-meta>
              ISBN: {{ item.bookIsbn }}<br /> Publish Date: {{ item.publicationDate }}<br />
              Publisher: {{ item.publisher }}<br /> Book Rating: {{ item.bookRating }}<br />
            </a-list-item>
          </template>
        </a-list>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios'
import { walkBlockDeclarations } from '@vue/compiler-core';

export default defineComponent({
  name: 'Home',
  setup() {
    console.log("setup")
    const books = ref()
    onMounted(() => {
      console.log("onMounted")
      axios.get("/book/list").then((res) => {
        const data = res.data
        books.value = data.content.list
      })
    })
    const toDetailPage = (idx: number) => {
      let url = "http://localhost:8081/books/id="
      url += idx
      window.location.href = url;
    }
    return {
      books,
      toDetailPage,
      pagination: {
        onChange: (page: number) => {
          console.log(page);
        },
        pageSize: 9
      },
      actions: [
        { type: 'StarOutlined', text: '156' },
        { type: 'LikeOutlined', text: '156' },
        { type: 'MessageOutlined', text: '2' },
      ]
    }
  }
});
</script>
<style scoped>
.ant-avatar {
  width: 80px;
  height: 100px;
  line-height: 60px;
  border-radius: 3%;
  margin: 5px 0;
}
.ant-carousel :deep(.slick-slide) {
  text-align: center;
  height: 500px;
  line-height: 160px;
  background: #364d79;
  overflow: hidden;
}
.ant-carousel :deep(.slick-slide h3) {
  color: #fff;
}
#list {
  margin-top: 50px;
  margin-left: 50px;
}
img {
  height: 500px;
  width: 1170px;
}
</style>