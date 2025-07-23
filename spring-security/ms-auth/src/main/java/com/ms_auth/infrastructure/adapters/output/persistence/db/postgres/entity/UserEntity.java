package com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "users")
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @Column("id")
    private Integer id;

    @Column("username")
    private String username;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("enabled")
    private Boolean isEnabled;

    @Column("created_at")
    private LocalDateTime createdAt;
}
