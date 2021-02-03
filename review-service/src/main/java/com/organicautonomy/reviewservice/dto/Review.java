package com.organicautonomy.reviewservice.dto;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    @DecimalMin(value = "0", message = "Value cannot be less than 0.")
    @DecimalMax(value = "5", message = "Value cannot be greater than 5.")
    @Column(precision = 7, scale = 2)
    private BigDecimal rating;
    @NotNull
    private String text;

    public Review() {
    }

    public Review(@NotNull Integer userId, @NotNull @DecimalMin("0") @DecimalMax("5") BigDecimal rating,
                  @NotNull String text) {
        this.userId = userId;
        this.rating = rating;
        this.text = text;
    }

    public Review(Integer id, @NotNull Integer userId, @NotNull @DecimalMin("0") @DecimalMax("5") BigDecimal rating,
                  @NotNull String text) {
        this.id = id;
        this.userId = userId;
        this.rating = rating;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) && Objects.equals(userId, review.userId)
                && Objects.equals(rating, review.rating) && Objects.equals(text, review.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, rating, text);
    }
}
