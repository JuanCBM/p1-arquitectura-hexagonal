function init({ shoppingCartRepository, shoppingCartValidationService }) {

  async function create() {
    return await shoppingCartRepository.createShoppingCart();
  }

  async function addProductWithQuantity({ shoppingCart, product, quantity }) {
    const shoppingCartFound = await shoppingCartRepository.updateShoppingCart({ shoppingCart, product, quantity });
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

  async function deletedById({ id }) {
    return await shoppingCartRepository.deleteById({ id });
  }

  async function removeProduct({ shoppingCart, product }) {
    return await shoppingCartRepository.removeProduct({ shoppingCart, product });
  }

  return {
    create,
    addProductWithQuantity,
    findById,
    updateStatus,
    deletedById,
    removeProduct
  }
}

module.exports.init = init;