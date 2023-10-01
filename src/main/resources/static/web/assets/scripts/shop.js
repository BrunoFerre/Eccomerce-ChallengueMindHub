const app = Vue.createApp({
    data() {
        return {
            products: [], 
        };
    },
    mounted() {
        axios.get('https://fakestoreapi.com/products')
            .then((response) => {
                this.products = response.data;
            })
            .catch((error) => {
                console.error('Error al obtener los productos', error);
            });
    },
});


app.mount('#app');
