let express = require('express');

let productRouter = require('./routes/products/routes');
let shoppingCartRouter = require('./routes/shoppingCarts/routes');

const http = require('http');

let app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

module.exports.init = (services) => {
  app.use('/api/products', productRouter.init(services));
  app.use('/api/shoppingcarts', shoppingCartRouter.init(services));
  const httpServer = http.createServer(app);
  return httpServer;
};
