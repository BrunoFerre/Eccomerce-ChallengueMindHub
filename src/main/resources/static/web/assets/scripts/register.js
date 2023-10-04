const { createApp } = Vue;
createApp({
    data() {
        return {
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            emailType: "",
            emailAddress: this.email + "@" + this.emailType,
        };
    },
    created() {

    },
    methods: {
        logIn() {
             axios.post('/api/login','email='+this.email+'&password='+this.password)
                .then((response) => {
                    if (this.email.contains('@admin')) {
                        location.href = "./manager.html"
                    } else {
                        location.href = '../pages/shop.html'
                    }
                })
                .catch(error => (error.message))
        },
        register() {
            let Person = {
                firstname: this.firstName,
                lastname: this.lastName,
                email: this.email,
                password: this.password
            }
            Swal.fire({
                title: 'Register Mind Hub Brother?',
                inputAttributes: {
                    autocapitalize: 'off',
                },
                showCancelButton: true,
                confirmButtonText: 'Yes',
                showLoaderOnConfirm: true,
                preConfirm: login => {
                    return axios
                        .post("/api/person/add", Person)
                        .then(response => {
                                this.logIn();
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
}).mount("#register")

