const Product = require('../../../src/domain/products/model');
const productServiceFactory = require('../../../src/domain/products/service');

function createProducts() {
  const p1 = new Product({name: 'test1', description: 'Monitor', _id: '1'});
  const p2 = new Product({name: 'test2', description: 'Altavoz', _id: '2'});
  return [p1, p2];
}

describe('Product unit tests', () => {

  test('When get all products Then OK', () => {
    const findAll = jest.fn();
    findAll.mockReturnValue(createProducts());
    let productRepository = {findAll};
    let productService = productServiceFactory.init({productRepository});

    productService.findAllProducts().then((products) => {
      expect(products.length).toBe(2);
      expect(products).toStrictEqual(createProducts());
    });

  });

  test('Given product When create product Then OK', () => {
    const productToCreate = createProducts()[0];
    const productCreated = createProducts()[0];

    const createProduct = jest.fn();
    createProduct.mockReturnValue(productCreated);
    let productRepository = {createProduct};
    let productService = productServiceFactory.init({productRepository});

    productService.create(productToCreate)
    .then((product) => {
      expect(product.name).toStrictEqual(productToCreate.name);
      expect(product.description).toStrictEqual(productToCreate.description)
      expect(product._id).not.toBeNull();
    });

  });

  test('Given product id When delete Then OK', () => {
    const id = 1;
    const productDeleted = createProducts()[0];

    const deleteById = jest.fn();
    deleteById.mockReturnValue(productDeleted);
    let productRepository = {deleteById};
    let productService = productServiceFactory.init({productRepository});

    productService.deleteById(id)
    .then((product) => {
      expect(product.name).toStrictEqual(productDeleted.name);
      expect(product.description).toStrictEqual(productDeleted.description)
      expect(product.id).not.toBeNull();
    });

  });

  test('Given id When get product Then OK', () => {
    const id = 1;
    const productToGet = createProducts()[0];

    const getProduct = jest.fn();
    getProduct.mockReturnValue(productToGet);
    let productRepository = {getProduct};
    let productService = productServiceFactory.init({productRepository});

    productService.findById(id)
    .then((product) => {
      expect(product.name).toStrictEqual(productToGet.name);
      expect(product.description).toStrictEqual(productToGet.description);
      expect(product.id).not.toBeNull();

    });

  });

});