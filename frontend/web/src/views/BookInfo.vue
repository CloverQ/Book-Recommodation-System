<template>
  <div class="components-page-header-demo-content">
    <a-page-header :title="book.bookTitle" class="site-page-header" :sub-title="book.bookIsbn"
      :avatar="{ src: book.imgUrl }">
      <template v-slot:tags>
        <a-tag color="blue">{{ book.category }}</a-tag>
      </template>
      <template v-slot:extra>
        <a-button html-type="submit" :loading="submitting" type="primary" @click="addWishlist" v-show="users.userId">
            Add Wishlist
          </a-button>
      </template>
      <a-row class="content">
        <div style="flex: 1">
          <a-rate v-model:value="book.bookRating" allow-half style="font-size: 20px" disabled />
          <div id="info">
            <p id="rating">Rating: {{ book.bookRating }}</p>
            <p id="publicationDate">Author: {{ book.bookAuthor }} </p>
            <p id="publicationDate">PublicationDate: {{ book.publicationDate }} </p>
            <p id="publisher">publisher: {{ book.publisher }} </p>
          </div>
          <p>{{ book.bookDesc }}</p>
        </div>
      </a-row>
    </a-page-header>
    <a-comment id="comments" v-for="(item, index) in comments" :key="index">
      <template #author><a @click="toProfilePage(item.userId)">{{ item.userName }}</a></template>
      <template #avatar>
        <a-avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" alt="Han Solo" />
      </template>
      <template #content>
        <p>
          {{ item.content }}
        </p>
      </template>
    </a-comment>
    <a-comment id="comments" v-show="users.userId">
      <template #avatar>
        <a-avatar :src="users.avatar" alt="Han Solo" />
      </template>
      <template #author><a>{{ users.userName }}</a></template>
      <template #content>
        <a-form-item>
          <a-textarea id="textarea" :rows="3" v-model:value="content" />
        </a-form-item>
        <a-form-item>
          <a-button html-type="submit" :loading="submitting" type="primary" @click="handleSubmit">
            Add Comment
          </a-button>
        </a-form-item>
      </template>
    </a-comment>
    <div>{{wishlist}}</div>
  </div>
</template>
  
<script>
import axios from 'axios'
import store from '@/store';
export default {
  data() {
    return {
      book: {},
      comments: {},
      users: {},
      content: "",
      new_comment: {},
      wishlist: [],
      value: 0,
      new_like: {},
    }
  },
  methods: {
    handleSubmit() {
      var url = `/comments/save`
      var str = this.$route.params.id
      str = str.replace("id=", "");
      this.new_comment.bookId = parseInt(str)
      this.new_comment.userId = store.state.user.userId
      this.new_comment.userName = this.users.userName
      this.new_comment.content = this.content
      console.log(this.new_comment)
      axios.post(url, this.new_comment)
        .then(response => {
          console.log(response.data)
        })
        .catch(error => {
          console.log(error)
        })
    },
    addWishlist() {
      console.log("!!!!!!!!")
      var url = `/favorite/save`
      var str = this.$route.params.id
      str = str.replace("id=", "");
      this.new_like.userId = store.state.user.userId
      this.new_like.bookId = parseInt(str)
      this.new_like.bookTitle = this.book.bookTitle
      this.new_like.imgUrl = this.book.imgUrl
      console.log(this.new_like)
      axios.post(url, this.new_like)
        .then(response => {
          console.log(response.data)
        })
        .catch(error => {
          console.log(error)
        })
    },
    toProfilePage(idx) {
      let url = "http://localhost:8081/user/other/id="
      url += idx
      window.location.href = url;
    }
  },
  mounted() {
    var url = `/book/list?${this.$route.params.id}`
    url = url.replace("id", "BookId");
    axios.get(url)
      .then(response => {
        this.book = response.data.content.list[0]
      })
      .catch(error => {
        console.log(error)
      })

    url = `/book/comments?${this.$route.params.id}`
    url = url.replace("id", "BookId");
    axios.get(url)
      .then(response => {
        this.comments = response.data.content.list
        console.log(this.comments)
      })
      .catch(error => {
        console.log(error)
      })

    url = '/users/list?userId='
    url += store.state.user.userId
    axios.get(url)
      .then(response => {
        this.users = response.data.content.list[0]
      })
      .catch(error => {
        console.log(error)
      })
  }
}
</script>

<style>
#info {
  display: flex;
  flex-direction: row;
}

#rating {
  color: #aaaaaa;
  font-size: 10px;
}

#publicationDate,
#publisher {
  margin-left: 30px;
  color: #aaaaaa;
  font-size: 10px;
}

#comments {
  margin-left: 30px;
  margin-top: -30px;
}

#textarea {
  width: 1000px;
}
</style>