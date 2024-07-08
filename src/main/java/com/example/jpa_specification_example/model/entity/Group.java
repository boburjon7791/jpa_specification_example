package com.example.jpa_specification_example.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    /**
     * for specifications
     */
    public static String _name="name";

    public static String _id="id";

    public static String _group="group";

    public static String _id="id";

    public static String _group="group";
}
