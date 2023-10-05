const app = Vue.createApp({
    data() {
        return {
            productDetails: null,
            selectedImage: '',
            productImages: [
                '../images/1.png',
                '../images/2.jpeg',
                '../images/2.jpeg',
            ],
            products: [],
            cart: [],
        };
    },
    created() {
        const productId = new URLSearchParams(window.location.search).get('id');
        this.loadProductDetails(productId);
        this.cards()
    },
    methods: {
        loadProductDetails(productId) {
            this.cart = JSON.parse(localStorage.getItem('cart')) ?? [];
            axios.get(`/api/products/${productId}`)
                .then((response) => {
                    this.productDetails = response.data;
                    this.productImages.push(response.data.image);
                    if (this.productImages.length > 0) {
                        this.selectedImage = this.productImages[3];
                    }
                })
                .catch((error) => {
                    console.error('Error al obtener los detalles del producto', error);
                });
        },
        changeMainImage(imageUrl) {
            this.selectedImage = imageUrl;
        },
        cards() {
            axios.get(`/api/products`)
                .then((response) => {
                    this.products = response.data;
                })
                .catch((error) => {
                    console.error('Error al obtener los productos', error);
                });
        },

        local(product, accion) {
            if (accion == 'add') {
                this.cart.push({ ...product, quantity: 1 });
            }
            localStorage.setItem('cart', JSON.stringify(this.cart))
        }
    },
});


app.mount('#prodetails');
