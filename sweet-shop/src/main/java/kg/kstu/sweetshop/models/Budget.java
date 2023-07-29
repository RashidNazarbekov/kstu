package kg.kstu.sweetshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    private Long id;

    private Double sumOfBudget;

    private Double percent;

    private Double bonus;
}
