const app = Vue.createApp({
    data() {
        return {
            products: [],
            cart: [],
            spinner: true,
            categoryProducts: [],
            checkCategory: [],
            inputSearch: '',
            fil: [],
            filerByPrice: [],
        };
    },
    created() {
        this.getData();
    },
    methods: {
        getData() {
            axios.get('http://localhost:8080/api/products')
                .then((response) => {
                    const products = response.data;
                    this.products = products
                    console.log(this.products);
                    this.categoryProducts = [...new Set(this.products.map(product => product.category))]
                    console.log(this.categoryProducts);
                })
                .catch((error) => {
                    console.error('Error al obtener los productos', error);
                });
            this.loadCartFromLocalStorage();
        },
        // Button Cart
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
        },
        orderMinorToMajor() {
            this.filterByPrice = this.products.sort((a, b) => {
                return a.price - b.price
            })
        },
        orderMajorToMinor() {
            this.filterByPrice = this.products.sort((a, b) => {
                return b.price - a.price
            })
        },
        orderByName() {
            this.filterByPrice = this.products.sort((a, b) => {
                return a.name.localeCompare(b.name)
            })
        },
        orderByNameReverse() {
            this.filterByPrice = this.products.sort((a, b) => {
                return b.name.localeCompare(a.name)
            })
        },
        clearFilters() {
            location.reload();
        },
        coffeeShop(){
        this.fil = this.products.filter((product) => {
            return product.name.includes('coffee') || product.description.includes('coffee')
        })  
        },
        mateShop(){
        this.fil = this.products.filter((product) => {
            return product.name.includes('mate') || product.description.includes('mate')
        })
        }
    },
    computed: {
        filters() {
            this.fil = this.products.filter(product => {
                return product.description.toLowerCase().includes(this.inputSearch.toLowerCase()) && (this.checkCategory.includes(product.category) || this.checkCategory.length == 0);
            })
        }
    },
});

app.mount('#app');