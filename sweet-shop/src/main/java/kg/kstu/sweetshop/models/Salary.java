package kg.kstu.sweetshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary {

    private Long id;
    private Long staffId;
    private String staff;
    private Integer year;
    private String month;
    private Double purchaseAmount;
    private Double productionAmount;
    private Double saleAmount;
    private Double sumAmount;
    private Double oklad;
    private Double sumSalary;
    private Boolean isIssued;
}
