const { createApp } = Vue

const manager = createApp({
    data() {
        return {
            products: [],
            productsByStock: [],
            searchValue: "",
            productName: "",
            productDescription: "",
            productPrice: 0,
            productStock: 0,
            productCategory: "",
            productColor: "",
            productDiscount: 0,
            productImage: "",
            imageCollection: [],
            preImageCollection: [],
            updateStockValue: 0,
            updatePriceValue: 0,
            updateDiscountValue: 0,
            deleteProductId: 0,
            updateStockId: 0,
            updatePriceId: 0,
            updateDiscounId: 0,
            fisrtName: "",
            lastName: "",
            email: "",
            password: "",
        };
    },
    created() {
        this.loadProductDetails()
        this.filterByStock()
    },
    methods: {
        loadProductDetails() {
            axios.get(`http://localhost:8080/api/products`)
                .then(response => {
                    this.products = response.data
                    this.filterByStock()
                })
                .catch(error => {
                    console.log(error)
                })
        },
        formatPrice(price) {
            return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(price)
        },
        filterByStock() {
            this.productsByStock = this.products.filter(product => product.stock <= 20)
        },
        filterBySearch() {
            if (this.searchValue.length >= 1) {
                this.productsByStock = this.products.filter(product => product.name.includes(this.searchValue.toUpperCase()))
            } else {
                // Si searchValue es 0 o está vacío, aplicar el filtro de stock
                this.filterByStock();
            }

            console.log(this.productsByStock);
            console.log(this.searchValue);
        },
        addProduct() {
            this.setImageCollection();
            this.productCategory = this.productCategory.toUpperCase();
            this.productColor = this.productColor.toUpperCase();

            // Show a confirmation alert before adding the product
            Swal.fire({
                title: 'Confirm adding the product?',
                text: 'Are you sure you want to add this product?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#45a049',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, add',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    // If the user confirms, send the request to add the product
                    axios.post("http://localhost:8080/api/products/add", {
                        "name": this.productName,
                        "description": this.productDescription,
                        "price": this.productPrice,
                        "stock": this.productStock,
                        "category": this.productCategory,
                        "color": this.productColor,
                        "discount": this.productDiscount,
                        "image": this.productImage,
                        "imageCollection": this.imageCollection
                    })
                        .then(response => {
                            // Success: Show a success alert
                            Swal.fire('Product Added', 'The product has been added successfully.', 'success');
                            console.log(response);
                        })
                        .catch(error => {
                            // Error: Show an error alert with the server message if available, otherwise a generic error message
                            if (error.response && error.response.data) {
                                Swal.fire('Error', error.response.data, 'error');
                            } else {
                                Swal.fire('Error', 'There was an error adding the product.', 'error');
                            }
                            console.log(error);
                        });
                }
            });
        },

        setImageCollection() {
            if (this.preImageCollection.length > 1) {
                this.imageCollection = this.preImageCollection.split(",");
            } else {
                this.imageCollection = [];
            }
        },
        deleteProduct(id, productName) {
            // Show a confirmation alert
            Swal.fire({
                title: `Are you sure you want to delete ${productName}?`,
                text: 'This action cannot be undone',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#45a049',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete',
                cancelButtonText: 'Cancel',
            }).then((result) => {
                if (result.isConfirmed) {
                    // User confirmed deletion, send the delete request
                    axios.delete(`/api/products/${id}`)
                        .then(response => {
                            // Successful deletion, show a success alert
                            Swal.fire('Deleted!', `${productName} has been deleted.`, 'success');
                            window.location.reload();
                        })
                        .catch(error => {
                            // Error during deletion, show an error alert
                            Swal.fire('Error', `There was an error deleting ${productName}.`, 'error');
                            console.log(error);
                        });
                }
            });
        },

        updatePrice() {
            Swal.fire({
                title: 'Confirm Price Update',
                text: 'Are you sure you want to update the price?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#45a049',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, update it',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.patch(`/api/products/price?id=${this.updatePriceId}&price=${this.updatePriceValue}`)
                        .then(response => {
                            Swal.fire({
                                title: 'Price Updated',
                                text: 'The price has been updated successfully.',
                                icon: 'success'
                            });
                            console.log(response);
                        })
                        .catch(error => {
                            Swal.fire({
                                title: 'Error',
                                text: 'There was a problem updating the price.',
                                icon: 'error'
                            });
                            console.log(error);
                        });
                }
            });
        },
        updateStock() {
            Swal.fire({
                title: 'Confirm Stock Update',
                text: 'Are you sure you want to update the stock?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#45a049',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, update it',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.patch(`/api/products/stock?id=${this.updateStockId}&stock=${this.updateStockValue}`)
                        .then(response => {
                            Swal.fire({
                                title: 'Stock Updated',
                                text: 'The stock has been updated successfully.',
                                icon: 'success'
                            });
                            console.log(response);
                        })
                        .catch(error => {
                            Swal.fire({
                                title: 'Error',
                                text: 'There was a problem updating the stock.',
                                icon: 'error'
                            });
                            console.log(error);
                        });
                }
            });
        },
        updateDiscount() {
            Swal.fire({
                title: 'Confirm Discount Update',
                text: 'Are you sure you want to update the discount?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#45a049',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, update it',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.patch(`/api/products/discount?id=${this.updateDiscounId}&discount=${this.updateDiscountValue}`)
                        .then(response => {
                            Swal.fire({
                                title: 'Discount Updated',
                                text: 'The discount has been updated successfully.',
                                icon: 'success'
                            });
                            console.log(response);
                        })
                        .catch(error => {
                            Swal.fire({
                                title: 'Error',
                                text: 'There was a problem updating the discount.',
                                icon: 'error'
                            });
                            console.log(error);
                        });
                }
            });
        }
        ,
        addAdmin() {
            Swal.fire({
                title: 'Confirm Admin Addition',
                text: 'Are you sure you want to add this admin?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#45a049',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, add admin',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post("/api/person/addAdmin", {
                        "firstName": this.fisrtName,
                        "lastName": this.lastName,
                        "email": this.email,
                        "password": this.password
                    })
                        .then(response => {
                            Swal.fire({
                                title: 'Admin Added',
                                text: 'The admin has been added successfully.',
                                icon: 'success'
                            });
                            console.log(response);
                        })
                        .catch(error => {
                            let errorMessage = 'There was a problem adding the admin';
                            if (error.response && error.response.data) {
                                errorMessage += `: ${error.response.data}`;
                            }
                            Swal.fire({
                                title: 'Error',
                                text: errorMessage,
                                icon: 'error'
                            });
                            console.log(error);
                        });
                }
            });
        }
        ,
        logOut() {
            Swal.fire({
                title: 'Confirm Logout',
                text: 'Are you sure you want to log out?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#45a049',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, logout',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post('/api/logout')
                        .then(response => {
                            this.isLogin = false;
                            console.log(response);
                            localStorage.removeItem('isLoggedIn');
                            window.location.href = './login-admin.html';
                        })
                        .catch(error => {
                            console.log(error);
                        });
                }
            });
        }

    },
    computed: {
        prueba() {
            console.log(this.products);
        }
    }
})

manager.mount('#manager')