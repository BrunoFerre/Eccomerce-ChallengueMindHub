const { createApp } = Vue;
createApp({
    data() {
        return {
            firstname: '',
            lastname: '',
            email: '',
            phone: '',
            password: '',
        }
    },
    methods: {
        register() {
            let Person = {
                firstname: this.firstname,
                lastname: this.lastname,
                email: this.email,
                phone: this.phone,
                password: this.password,
            }
            Swal.fire({
                title: 'Register Matoffee?',
                inputAttributes: {
                    autocapitalize: 'off',
                },
                showCancelButton: true,
                confirmButtonText: 'Yes',
                showLoaderOnConfirm: true,
                preConfirm: login => {
                    this.emailAddress = this.email + "@" + this.emailType
                    return axios
                        .post("/api/person/add",  Person)
                        .then(response => {
                                this.logIn();
                        })
                        .catch(error => {
                            Swal.fire({
                                icon: 'error',
                                text: error.response.data,
                                confirmButtonColor: '#5b31be93',
                            })
                        });
                },
                allowOutsideClick: () => !Swal.isLoading(),
            });
        },
        login() {
            axios.post('/login','email='+this.email+'&password='+this.password)
            .then(response => {
                if(this.email.includes('@admi')){
                    location.href = 'pages'
                }else{
                    location.href = 'index'
                }
            }).catch(error => {
                console.log(error)
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Something went wrong!',
                    footer: 'Please try again!'
            })
            })
        }
    },
}).mount('#register')