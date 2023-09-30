const { createApp } = Vue;
createApp({
    data() {
        return {
            addRess: {},
            idAddress: '',
            street: '',
            number: '',
            city: '',
            apartment: '',
            floor: '',
        }
    },
    created() {
        this.getAddress()
    },
    methods: {
        getAddress() {
            axios.get('/api/address')
                .then(response => {
                    this.addRess = response.data
                }).catch(error => {
                    console.log(error)
                })
        },
        addAddress() {
            let Address = {
                street: this.street,
                number: this.number,
                city: this.city,
                apartment: this.apartment,
                floor: this.floor,
            }
            Swal.fire({
                title: 'Add a new Address?',
                input: 'text',
                inputAttributes: {
                    autocapitalize: 'off',
                },
                showCancelButton: true,
                confirmButtonText: 'Add',
                showLoaderOnConfirm: true,
                preConfirm: login => {
                    return axios
                        .post("/api/address/add", Address)
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
        removeAddress() {
            Swal.fire({
                title: 'Remove Address?',
                input: 'text',
                inputAttributes: {
                    autocapitalize: 'off',
                },
                showCancelButton: true,
                confirmButtonText: 'Remove',
                showLoaderOnConfirm: true,
                preConfirm: login => {
                    return axios
                        .post(`/api/address/remove/${this.idAddress}`)
                        .then(response => {
                            location.reload();
                        })
                        .catch(error => {
                            Swal.fire({
                                icon: 'error',
                                text: error.response.data,
                                confirmButtonColor: '#5b31be93',
                            })
                        })
                }
            })
        }
    }
}).mount('#app');