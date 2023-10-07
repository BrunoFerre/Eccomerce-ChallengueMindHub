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
            page2: [],
            remaingProducts: [],
            cart: [],
            productHome: [],
        };
    },
    created() {
        this.getData();
    },
    methods: {
        getData() {
            axios.get('/api/products')
                .then((response) => {
                    const products = response.data;
                    this.products = products.sort((a, b) => a.id - b.id);
                    this.categoryProducts = [...new Set(this.products.map(product => product.category))]
                    this.getProductHome();
                })
                .catch((error) => {
                    console.error('Error al obtener los productos', error);
                });
            this.cart = JSON.parse(localStorage.getItem('cart')) ?? [];
        },
        // Button Cart
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
        addToCart(product) {
            const cartItem = this.cart.find(item => item.id === product.id);

            if (cartItem) {
                // Si el producto ya está en el carrito, aumenta la cantidad
                cartItem.quantity++;
            } else {
                // Si el producto no está en el carrito, agrégalo con cantidad 1
                this.cart.push({ ...product, quantity: 1 });
            }

            // Guarda el carrito actualizado en el almacenamiento local
            this.saveCartToLocalStorage();
        },
        saveCartToLocalStorage() {
            localStorage.setItem('cart', JSON.stringify(this.cart));
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
        coffeeShop() {
            this.fil = this.products.filter((product) => {
                return product.name.includes('coffee') || product.description.includes('coffee')
            })
        },
        mateShop() {
            this.fil = this.products.filter((product) => {
                return product.name.includes('mate') || product.description.includes('mate')
            })
        },
        page(page) {
            if (page == 1) {
                this.fil = this.products.slice(0, 18).sort((a, b) => {
                    return a.id - b.id;
                })
            } else {
                this.fil = this.products.slice((page - 1) * 18, page * 18).sort((a, b) => {
                    return a.id - b.id;
                })
            }
        },
        local(product, accion) {
            if (accion == 'add') {
                this.cart.push({ ...product, quantity: 1 });
            }
            localStorage.setItem('cart', JSON.stringify(this.cart))
        },
        getProductHome() {
            console.log(this.products);
            this.productHome = this.products.filter((product) => {
                return product.discount > 0
            }).splice(0, 4)
            console.log(this.productHome);
        },
        formatPrice(number) {
            let reset = new Intl.NumberFormat('en-En', { style: 'currency', currency: 'USD' })
            let balanceFormat = reset.format(number)
            return balanceFormat
        },
    },
    computed: {
        filters() {
            this.fil = this.products.filter(product => {
                return product.description.toLowerCase().includes(this.inputSearch.toLowerCase()) && (this.checkCategory.includes(product.category) || this.checkCategory.length == 0);
            }).slice(0, 16).sort((a, b) => {
                return a.id - b.id;
            });
        },
        changeStorage() {
            window.addEventListener('storage', (event) => {
                if (event.key === 'cart') {
                    this.cart = JSON.parse(localStorage.getItem('cart') ?? []);
                }
            })
        },
    }
});
app.mount('#app');