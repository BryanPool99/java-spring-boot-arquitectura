package com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "refresh_tokens")
@Getter
@Setter
@Builder
public class RefreshTokenEntity {
    @Id
    @Column("id")
    private Integer id;

    @Column("user_id")
    private Integer userId;

    @Column("token")
    private String token;

    @Column("expire_date")
    private LocalDateTime expireDate;
}
