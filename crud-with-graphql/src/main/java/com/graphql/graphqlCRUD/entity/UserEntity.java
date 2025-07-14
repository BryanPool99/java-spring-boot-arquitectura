package com.graphql.graphqlCRUD.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    private Integer id;
    @Column("name")
    private String name;
    @Column("email")
    private String email;
}
