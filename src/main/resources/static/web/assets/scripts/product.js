const { createApp } = Vue;
createApp({
    data() {
        return {
            products: [],
            byCategory: [],
            byPrice: [],
            categoryProducts: [],
            category: '',

        }
    },
    created() {
        this.getProducts()
    },
    methods: {
        getProducts() {
            axios.get('/api/products')
                .then(response => {
                    this.products = response.data
                    this.categoryProducts =[...new Set(this.products.map(product => product.category))]
                }).catch(error => {
                    console.log(error)
                })
        },
        addProduct() {

        },
        purchaseProduct() {
        }
    },
    computed: {
        filterByCategory() {
            this.byCategory = this.products.filter((product) => {
                return product.category === this.category
            })
            
        },
        filterByPrice() {
            this.byPrice = this.products.sort((a, b) => {
                return a.price - b.price
            })  
        }
    }
}).mount('#app')