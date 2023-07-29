package kg.kstu.sweetshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grafik {

    private Long id;

    private Double sum;

    private Double part;

    private Double percent;

    private Double fine;

    private Date date;

    private Boolean status;

    private Long creditId;

    private String credit;
}
