<template>
    <div class="p-d-flex p-flex-column p-jc-center p-ai-center">
        <h1 class="p-m-0">Favourite Songs</h1>
        <Carousel :value="songs" :numVisible="3" :numScroll="1" :circular="true" >
            <template #item="{data : song}">
                <div class="p-d-flex p-flex-column p-jc-center p-ai-center">
                  <!-- <img :src="value.image" :alt="value.name" class="p-shadow-2" style="width: 250px; height: 250px" /> -->
                    <h3 class="p-m-0">{{song.name}}</h3>
                    <h4 class="p-m-0">{{song.artist}}</h4>
                </div>
            </template>
        </Carousel>
    </div>
</template>

<script setup lang="ts">
import Carousel from 'primevue/carousel';
import { songs } from '../assets/songs';
</script>
<script lang="ts">
</script>
<style scoped>
</style>