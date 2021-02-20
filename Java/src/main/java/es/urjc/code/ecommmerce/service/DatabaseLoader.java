package es.urjc.code.ecommmerce.service;

import es.urjc.code.ecommmerce.infrastructure.model.ProductEntity;
import es.urjc.code.ecommmerce.infrastructure.model.ShoppingCartEntity;
import es.urjc.code.ecommmerce.infrastructure.model.ShoppingCartProductEntity;
import es.urjc.code.ecommmerce.infrastructure.repository.ProductJpaRepository;
import es.urjc.code.ecommmerce.infrastructure.repository.ShoppingCartJpaRepository;
import es.urjc.code.ecommmerce.infrastructure.repository.ShoppingCartProductJpaRepository;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DatabaseLoader implements CommandLineRunner {

  private final ShoppingCartJpaRepository shoppingCartJpaRepository;
  private final ProductJpaRepository productJpaRepository;
  private final ShoppingCartProductJpaRepository shoppingCartProductJpaRepository;

  public DatabaseLoader(final ShoppingCartJpaRepository shoppingCartJpaRepository,
      ProductJpaRepository productJpaRepository,
      ShoppingCartProductJpaRepository shoppingCartProductJpaRepository) {
    this.shoppingCartJpaRepository = shoppingCartJpaRepository;
    this.productJpaRepository = productJpaRepository;
    this.shoppingCartProductJpaRepository = shoppingCartProductJpaRepository;
  }

  @Override
  public void run(String... args) {
    ProductEntity productEntity = ProductEntity.builder().name("Monitor")
        .description("Monitor 27 pulgadas LG").build();
    ProductEntity productEntity2 = ProductEntity.builder().name("Teclado")
        .description("Teclado mini Lenovo").build();
    ProductEntity productEntity3 = ProductEntity.builder().name("Ratón")
        .description("Ratón ergonómico MSI").build();

    ShoppingCartEntity shoppingCartEntity = ShoppingCartEntity.builder().ownerName("Juan Carlos")
        .build();
    ShoppingCartEntity shoppingCartEntity2 = ShoppingCartEntity.builder().ownerName("Miguel Angel")
        .build();

    ShoppingCartProductEntity shoppingCart1ProductEntity1 = new ShoppingCartProductEntity(
        shoppingCartEntity, productEntity, 1);

    ShoppingCartProductEntity shoppingCart1ProductEntity2 = new ShoppingCartProductEntity(
        shoppingCartEntity, productEntity2, 3);

    ShoppingCartProductEntity shoppingCart2ProductEntity = new ShoppingCartProductEntity(
        shoppingCartEntity2, productEntity2, 5);

    this.productJpaRepository.saveAll(Arrays.asList(productEntity, productEntity2, productEntity3));
    this.shoppingCartJpaRepository.saveAll(Arrays.asList(shoppingCartEntity, shoppingCartEntity2));
    this.shoppingCartProductJpaRepository
        .saveAll(Arrays.asList(shoppingCart1ProductEntity1, shoppingCart1ProductEntity2,
            shoppingCart2ProductEntity));
  }

}