function create(mongoose) {

  const productSubSchema = mongoose.Schema({
    product: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'Product',
    },
    quantity: {
      type: Number,
    }
  }, {
    _id: false
  });

  const shoppingCartSchema = mongoose.Schema({
        products: [productSubSchema],
        completed: Boolean
      },
      {
        usePushEach: true,
      });

  return mongoose.model('ShoppingCart', shoppingCartSchema);
}

module.exports = create;