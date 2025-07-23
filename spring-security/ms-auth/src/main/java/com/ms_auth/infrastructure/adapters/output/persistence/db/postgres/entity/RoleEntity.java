package com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "roles")
@Getter
@Setter
@Builder
public class RoleEntity {
    @Id
    @Column("id")
    private Integer roleId;

    @Column("name")
    private String name;
}
