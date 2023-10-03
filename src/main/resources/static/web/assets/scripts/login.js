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
        login() {
            // Realizar la solicitud POST al servidor
            axios.post('/api/login', {
                email: this.email,
                password: this.password
            })
            .then(response => {
                // Manejar la respuesta del servidor aquí
                console.log(response.data);
            })
            .catch(error => {
                // Manejar errores aquí
                console.error(error);
            });
            window.location.href = '/web/manager.html';
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
