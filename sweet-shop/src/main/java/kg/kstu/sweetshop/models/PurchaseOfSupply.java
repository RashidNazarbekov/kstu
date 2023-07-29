package kg.kstu.sweetshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOfSupply {

    private Long id;

    private Long supplyId;

    private String supply;

    private Double amount;

    private Double sum;

    private Date date;

    private Long staffId;

    private String staff;
}
