const app = Vue.createApp({
    data() {
        return {
            newProduct: {
                name: '',
                description: '',
                price: 0,
                stock: 0,
                category: '',
                color: '',
                discount: 0,
                image: '',
                imageCollection: ''
            },
            categories: [ ],
            colors: [ ],
            products: []
        };
    },
    created() {
        this.loadProducts();
    },
    methods: {
        createProduct() {
            axios.post('/api/products', this.newProduct)
                .then((response) => {
                    this.products.push(response.data);
                    this.newProduct = {
                        name: '',
                        description: '',
                        price: 0,
                        stock: 0,
                        category: '',
                        color: '',
                        discount: 0,
                        image: '',
                        imageCollection: ''
                    };
                })
                .catch((error) => {
                    console.error('Error al crear el producto', error);    
                })
        },
        loadProducts() {
            axios.get('/api/products')
                .then((response) => {
                    this.products = response.data;
                    console.log(this.products);
                })
                .catch((error) => {
                    console.error('Error al obtener los productos', error);
                });
        },
    },
});

app.mount('#app');
