class ShoppingCartResponse {
  constructor({ id, products, completed } = {}) {
    this.id = id;
    this.products = products;
    this.completed = completed;

  }
}

module.exports = ShoppingCartResponse;
