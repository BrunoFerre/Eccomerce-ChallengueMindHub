const { createApp } = Vue
createApp({
    data() {
        return {
            client: [],
            purchase: [],
        };
    },
    created() {
        this.getData();
    },
    methods: {
        getData() {
            axios.get('/api/purchase/history')
                .then((response) => {
                    const purchase = response.data;
                    this.purchase = purchase
                    console.log(this.purchase);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        addAddress() {
            
        }
    }
}).mount('#app')