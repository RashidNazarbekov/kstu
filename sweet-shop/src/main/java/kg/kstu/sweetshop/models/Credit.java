package kg.kstu.sweetshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {

    private Long id;

    private Double sum;

    private Integer years;

    private Date date;

    private Boolean status;
}
