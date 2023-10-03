const { createApp } = Vue;

const app1 = createApp({
    data() {
        return {
            cart: [],
            spinner: true
        };
    },
    created() {
        axios
            .get('/api/products')
            .then((res) => {
                this.spinner = false;
                this.products = res.data;
                console.log(this.products);
            })
            .catch((err) => {
                console.log(err);
            });
        this.loadCartFromLocalStorage();
    },
    methods: {
        addToCart(product) {
            const existingCartItemIndex = this.cart.findIndex(item => item.id === product.id);

            if (existingCartItemIndex !== -1) {
                // Si el producto ya existe en el carrito, aumenta la cantidad
                this.cart[existingCartItemIndex].quantity++;
            } else {
                // Si es un nuevo producto, agrégalo al carrito
                this.cart.push({ ...product, quantity: 1 });
            }

            this.saveCartToLocalStorage();
        },
        saveCartToLocalStorage() {
            localStorage.setItem('cart', JSON.stringify(this.cart));
        },
        loadCartFromLocalStorage() {
            const savedCart = localStorage.getItem('cart');
            if (savedCart) {
                this.cart = JSON.parse(savedCart);
            }
        },
        calculateTotal() {
            return this.cart.reduce((total, item) => total + item.price * item.quantity, 0);
        },
        removeFromCart(index) {
            const item = this.cart[index];
            if (item.quantity > 1) {
                // Si hay más de un elemento de este producto en el carrito, disminuye la cantidad
                item.quantity--;
            } else {
                // Si solo hay un elemento de este producto, elimina el producto del carrito
                this.cart.splice(index, 1);
            }

            this.saveCartToLocalStorage();
        },
        removeAllFromCart(id) {
            this.cart = this.cart.filter((item) => id !== item.id);
        },
        plusItem(index) {
            const item = this.cart[index];
            item.quantity++;
        }

    },
    computed: {
        prueba() {
            console.log(this.cart);
        },
    },
});

app1.mount('#app');