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
@Table(name = "groups",
    indexes = {
        @Index(name = "idx_created_at", columnList = "created_at"),
        @Index(name = "idx_all", columnList = "created_at, name"),
        @Index(name = "idx_rate", columnList = "rate")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "unq_name", columnNames = {"name"})
    }
)
public class Group extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer rate;
}
