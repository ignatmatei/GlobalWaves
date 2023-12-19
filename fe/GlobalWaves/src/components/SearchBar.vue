<template>
<InputGroup style="width: 30em; margin-left: 40em;">
    <InputText placeholder="Search" />

</InputGroup>
</template>

<script setup lang="ts">
import InputGroup from 'primevue/inputgroup';
import InputText from 'primevue/inputtext';
</script>

<style scoped>
:deep(.p-inputgroup) {
    box-shadow: none;
    background-color: transparent;
    border: none;
    border-bottom: 1px solid var(--surface-d);
}
:deep(.p-inputtext) {
    font-size: 1.5rem;
    font-weight: 600;
    color: rgb(31, 86, 187);
    width: 80%;
    text-align: center;
}
</style>