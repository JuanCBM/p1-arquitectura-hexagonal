const productRepositoryContainer = require('./data/repositories/products');
const productServiceContainer = require('./domain/products/service');
const appContainer = require('./router/http/app')
const db = require('./data/infrastructure/db')({dbConnectionString: 'mongodb://127.0.0.1:27017/shopping-cart'});

const productRepository = productRepositoryContainer.init(db.schemas);

const productService = productServiceContainer.init({ productRepository });

const app = appContainer.init({ productService });

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
