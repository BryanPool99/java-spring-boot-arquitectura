package com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "users_roles")
@Getter
@Setter
@Builder
public class UserRoleEntity {
    @Column("user_id")
    private Integer userId;

    @Column("role_id")
    private Integer roleId;
}
