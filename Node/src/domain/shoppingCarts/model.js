class ShoppingCart {
  constructor({ _id, products, completed } = {}) {
    this.id = _id;
    this.products = products;
    this.completed = completed;
  }
}

module.exports = ShoppingCart;