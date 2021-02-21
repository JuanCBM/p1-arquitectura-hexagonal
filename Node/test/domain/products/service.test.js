const Product = require('../../../src/domain/products/model');
const productServiceFactory = require('../../../src/domain/products/service');

function createProducts() {
  const p1 = new Product({ name: 'test1', description: 'Monitor', _id: '1' });
  const p2 = new Product({ name: 'test2', description: 'Altavoz', _id: '2' });
  return [p1, p2];
}

test('product service test', () => {
  const findAll = jest.fn();
  findAll.mockReturnValue(createProducts());
  let productRepository = { findAll };
  let productService = productServiceFactory.init({productRepository});

  productService.findAllProducts().then((products) => {
    expect(products).to.have.lengthOf(2);
    expect(products).to.eql(createProducts());
    return done();
  });

});





