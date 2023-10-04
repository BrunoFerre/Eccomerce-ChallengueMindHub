const { createApp } = Vue
createApp({
    data() {
        return {
            client: [],
            purchase: [],
            showForm: true,
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
        }
    }
}).mount('#app')