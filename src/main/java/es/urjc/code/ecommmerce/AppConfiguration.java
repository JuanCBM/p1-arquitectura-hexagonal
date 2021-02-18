package es.urjc.code.ecommmerce;

import es.urjc.code.ecommmerce.domain.ProductUseCase;
import es.urjc.code.ecommmerce.domain.ProductUseCaseImpl;
import es.urjc.code.ecommmerce.infrastructure.ProductRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean
  public ProductUseCase productUseCase(ProductRepositoryAdapter productRepositoryAdapter) {
    return new ProductUseCaseImpl(productRepositoryAdapter);
  }

}
