const ShoppingCart = require('../../../src/domain/shoppingCarts/model');
const Product = require('../../../src/domain/products/model');

const shoppingCartServiceFactory = require(
    '../../../src/domain/shoppingCarts/service');

function createShoppingCart() {
  return new ShoppingCart({products: createProducts(), completed: false});
}

function createEmptyShoppingCart() {
  return new ShoppingCart({products: [], completed: false});
}

function createProducts() {
  const p1 = new Product({name: 'test1', description: 'Monitor', _id: '1'});
  return [p1];
}

describe('Shopping cart unit tests', () => {

  test('Given shopping cart When create Then OK', () => {
    const createShoppingCart = jest.fn();
    createShoppingCart.mockReturnValue(createEmptyShoppingCart());
    let shoppingCartRepository = {createShoppingCart};
    let shoppingCartService = shoppingCartServiceFactory.init(
        {shoppingCartRepository});

    shoppingCartService.create().then((shoppingCart) => {
      expect(shoppingCart.products.length).toBe(0);
      expect(shoppingCart.completed).toBeFalsy();
    });

  });

  test('Given shopping cart, product and quantity When add product Then OK',
      () => {
        let shoppingCartToUpdate = createShoppingCart();
        let productToAdd = createProducts()[0];
        let quantity = 1;

        const updateShoppingCart = jest.fn();
        updateShoppingCart.mockReturnValue(shoppingCartToUpdate);
        let shoppingCartRepository = {updateShoppingCart};
        let shoppingCartService = shoppingCartServiceFactory.init(
            {shoppingCartRepository});

        shoppingCartService.addProductWithQuantity(
            {shoppingCartToUpdate, productToAdd, quantity}).then(
            (shoppingCart) => {
              expect(shoppingCart.products.length).toBe(1);
            });

      });

  // TODO: Test other functions

});