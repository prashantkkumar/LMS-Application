package org.example.batch.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long studentId;
    private Long instructorId;
    private Long courseId;

//    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
//    private List<Schedule> schedules;
//
//    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
//    private List<Attendance> attendances;
}
