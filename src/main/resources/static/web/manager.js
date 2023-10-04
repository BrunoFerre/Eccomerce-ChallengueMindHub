const { createApp } = Vue

const manager = createApp({
    data() {
        return {
            products: [],
            prueba1: ""
        };
    },
    created() {
        this.loadProductDetails()
    },
    methods: {
        loadProductDetails() {
            axios.get(`http://localhost:8080/api/products`)
                .then(response => {
                    this.products = response.data
                    console.log(this.products);
                })
                .catch(error => {
                    console.log(error)
                })
        },
        formatPrice(price) {
            return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(price)
        }
    },
    computed: {
        prueba() {
            console.log(this.prueba1);
            console.log(this.products);
        }
    }
})

manager.mount('#manager')