<template>
<div class="wrapper">
    <Card>
        <template #title>
            <h1 style= "text-align:center"> Sign up </h1>
        </template>
        <template #header>
            <img src = "../assets/sign-up-image.jpg" alt = "Sign up image" class="girl-inspirational"/>
            <h2 style=" text-align:center">Help make a difference in the music industry!</h2>
            <h2 style=" text-align:center">(and also help your pocket)</h2>
        </template>
        <template #content>
            <InputGroup>
                <InputGroupAddon>
                 <img src="../assets/user.svg" alt="Username icon" class="icon" />
                <InputText placeholder="Username" />
                </InputGroupAddon>
            </InputGroup>
            <br />
            <InputGroup>
                <InputGroupAddon>
                 <img src="../assets/mail.svg" alt="Mail icon" class="icon" />
                <InputText placeholder="Mail"  />
                </InputGroupAddon>
            </InputGroup>
            <br />
            <InputGroup>
                <InputGroupAddon>
                 <img src="../assets/password.svg" alt="Password icon" class="icon" />
                <InputText placeholder="Confirm password"  />
                </InputGroupAddon>
            </InputGroup>
            <br />
            <div style="text-align: center">
               <a> Already have an account? </a>
                <a> Log in here! </a>
            </div>
            <Button label="Sign up" />
        </template>
    </Card>
</div>
</template>

<script setup lang="ts">
import Card from 'primevue/card';
import Button from 'primevue/button';
import InputGroup from 'primevue/inputgroup';
import InputText from 'primevue/inputtext';
</script>

<style scoped>
    .wrapper {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .p-card {
        width: 40em;
        border-color: green;
        border-style: solid;
        border-width: 3px;
        padding: 1em;
    }
    .p-card-title {
        text-align: center;
    }
    .girl-inspirational {
        width: 100%;
        height: 100%;
    }
    .icon {
        width: 1.5em;
        height: 1.5em;
        margin-right: 0.5em;
    }
    .p-inputgroup {
        justify-content: center;
    }
    .p-inputtext {
        width: 90%;
        margin-left: 5%;
        margin-right: 5%;
    }
    .p-button {
        width: 90%;
        margin-left: 5%;
        margin-right: 5%;
    }
</style>