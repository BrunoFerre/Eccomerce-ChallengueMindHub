const { createApp } = Vue
createApp({
    data() {
        return {
            client: [],
            purchase: [],
            street: "",
            number: "",
            city: "",
            apartment: "",
            floor: "",
            zip: "",
        };
    },
    created() {
        this.getData();
    },
    methods: {
        getData() {
            axios.get('/api/person/current')
                .then((response) => {
                    this.client = response.data;
                    console.log(this.client);
                    this.purchase = this.client.purchaseOrder
                    console.log(this.purchase);
                }).catch((error) => {
                    console.log(error);
                });
            axios.get('/api/purchase/history')
                .then((response) => {
                    const purchase = response.data;
                    this.purchase = purchase
                    console.log(this.purchase);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        addAddress() {
            const addAddress = {
                street: this.street,
                number: this.number,
                city: this.city,
                apartament: this.apartment,
                floor: this.floor,
                zipCode: this.zip,
            }
            console.log(addAddress)
            Swal.fire({
                title: 'Add a new Address?',
                inputAttributes: {
                    autocapitalize: 'off',
                },
                showCancelButton: true,
                confirmButtonText: 'Add',
                showLoaderOnConfirm: true,
                preConfirm: login => {
                    return axios
                        .post("/api/adress/add", addAddress)
                        .then(response => {
                            location.reload();
                        })
                        .catch(error => {
                            Swal.fire({
                                icon: 'error',
                                text: error.response.data,
                                confirmButtonColor: '#5b31be93',
                            })
                        });
                }
            })
        },
        getTicket(ticket) {
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
                    location.reload()
                })
                .catch(error => {
                    console.log(error)
                })
        },
        formatPrice(number) {
            let reset = new Intl.NumberFormat('en-En', { style: 'currency', currency: 'USD' })
            let balanceFormat = reset.format(number)
            return balanceFormat
        },
    }
}).mount('#app')