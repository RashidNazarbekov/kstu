package kg.kstu.sweetshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private Long id;

    private Long productId;

    private String product;

    private Long supplyId;

    private String supply;

    private Double amount;
}
