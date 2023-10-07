
const { createApp } = Vue;

createApp({
    data() {
        return {
            cart: [],
            showForm: false,
            cardNumber: "",
            dueDate: "",
            cvv: "",
            client: [],
        };
    },
    created() {
        this.getData();
        this.getPerson();
    },
    methods: {
        getPerson() {
            axios.get('/api/person/current')
                .then((response) => {
                    this.client = response.data;
                    console.log(this.client);
                    this.purchase = this.client.purchaseOrder
                    console.log(this.purchase);
                }).catch((error) => {
                    console.log(error);
                });
        },
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
            } else {
                this.cart.push({ ...product, quantity: 1 });
            }
            localStorage.setItem('cart', JSON.stringify(this.cart));
        },
        minusItem(index) {
            const item = this.cart[index]
            if (item.quantity > 1) {
                item.quantity--;
            } else if (item.quantity === 1) {
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
        pay() {
            let payObj = {
                addressId: this.client.address[0].id,
                paymentMethod: "DEBIT",
                details: []
            }
            for (const product of this.cart) {
                this.obj = {
                    productId: product.id,
                    quantity: product.quantity
                }
                payObj.details.push(this.obj)
            }
            console.log(payObj)
            axios.post("/api/purchase/purchaseOrder", payObj)
                .then(response => {
                    console.log(response);
                    const ticket = response.data
                    console.log(ticket);
                    axios.get(`/api/ticket?ticket=${ticket}`, { responseType: 'blob' })
                        .then(response => {
                            const blob = new Blob([response.data], { type: 'application/pdf' });
                            console.log(blob);
                            const url = window.URL.createObjectURL(blob);
                            const a = document.createElement('a');
                            a.href = url;
                            a.download = 'ticket.pdf';
                            a.click();
                            window.URL.revokeObjectURL(url);
                            this.clearCart()
                            location.reload()
                        })
                        .catch(error => {
                            console.log(error)
                        })
                })
                .catch(error => {
                    console.log(error.response.data.text()
                        .then(res => {
                            console.log(res);
                        }));
                });
        },
        showFormPay() {
            this.showForm = !this.showForm;
        },
        sendBuy() {
            let total = this.calculateTotal()
            let cardDate = {
                cardNumber: this.cardNumber,
                cvv: this.cvv,
                amount: total,
                description: "Payment for the purchase in the store: Mattoof",
            }

            console.log(cardDate)
            Swal.fire({
                title: 'Do you want to make the purchase?',
                inputAttributes: {
                    autocapitalize: 'off',
                },
                showCancelButton: true,
                confirmButtonText: 'Yes',
                showLoaderOnConfirm: true,
                preConfirm: login => {
                    return axios.post("https://homebanking-production-0510.up.railway.app/api/payment_point", cardDate).then(response => {
                        console.log(response);
                        this.pay()
                    })
                        .catch(error => {
                            console.log(error)

                        })
                }
            })
        },
        formatPrice(number) {
            let reset = new Intl.NumberFormat('en-En', { style: 'currency', currency: 'USD' })
            let balanceFormat = reset.format(number)
            return balanceFormat
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
    }
}).mount('#app');