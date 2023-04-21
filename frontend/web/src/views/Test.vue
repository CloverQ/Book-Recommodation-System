<template>
    <div>
      <button @click="filterByAge(20)">Filter by Age 20</button>
      <button @click="filterByAge(30)">Filter by Age 30</button>
      <ul>
        <li v-for="item in filteredList" :key="item.name">
          {{ item.name }} ({{ item.age }})
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        items: [
          { name: 'Alice', age: 20 },
          { name: 'Bob', age: 30 },
          { name: 'Charlie', age: 20 },
          { name: 'Dave', age: 30 },
        ],
        ageFilter: null,
      };
    },
    methods: {
      filterByAge(age) {
        this.ageFilter = age;
      },
    },
    computed: {
      filteredList() {
        if (!this.ageFilter) {
          return this.items;
        }
        return this.items.filter((item) => item.age === this.ageFilter);
      },
    },
  };
  </script>