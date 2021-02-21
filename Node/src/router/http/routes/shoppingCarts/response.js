class ShoppingCartResponse {
  constructor({ id, products, completed } = {}) {
    console.log('ShoppingCartResponse: ', id, products);

    this.id = id;
    this.products = products;
    this.completed = completed;

  }
}

module.exports = ShoppingCartResponse;
