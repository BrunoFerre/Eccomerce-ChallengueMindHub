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
            addressId: null,
            paymentMethod: "",
            details: [],
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
                    this.products = products;
                    this.categoryProducts = [...new Set(this.products.map(product => product.category))]
                })
                .catch((error) => {
                    console.error('Error al obtener los productos', error);
                });
            this.loadCartFromLocalStorage();
        },
        buyCart() {
            const homeBanking = "https://homebanking-production-0510.up.railway.app/api/payment_point"
            const objHB = {
                "cardNumber": "4000 2324 5432 1298",
                "cvv": 331,
                "amount": 200,
                "description": "hola"
            }
            const objMatoffeePrueba = {
                "addressId": 2,
                "paymentMethod": "DEBIT",
                "details": [
                    {
                        "productId": 56,
                        "quantity": 1
                    }
                ]
            }
            axios.post(homeBanking, objHB)
                .then(res => {
                    console.log(res.data, "entro a la peticion");
                    axios.post("/api/purchase/purchaseOrder", objMatoffeePrueba)
                        .then(res => {
                            console.log("Buy Success!");
                            console.log(res.data);
                            const purchaseOrderDTO = res.data;
                            const wantsToDownload = window.confirm("Buy success, ¿do you want to download PDF?")
                            if (wantsToDownload) {
                                axios.get('/api/ticket', purchaseOrderDTO, {
                                    responseType: 'blob' // Especificar que esperamos una respuesta binaria (blob)
                                })
                                    .then(ticketResponse => {
                                        const blob = new Blob([ticketResponse.data], { type: 'application/pdf' });
                                        const blobURL = URL.createObjectURL(blob);
                                        const downloadLink = document.createElement('a');
                                        downloadLink.href = blobURL;
                                        downloadLink.download = 'ticket.pdf';
                                        downloadLink.click();
                                        URL.revokeObjectURL(blobURL);
                                    })
                                    .catch(ticketError => {
                                        console.error(ticketError);
                                    });
                            }

                        })
                        .catch(error => {
                            console.log(error.response.data.text()
                                .then(res => {
                                    console.log(res);
                                }))
                        })
                }
                )
                .catch(error => {
                    console.log(error);
                    console.log("error en la peticion de HomeBanking");
                })
        },
        prueba() {
            axios.get('localhost:8080/api/ticket', {
                "id": 1,
                "amount": 1910.48,
                "date": "2023-10-03T01:53:28.205016",
                "paymentMethod": "DEBIT",
                "personID": 2,
                "details": [
                    {
                        "id": 3,
                        "quantity": 12.0,
                        "price": 12.86,
                        "productID": 51
                    },
                    {
                        "id": 4,
                        "quantity": 1.0,
                        "price": 88.91,
                        "productID": 29
                    },
                    {
                        "id": 1,
                        "quantity": 3.0,
                        "price": 413.67,
                        "productID": 48
                    },
                    {
                        "id": 2,
                        "quantity": 6.0,
                        "price": 71.04,
                        "productID": 8
                    }
                ],
                "adress": {
                    "id": 2,
                    "street": "Calle 123",
                    "number": 12345,
                    "city": "Villa Quien",
                    "apartament": "TuyaBB",
                    "floor": 21,
                    "active": true
                }
            }, { responseType: 'blob' })
                .then(res => {
                    const blob = new Blob([response.data], { type: 'application/pdf' });
                    const url = window.URL.createObjectURL(blob);
                    const a = document.createElement('a');
                    a.href = url;
                    a.download = 'ticket.pdf';
                    a.click();
                    window.URL.revokeObjectURL(url);
                    console.log(res.data);
                    "ticket.pdf"
                })
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
        }
    },
    computed: {
        filters() {
            this.fil = this.products.filter(product => {
                return product.description.toLowerCase().includes(this.inputSearch.toLowerCase()) && (this.checkCategory.includes(product.category) || this.checkCategory.length == 0);
            }).slice(0, 18).sort((a, b) => {
                return a.id - b.id;
            });
        },
    },
});

app.mount('#app');
