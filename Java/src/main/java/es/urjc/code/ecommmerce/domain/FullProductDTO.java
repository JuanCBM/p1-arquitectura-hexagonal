package es.urjc.code.ecommmerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullProductDTO {

  Long id;
  String name;
  String description;
}
