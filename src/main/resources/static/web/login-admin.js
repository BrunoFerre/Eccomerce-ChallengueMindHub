const appLogin = Vue.createApp({
    data() {
        return {
            email: 'fbrunomarcos@gmail.com',
            password: 'pass123',
            isLogin: false
        };
    },
    created() {

    },
    methods: {
        login() {
            axios.post('/api/login', 'email=' + this.email + '&password=' + this.password)
                .then(response => {
                    if (this.email.includes('@admi')) {
                        location.href = 'pages'
                    } else {
                        location.href = './manager.html'
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
    }

}).mount('#login')