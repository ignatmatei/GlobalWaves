<template>
    <Card style="margin-top: 10em">
        <template #header>
            <h1 style=" text-align:center">
            Welcome to GlobalWaves!
            </h1>
        </template>
        <template #title>
            <h2 style=" text-align:center"> 
                Please sign up or login to continue
             </h2>
        </template>
        <template #content>
            <div class="p-d-flex p-jc-center p-ai-center">
                <Button label="Sign up" @click="goToSignUp()" color="warning"/>
                <Button label="Log in" @click="goToLogIn()" />
            </div>
        </template>
</Card>
</template>

<script setup lang="ts">
import Button from 'primevue/button';
import Card from 'primevue/card';
import router from '../router';
</script>
<script lang="ts">
    function goToSignUp() : void {
        router.push('../sign-up');
    }
    function goToLogIn() : void {
        router.push('../log-in');
    }
  </script>

<style scoped>
    .p-card {
        top: 50%;
        width: 90%;
        margin-left: 5%;
        margin-right: 5%;
        height: 15em;
        border-color: rgb(162, 37, 187);
        border-style: solid;
        border-width: 1px;
        text-align: center;
        padding: 2em;
        box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.1);
                
    }
    .p-button {
        margin-right: 1em;
    }
    </style>