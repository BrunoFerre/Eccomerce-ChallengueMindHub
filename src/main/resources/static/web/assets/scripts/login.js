const app = Vue.createApp({
    data() {
        return {
            email: "",
            password: "",
            firstName: "",
            lastName: "",
            showRegistrationForm: false, // Agrega una variable para controlar qué formulario se muestra
        };
    },
    methods: {
        login(event) {
            // Lógica para el inicio de sesión
            // ...
        },
        addClient() {
            // Lógica para agregar un cliente
            // ...
        },
        signUp() {
            // Lógica para el registro de un nuevo cliente
            // ...
        },
        toggleForm() {
            // Método para alternar entre el formulario de inicio de sesión y el de registro
            this.showRegistrationForm = !this.showRegistrationForm;
        },
    },
});

app.mount('#app');
