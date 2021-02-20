package es.urjc.code.ecommmerce.domain.model.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullShoppingCartDTO {

  Long id;
  boolean completed;
  String ownerName;
  List<FullProductDTO> products = new ArrayList<>();

}
