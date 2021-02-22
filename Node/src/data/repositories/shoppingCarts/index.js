const mapper = require('../../mapper');
const ShoppingCartDomainModel = require('../../../domain/shoppingCarts/model');

const shoppingCartStore = {

  async createShoppingCart() {
    try {
      const { ShoppingCart : shoppingCartSchema } = this.getSchemas();
      const shoppingCart = new shoppingCartSchema({
        completed: false
      });
      const savedCart = await shoppingCart.save();
      return mapper.toDomainModel(savedCart, ShoppingCartDomainModel);
    } catch (e) {
      throw e;
    }
  },
  async getShoppingCart(params) {
    try {
      const { ShoppingCart : shoppingCartSchema } = this.getSchemas();
      const shoppingCart = await shoppingCartSchema.findOne({_id : params.shoppingCart});
      if(!shoppingCart) {
        throw new Error('Shopping cart not found');
      }
      shoppingCart.populate('products.product').execPopulate();
      return mapper.toDomainModel(shoppingCart, ShoppingCartDomainModel);
    } catch (error) {
      throw error;
    }
  },

  async updateStatusShoppingCart(params) {
    try {
      const { ShoppingCart : shoppingCartSchema } = this.getSchemas();
      const shoppingCart = await shoppingCartSchema.findOneAndUpdate(
          {_id:params.id},
          {completed: true},
          {new: true}
      );
      if(!shoppingCart) {
        throw new Error('Shopping cart not found');
      }
      shoppingCart.populate('products.product').execPopulate();
      return mapper.toDomainModel(shoppingCart, ShoppingCartDomainModel);
    } catch (error) {
      throw error;
    }
  },

  async updateShoppingCart(params) {
    try {
      const { ShoppingCart : shoppingCartSchema } = this.getSchemas();
      const { Product : productSchema } = this.getSchemas();
      const shoppingCartFound = await shoppingCartSchema.findOne({ _id: params.shoppingCart });
      const productToUpdate = shoppingCartFound.products.filter(item => item.product == params.product)[0];
      if (!productToUpdate){
        const productFound = await productSchema.findOne({ _id: params.product });
        if(!!productFound){
          shoppingCartFound.products.push({ product: mapper.toObjectId(params.product), quantity: params.quantity });
        } else {
          throw new Error('Product not found');
        }
      } else {
        productToUpdate.quantity = Number(params.quantity) + Number(productToUpdate.quantity);
      }
      const shoppingCartSaved = await shoppingCartFound.save();

      return mapper.toDomainModel(shoppingCartSaved, ShoppingCartDomainModel);
    } catch (e) {
      throw e;
    }
  },
  async deleteById(params) {
    try {
      const { ShoppingCart : shoppingCartSchema } = this.getSchemas();
      const shoppingCart = await shoppingCartSchema.findOne({ _id: params.id });
      if(!shoppingCart) {
        throw new Error('Shopping cart not found');
      }
      await shoppingCartSchema.deleteOne({ _id: params.id });
      return mapper.toDomainModel(shoppingCart, ShoppingCartDomainModel);
    } catch (error) {
      throw error;
    }
  },
  async removeProduct(params) {
    try {
      const { ShoppingCart : shoppingCartSchema } = this.getSchemas();
      const shoppingCartFound = await shoppingCartSchema.findOne({ _id: params.shoppingCart });
      if (!shoppingCartFound) {
        throw new Error('Shopping cart not found');
      }
      shoppingCartFound.products = shoppingCartFound.products.filter(
          item => item.product.toString() !== params.product);
      const shoppingCartSaved = await shoppingCartFound.save();

      return mapper.toDomainModel(shoppingCartSaved, ShoppingCartDomainModel);
    } catch (e) {
      throw e;
    }
  }
};

module.exports.init = ({ ShoppingCart, Product }) => Object.assign(Object.create(shoppingCartStore), {
  getSchemas() { return { ShoppingCart, Product }; }
});