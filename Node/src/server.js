const productRepositoryContainer = require('./data/repositories/products');
const productServiceContainer = require('./domain/products/service');
const shoppingCartServiceContainer = require('./domain/shoppingCarts/service');
const shoppingCartRepositoryContainer = require('./data/repositories/shoppingCarts');
const shoppingCartValidationServiceContainer = require('./common/externalService/shoppingCartValidation');

const appContainer = require('./router/http/app')
const db = require('./data/infrastructure/db')({dbConnectionString: 'mongodb://127.0.0.1:27017/shopping-cart'});

const productRepository = productRepositoryContainer.init(db.schemas);
const shoppingCartRepository = shoppingCartRepositoryContainer.init(db.schemas);
const shoppingCartValidationService = shoppingCartValidationServiceContainer.init();

const shoppingCartService = shoppingCartServiceContainer.init({shoppingCartRepository, shoppingCartValidationService});
const productService = productServiceContainer.init({productRepository});

const app = appContainer.init({ productService, shoppingCartService });

let server = app.listen('3000', () => {
  console.log('App listening on port 3000!');
});

(async () => {
  try {
    await db.connect();
  } catch (error) {
    await db.close();
    await server.close();
  }
})();
