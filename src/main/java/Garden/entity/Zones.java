package Garden.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Zones6")

public class Zones {

    @Id
    private Integer zoneId;

    @Column(name = "zoneLow")
    private Integer zoneLow;

    @Column(name = "zoneHigh")
    private Integer zoneHigh;

    public Zones() {
    }

    public Zones(Integer zoneId,Integer zoneLow, Integer zoneHigh) {
        this.zoneId = zoneId;
        this.zoneLow = zoneLow;
        this.zoneHigh = zoneHigh;
    }

}

