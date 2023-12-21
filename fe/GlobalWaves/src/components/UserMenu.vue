<template>
   <div class="card flex justify-content-center">
        <Menu :model="items" style="  border-color: black;
    border-style: solid;
    border-width: 2px;
    width: 30%;"/>
    </div>
   </template>

<script setup lang="ts">
import Menu from 'primevue/menu';
import {ref} from 'vue';
const items = ref([
    {
        label: 'Profile',
        icon: 'pi pi-fw pi-user',
        command: () => {
            window.location.href = '/profile';
        }
    },
    {
        label: 'Home',
        icon: 'pi pi-fw pi-home',
        command: () => {
            window.location.href = '/home';
        }
    },
    {
        label: 'Favourite songs',
        icon: 'pi pi-fw pi-heart',
        command: () => {
            window.location.href = '/favourite-songs';
        }
    },
    {
        label: 'Settings',
        icon: 'pi pi-fw pi-cog',
        command: () => {
            window.location.href = '/settings';
        }
    },
    {
        label: 'Log out',
        icon: 'pi pi-fw pi-power-off',
        command: () => {
            window.location.href = '/logout';
        }
    }
]);

</script>

<script lang="ts">
</script>
<style scoped>
.p-menu {
    width: 80%;
}
:deep(.p-menuitem-text) {
    font-size: 1.5rem;
    font-weight: 60;
    color: rgb(31, 86, 187);
    width: 80%;
    text-align: left;
    margin-left: 0em;
}
:deep(.p-menuitem-icon) {
    width: 20%;
    margin-left: 0em;
    color: #000;
}
</style>

