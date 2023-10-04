const app = Vue.createApp({
    data() {
        return {
            email: "",
            password: "",
            firstName: "",
            lastName: "",
            showRegistrationForm: null,
            showImageTemplate: true,
        };
    },
    methods: {
        toggleForm(formName) {
            if (this.showRegistrationForm === formName) {
                // Si el formulario que se hizo clic ya está abierto, ciérralo
                this.showRegistrationForm = null;
            } else {
                // De lo contrario, ábrelo
                this.showRegistrationForm = formName;
                // Cierra el template de la imagen
                this.showImageTemplate = false;
            }
        },
        logout() {
            Swal.fire({
                title: 'Are you sure you want to log out?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Yes, log out',
                cancelButtonText: 'No, cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post(`/api/logout`)
                        .then(response => {       
                            Swal.fire({
                                icon: 'success',
                                title: 'Logout Successful',
                                text: 'Your session has been successfully closed.',
                            }).then(() => {
                                window.location.href = '/web/Index.html';
                            });
                        })
                        .catch(error => {
                            console.error('Error logging out', error);
                        });
                }
            });
          },  
      },

      
      
});

app.mount('#app');
