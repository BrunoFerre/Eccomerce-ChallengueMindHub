const app = Vue.createApp({
    data() {
        return {
            email: "",
            password: "",
            firstName: "",
            lastName: "",
            showRegistrationForm: false, 
        };
    },
    methods: {
        login(event) {

        },
        addClient() {

        },
        signUp() {

        },
        toggleForm() {
            this.showRegistrationForm = !this.showRegistrationForm;
        },
    },
});

app.mount('#app');
