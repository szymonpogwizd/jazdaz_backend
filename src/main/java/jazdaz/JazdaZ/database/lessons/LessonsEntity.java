package jazdaz.JazdaZ.database.lessons;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "lessons")
public class LessonsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
    private String type;

    @Column(nullable = false)
    @NotEmpty
    private int hours_spend;

    @Column
    private LocalDate start_date;

    @Column
    private LocalDate end_date;
}
