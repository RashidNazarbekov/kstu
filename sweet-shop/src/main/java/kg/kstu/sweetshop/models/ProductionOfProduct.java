package kg.kstu.sweetshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionOfProduct {

    private Long id;

    private Long productId;

    private String product;

    private Double amount;

    private Date date;

    private Long staffId;

    private String staff;
}
