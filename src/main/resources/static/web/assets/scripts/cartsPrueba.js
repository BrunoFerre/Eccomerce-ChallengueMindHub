
const { createApp } = Vue;

createApp({
    data() {
        return {
            cart: [],
            obj:{}
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
            let payObj={
                addressId:1,
                paymentMethodId:"DEBIT",
                details:[]
            }
            for (const product  of this.cart) {
                this.obj={
                    productId:product.id,
                    quantity:product.quantity
                }
                payObj.details.push(this.obj)
            }
            console.log(payObj)
            Swal.fire({
                title: 'Do you want to make the purchase?',
                inputAttributes: {
                    autocapitalize: 'off',
                },
                showCancelButton: true,
                confirmButtonText: 'Yes',
                showLoaderOnConfirm: true,
                preConfirm: login => {
                    return axios
                        .post("/api/purchase/purchaseOrder", payObj)
                        .then(response => {
                            Swal.fire({
                                icon: 'success',
                                text: 'The purchase was successful,Do you want to print your invoice?',
                                inputAttributes: {
                                    autocapitalize: 'off',
                                },
                                showCancelButton: true,
                                confirmButtonColor: 'Yes',
                                showLoaderOnConfirm: true,
                                preConfirm: login => {
                                    axios.get('/api/ticket', 'ticket=' + response.data,{responseType: 'blob'})
                                        .then(response => {
                                            const blob = new Blob([response.data], { type: 'application/pdf' });
                                            const link = window.URL.createObjectURL(blob);
                                            const a = document.createElement('a');
                                            a.href = link;
                                            a.download = 'ticket.pdf';
                                            a.click();
                                            window.URL.revokeObjectURL(link);
                                        }).catch(error => {
                                            const e = error.response.data.text().then(e => {
                                                Swal.fire({
                                                    icon: 'error',
                                                    text: e,
                                                    confirmButtonColor: '#5b31be93',
                                                })
                                            })
                                        })
                                }
                            })
                        })
                        .catch(error => {
                            console.log(error)
                            Swal.fire({
                                icon: 'error',
                                text: error.response.data,
                                confirmButtonColor: '#5b31be93',
                            })
                        });
                },
                allowOutsideClick: () => !Swal.isLoading(),
            });
        }
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