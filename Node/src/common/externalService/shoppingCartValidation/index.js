function init() {

  function validateShoppingCart({id}) {
    return (Math.random() < 0.5);
  }
  return {
    validateShoppingCart
  }
}

module.exports.init = init;