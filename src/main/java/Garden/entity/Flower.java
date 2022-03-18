package Garden.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
    @Table(name = "Flower6")
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class Flower{


        @Id
        private Integer flowId;

        @Column
        private String flowName;

        @Column
        private String flowColor;

        @Column
        private Integer height;

    @ManyToOne
    @JoinColumn(name = "zoneId")
    private Zones zones;

    }

