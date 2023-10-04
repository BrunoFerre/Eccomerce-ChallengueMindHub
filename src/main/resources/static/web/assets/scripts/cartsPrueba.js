
const { createApp } = Vue;

createApp({
    data() {
        return {
            cart: [],
        };
    },
    created() {
        this.getData();
    },
    methods: {
        getData() {
            this.cart = JSON.parse(localStorage.getItem('cart')) ?? [];
            axios.get('/api/products')
                .then((res) => {
                    this.spinner = false;
                    this.products = res.data;
                    console.log(this.products);
                })
                .catch((err) => {
                    console.log(err);
                });
        },
        plusItem(product) {
            const exist = this.cart.findIndex((p) => p.id === product.id);
            if (exist !== -1) {
                this.cart[exist].quantity++;
            }else{
                this.cart.push({...product, quantity: 1});
            }
            localStorage.setItem('cart', JSON.stringify(this.cart));
        },
        minusItem(index) {
            const item = this.cart[index]
            if (item.quantity > 1) {
                item.quantity--;
            }else if(item.quantity === 1){
                this.removeItem(index)
            }
        },
        calculateTotal() {
            return this.cart.reduce((acc, item) => acc + (item.price * item.quantity), 0);
        },
        calculateSubtotal() {
            return this.cart.reduce((acc, item) => acc + (item.quantity * item.price), 0);
        },
        clearCart() {
            this.cart = [];
            localStorage.removeItem('cart');
        },
        removeItem(index) {
            this.cart.splice(index, 1);
            localStorage.setItem('cart', JSON.stringify(this.cart));
        },
    },
    computed: {
        changeStorage() {
            window.addEventListener('storage', (event) => {
                if (event.key === 'cart') {
                    this.cart = JSON.parse(localStorage.getItem('cart')) ?? [];
                }
            }) 
        }
    }

}).mount('#app');