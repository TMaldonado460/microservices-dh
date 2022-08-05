package com.dh.msproduct.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table("products")
public class Product {

    @Id
    private String idProduct;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotNull
    private Double price;

    @Column
    private LocalDateTime createdAt;

}
