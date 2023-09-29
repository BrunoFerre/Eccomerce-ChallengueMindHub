// No necesitas importar createApp ni axios aquí

const app = Vue.createApp({
    data() {
        return {
            products: [], // Almacenar los productos
        };
    },
    mounted() {
        // Realizar una solicitud GET a la API de productos
        axios.get('https://fakestoreapi.com/products')
            .then((response) => {
                this.products = response.data;
            })
            .catch((error) => {
                console.error('Error al obtener los productos', error);
            });
    },
});

// Montar la instancia de Vue en el elemento con ID "app"
app.mount('#app');
