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
            pageA: [],
            remaingProducts: [],
            cart: [],
            isLogin: false,
            productosPorPag: 18,
        };
    },
    created() {
        this.getData();
        this.isLoginV();
    },
    methods: {
        getData() {
            axios.get('/api/products')
                .then((response) => {
                    const products = response.data;
                    this.products = products.sort((a, b) => a.id - b.id);
                    this.categoryProducts = [...new Set(this.products.map(product => product.category))]
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
        page() {
            const start = (this.page - 1) * this.productosPorPag;
            const end = start + this.productosPorPag;
            if(start< this.products.length){
                const newProduct = this.products.slice(start, end);
                this.products = this.products.concat(newProduct);
                this.page++;
            }
        },
        local(product, accion) {
            if (accion == 'add') {
                this.cart.push({ ...product, quantity: 1 });
            }
            localStorage.setItem('cart', JSON.stringify(this.cart))
        },
        isLoginV() {
            let login = localStorage.getItem('isLoggedIn');
            if (login) {
                this.isLogin = true
            } else {
                this.isLogin = false
            }
        },
        logOut() {
            axios.post('/api/logout')
                .then(response => {
                    console.log(response);
                    window.location.reload();
                })
                .catch(error => {
                    console.log(error);
                })
        },

    },
    computed: {
        filters() {
            this.fil = this.products.filter(product => {
                return product.description.toLowerCase().includes(this.inputSearch.toLowerCase()) && (this.checkCategory.includes(product.category) || this.checkCategory.length == 0);
            }).slice(0, this.productosPorPag).sort((a, b) => {
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