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