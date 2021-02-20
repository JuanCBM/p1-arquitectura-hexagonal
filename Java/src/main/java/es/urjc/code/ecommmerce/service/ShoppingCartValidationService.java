package es.urjc.code.ecommmerce.service;

import es.urjc.code.ecommmerce.domain.model.dto.ShoppingCartProductDTO;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartValidationService {

  public boolean validateShoppingCart(List<ShoppingCartProductDTO> products) {
    return new Random().nextBoolean();
  }

}
