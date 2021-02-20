package es.urjc.code.ecommmerce;

import es.urjc.code.ecommmerce.domain.usecase.ProductUseCase;
import es.urjc.code.ecommmerce.domain.usecase.ProductUseCaseImpl;
import es.urjc.code.ecommmerce.domain.usecase.ShoppingCartUseCase;
import es.urjc.code.ecommmerce.domain.usecase.ShoppingCartUseCaseImpl;
import es.urjc.code.ecommmerce.infrastructure.ProductRepositoryAdapter;
import es.urjc.code.ecommmerce.infrastructure.ShoppingCartRespositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean
  public ProductUseCase productUseCase(ProductRepositoryAdapter productRepositoryAdapter) {
    return new ProductUseCaseImpl(productRepositoryAdapter);
  }

  @Bean
  public ShoppingCartUseCase shoppingCartUseCase(
      ShoppingCartRespositoryAdapter shoppingCartRespositoryAdapter) {
    return new ShoppingCartUseCaseImpl(shoppingCartRespositoryAdapter);
  }

}
