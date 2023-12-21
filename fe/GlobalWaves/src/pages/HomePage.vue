<template>
    <SearchBar />
    <div class="parent">
     <div class="child">
    <UserMenu  />
    </div>
    <div class="child">
        <h1 class="p-m-0" style="text-align: center; margin-top: 6.5em;">My unique playlists</h1>
        <Carousel :value="playlists" :numVisible="3" :numScroll="1" :circular="true" >
            <template #item="{data : playlist}">
                <div class="item">
                  <!-- <img :src="value.image" :alt="value.name" class="p-shadow-2" style="width: 250px; height: 250px" /> -->
                    <h3 class="p-m-0">{{playlist.name}}</h3>
                    <h4 class="p-m-0">{{playlist.description}}</h4>
                </div>
            </template>
        </Carousel>
    </div>
    </div>
</template>

<script setup lang="ts">
import SearchBar from '../components/SearchBar.vue';
import Carousel from 'primevue/carousel';
import {playlists} from '../assets/playlists';
import UserMenu from '../components/UserMenu.vue';
</script>

<style scoped>
.child {
    display: inline-block;
}
.item {
    height: 14em;
    text-align: center;
    padding: 2em;
    box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.1);
    background-color: #ffffff;
    margin: 0 1em;
    border-color: black;
    border-style: solid;
    border-width: 1px;
}
</style>