<template>
  <a-layout>
    <a-layout style="padding: 0 24px 24px;">
      <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
        <a-table :columns="columns" :data-source="books" rowKey="bookId">
          <template #name="{ text }">
            <a>{{ text }}</a>
          </template>
          <template #customTitle>
            <span>
              <smile-outlined />
              Name
            </span>
          </template>
          <template #cover="{ text: cover }">
            <img v-if="cover" :src="cover" alt="avatar" :style="{ width: '80px', height: '110px' }" />
          </template>
          <template #action="{ record }">
            <span>
              <a-button type="primary" block ghost @click="showEditModal(record)">Edit</a-button>
            </span>
          </template>
        </a-table>

        <a-modal title="Edit" v-model:visible="editModalVisible" :confirm-loading="editModalLoading" @ok="edit">
          <a-form :model="editBook" :label-col="{ span: 7 }" :wrapper-col="{ span: 14 }">
            <a-form-item label="bookTitle">
              <a-input id="input" v-model:value="editBook.bookTitle" />
            </a-form-item>
            <a-form-item label="imgUrl">
              <a-input id="input" v-model:value="editBook.imgUrl" />
            </a-form-item>
            <a-form-item label="bookIsbn">
              <a-input id="input" v-model:value="editBook.bookIsbn" />
            </a-form-item>
            <a-form-item label="bookAuthor">
              <a-input id="input" v-model:value="editBook.bookAuthor" />
            </a-form-item>
            <a-form-item label="bookRating">
              <a-input id="input" v-model:value="editBook.bookRating" />
            </a-form-item>
            <a-form-item label="publicationDate">
              <a-input id="input" v-model:value="editBook.publicationDate" />
            </a-form-item>
            <a-form-item label="publisher">
              <a-input id="input" v-model:value="editBook.publisher" />
            </a-form-item>
            <a-form-item label="totalReaders">
              <a-input id="input" v-model:value="editBook.totalReaders" />
            </a-form-item>
          </a-form>
        </a-modal>

      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
import { SmileOutlined, DownOutlined } from '@ant-design/icons-vue';
import { defineComponent, onMounted, ref } from 'vue';
import { message } from 'ant-design-vue';
import axios from 'axios';
import store from '@/store';

const columns = [
  {
    title: 'BookId',
    dataIndex: 'bookId'
  },
  {
    title: 'BookTitle',
    dataIndex: 'bookTitle',
  },
  {
    title: 'BookAuthor',
    dataIndex: 'bookAuthor',
  },
  {
    title: 'cover',
    dataIndex: 'imgUrl',
    slots: { customRender: 'cover' }
  },
  {
    title: 'BookIsbn',
    dataIndex: 'bookIsbn',
  },
  {
    title: 'BookRating',
    dataIndex: 'bookRating',
  },
  {
    title: 'PublicationDate',
    dataIndex: 'publicationDate',
  },
  {
    title: 'Publisher',
    dataIndex: 'publisher',
  },
  {
    title: 'TotalReaders',
    dataIndex: 'totalReaders',
  },
  {
    title: 'action',
    dataIndex: 'action',
    slots: { customRender: 'action' }
  }
];
export default defineComponent({
  setup() {
    const books = ref();
    const editBook = ref({
      
    });
    const editModalLoading = ref(false);
    const editModalVisible = ref(false);
    const showEditModal = (record) => {
      editBook.value = { ...record };
      editModalVisible.value = true;
    };
    const edit = () => {
      console.log("edit start ...");
      editModalLoading.value = true;
      axios.post('/book/save', editBook.value).then((response) => {
        editModalLoading.value = false;
        const data = response.data;
        if (data.success) {
          editModalVisible.value = false;
          message.success("Edit Success!");
        } else {
          message.error(data.message)
        }
      });
    }
    onMounted(() => {
      console.log("onMounted")
      var url = "/book/list";
      axios.get(url).then((res) => {
        const data = res.data
        books.value = data.content.list
      })
    });
    return {
      books,
      columns,
      editModalLoading,
      editModalVisible,
      edit,
      showEditModal,
      editBook,
    };
  },
  components: {
    SmileOutlined,
    DownOutlined,
  },
});
</script>