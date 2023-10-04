
const appLogin = Vue.createApp({
    data() {
        return {
            email: '',
            password: '',
            firstName: '',
            lastName: '',
            showRegistrationForm: false,
            isLogin: false
        };
    },
    created() {
        this.verifyiLogin()
    },
    methods: {
        login() {
            axios.post('/api/login', 'email=' + this.email + '&password=' + this.password)
                .then(response => {
                    if (this.email.includes('@admi')) {
                        location.href = 'pages'
                    } else {
                        location.href = './shop.html'
                    }
                    localStorage.setItem('isLoggedIn', 'true')
                    this.verifyiLogin()
                }).catch(error => {
                    console.log(error)
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Something went wrong!',
                        footer: 'Please try again!'
                    })
                })
        },
        register() {
            let Person = {
                firstname: this.firstName,
                lastname: this.lastName,
                email: this.email,
                password: this.password,
            }
            console.log(Person);
            Swal.fire({
                title: 'Register Matoffee?',
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
                            this.login();
                        })
                        .catch(error => {
                            console.log(error);
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
        toggleForm() {
            this.showRegistrationForm = !this.showRegistrationForm;
        },
        logOut() {
            axios.post('/api/logout')
                .then(response => {
                    this.isLogin = false
                    console.log(response);
                    localStorage.removeItem('isLoggedIn');
                    window.location.reload();
                })
                .catch(error => {
                    console.log(error);
                })
        },
        verifyiLogin() {
            let login = localStorage.getItem('isLoggedIn');
            if (login) {
                this.isLogin = true
            } else {
                this.isLogin = false
            }
        }
    },
});
appLogin.mount('#login');