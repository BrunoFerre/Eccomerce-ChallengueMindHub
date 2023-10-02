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
        };
    },
    created() {
        const productId = new URLSearchParams(window.location.search).get('id');
        this.loadProductDetails(productId);
        this.cards()
    },
    methods: {
        loadProductDetails(productId) {
            axios.get(`https://fakestoreapi.com/products/${productId}`)
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
            axios.get('https://fakestoreapi.com/products')
                .then((response) => {
                    this.products = response.data;
                })
                .catch((error) => {
                    console.error('Error al obtener los productos', error);
                });
        },
    },
});


app.mount('#prodetails');
