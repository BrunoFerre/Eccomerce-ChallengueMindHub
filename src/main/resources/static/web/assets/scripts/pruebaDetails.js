const app = Vue.createApp({
    data() {
        return {
            productDetails: null,
            selectedImage: '', // Agregamos una variable para rastrear la imagen principal seleccionada
            productImages: [ // Define una matriz de rutas de imágenes
                '../images/1.png',
                '../images/2.jpeg',
                '../images/2.jpeg', 
            ],
            products: [], // Almacenar los productos
        };
    },
    created() {
        // Obtener el ID del producto de la URL
        const productId = new URLSearchParams(window.location.search).get('id');
        this.loadProductDetails(productId);
    },
    methods: {
        loadProductDetails(productId) {
            // Realizar una solicitud para obtener los detalles del producto por su ID
            axios.get(`https://fakestoreapi.com/products/${productId}`)
                .then((response) => {
                    this.productDetails = response.data;
                    // Agregar la imagen de la API al array productImages
                    this.productImages.push(response.data.image);
                    // Establecer la primera imagen como la imagen principal seleccionada
                    if (this.productImages.length > 0) {
                        this.selectedImage = this.productImages[3];
                    }
                })
                .catch((error) => {
                    console.error('Error al obtener los detalles del producto', error);
                });
                
        },
        changeMainImage(imageUrl) {
            // Cambiar la imagen principal seleccionada al hacer clic en una imagen pequeña
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

// Montar la instancia de Vue en el elemento con ID "prodetails"
app.mount('#prodetails');
