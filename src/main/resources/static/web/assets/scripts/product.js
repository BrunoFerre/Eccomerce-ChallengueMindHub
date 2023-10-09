const app = Vue.createApp({
    data() {
        return {
            productDetails: null,
            selectedImage: '',
            productImages: [

            ],
            products: [],
            productId: null,
            productsPage: []
        };
    },
    created() {
        this.productId = new URLSearchParams(window.location.search).get('id');
        this.loadProductDetails(this.productId);
        this.cards()
        this.getData();
    },
    methods: {
        loadProductDetails() {
            axios.get(`/api/products/${this.productId}`)
                .then((response) => {
                    this.productDetails = response.data;
                    this.productImages = this.productDetails.imageCollection;
                    this.productImages.push(response.data.image);
                    if (this.productImages.length > 0) {
                        this.selectedImage = this.productImages[1];
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
        getData() {
            axios.get('/api/products')
                .then((response) => {
                    const products = response.data;
                    this.products = products.sort((a, b) => a.id - b.id);
                    this.categoryProducts = [...new Set(this.products.map(product => product.category))]
                    this.getProductHome();
                })
                .catch((error) => {
                    console.error('Error al obtener los productos', error);
                });
            this.cart = JSON.parse(localStorage.getItem('cart')) ?? [];
        },
        getProductHome() {
            console.log(this.products);
            this.productsPage = this.products.filter((product) => {
                return product.discount > 0
            }).splice(0, 4)
            console.log(this.productsPage);
        },
        formatPrice(number) {
            let reset = new Intl.NumberFormat('en-En', { style: 'currency', currency: 'USD' })
            let balanceFormat = reset.format(number)
            return balanceFormat
        },
    },
});


app.mount('#prodetails12');