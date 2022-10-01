<template>
  <v-chart class="chart" :option="option" />
</template>
<script>

import * as echarts from 'echarts/core';
import { GridComponent } from 'echarts/components';
import { BarChart } from 'echarts/charts';
import { CanvasRenderer } from 'echarts/renderers';
echarts.use([GridComponent, BarChart, CanvasRenderer]);

import VChart, { THEME_KEY } from 'vue-echarts';
import { ref, defineComponent,computed } from 'vue';

let userStr=sessionStorage.getItem("users")||"{}"
let user=JSON.parse(userStr)

export default defineComponent({
  name: 'echart',
  data(){
    return{}
  },
  components: {
    VChart,
  },
  provide: {
    [THEME_KEY]: 'dark',
  },
  setup() {
    const data=ref(user.records)
    const option = ref({
      xAxis: {
        type: 'category',
        data: data.value.map(d => d.id)
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: data.value.map(d => d.age),
          type: 'bar'
        }
      ]
    });
    return { option };
  },

});
</script>

<style scoped>
.chart {
  height: 50vh;
}
</style>