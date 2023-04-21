<template>
  <the-menu></the-menu>
  <br />
  <a-form :model="formState" name="validate_other" v-bind="formItemLayout" @finishFailed="onFinishFailed"
    @finish="onFinish">

    <a-form-item label="Username" name="username" value="abc">
      <a-input v-model:value="users.userName" />
    </a-form-item>

    <a-form-item label="Password" name="password" value="">
      <a-input v-model:value="users.password" type="password" />
    </a-form-item>

    <a-form-item :wrapper-col="{ span: 12, offset: 6 }">
      <a-button type="primary" html-type="submit" @click="onSubmit">Submit</a-button>
    </a-form-item>
  </a-form>
</template>
<script>
import { defineComponent, reactive, toRefs, watch, onMounted, ref } from 'vue';
import axios from 'axios'
import store from '@/store';
import TheMenu from '@/components/the-menu.vue'
import { UploadOutlined } from '@ant-design/icons-vue';
import { message } from 'ant-design-vue';
import { MenuFoldOutlined, MenuUnfoldOutlined, PieChartOutlined, MailOutlined, DesktopOutlined, InboxOutlined, AppstoreOutlined } from '@ant-design/icons-vue';
export default defineComponent({
  components: {
    TheMenu,
    UploadOutlined,
    InboxOutlined,
  },
  data() {
    return {
      formItemLayout: {
        labelCol: {
          span: 6,
          offset: 0
        },
        wrapperCol: {
          span: 14,
          offset: 0
        },
      }
    }
  },
  mounted() {
    var url = '/users/list?userId='
    url += store.state.user.userId
    axios.get(url)
      .then(response => {
        this.users.userId = response.data.content.list[0].userId
        this.users.userName = response.data.content.list[0].userName
      })
      .catch(error => {
        console.log(error)
      })
  },
  methods: {
    onSubmit() {
      console.log("!!!", this.users)
      axios.post('users/reset-pwd', this.users)
        .then(response => {
          console.log(response.data)
          message.success('reset password success!')
        })
        .catch(error => {
          message.error(error)
        })
    }
  },
  setup() {
    const formState = reactive({
      'input-number': 3,
      'checkbox-group': ['A', 'B'],
      rate: 3.5,
    });
    const onFinish = values => {
      console.log('Success:', values);
    };
    const onFinishFailed = errorInfo => {
      console.log('Failed:', errorInfo);
    };
    const users = ref({
      userId: "",
      userName: "",
      password: "",
    });
    return {
      formState,
      onFinish,
      onFinishFailed,
      users,
    };
  }
});
</script>