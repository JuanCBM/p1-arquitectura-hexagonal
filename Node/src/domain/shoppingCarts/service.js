function init({ shoppingCartRepository, shoppingCartValidationService }) {

  async function create() {
    return await shoppingCartRepository.createShoppingCart();
  }

  async function addProductWithQuantity({ shoppingCart, product, quantity }) {
    const shoppingCartFound = await shoppingCartRepository.updateShoppingCart({ shoppingCart, product, quantity });
    console.log('shoppingCartFound', shoppingCartFound);
    return shoppingCartFound;
  }

  async function findById( {shoppingCart} ){
    return await shoppingCartRepository.getShoppingCart({ shoppingCart });
  }

  async function updateStatus( {id} ){
    if(shoppingCartValidationService.validateShoppingCart({id})){
      return await shoppingCartRepository.updateStatusShoppingCart({ id });
    }else{
      throw new Error('Product not available');
    }
  }

  return {
    create,
    addProductWithQuantity,
    findById,
    updateStatus
  }
}

module.exports.init = init;