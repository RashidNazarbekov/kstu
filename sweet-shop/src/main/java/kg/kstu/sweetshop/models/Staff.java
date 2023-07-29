package kg.kstu.sweetshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    private Long id;

    private String fio;

    private Long positionId;

    private String position;

    private Double salary;

    private String address;

    private String phoneNumber;
}
