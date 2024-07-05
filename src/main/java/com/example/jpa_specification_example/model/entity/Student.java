package com.example.jpa_specification_example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student",
    indexes = {
        @Index(name = "idx_group_id", columnList = "group_id"),
        @Index(name = "idx_created_at", columnList = "created_at"),
        @Index(name = "idx_rate", columnList = "rate"),
        @Index(name = "idx_all_1", columnList = "group_id, rate"),
        @Index(name = "idx_all_2", columnList = "group_id, created_at"),
        @Index(name = "idx_all_3", columnList = "rate, created_at")
    }
)
public class Student extends BaseEntity{
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(nullable = false)
    private Integer rate;

    /**
     * for specifications
     */
    public static String _createdAt="createdAt";
    public static String _fullName="fullName";
    public static String _groupId="groupId";
    public static String _rate="rate";
    
}
