<template>
    <Menu :model="items" popup :style="{width: '12em'}" />
</template>

<script setup lang="ts">
import Menu from 'primevue/menu';
const items = [
    {
        label: 'Profile',
        icon: 'pi pi-user',
        command: () => {
            window.location.href = '/profile';
        }
    },
    {
        label: 'Favourite songs',
        icon: 'pi pi-heart',
        command: () => {
            window.location.href = '/favourite-songs';
        }
    },
    {
        label: 'Settings',
        icon: 'pi pi-cog',
        command: () => {
            window.location.href = '/settings';
        }
    },
    {
        label: 'Log out',
        icon: 'pi pi-sign-out',
        command: () => {
            window.location.href = '/logout';
        }
    }
];
</script>

<style scoped>
</style>
