package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String bookName;

  @Column
  private String authorName;

  @Column
  private Integer inventory;

  @Column
  private Boolean isAvailable;

  @Column
  private Float price;

  @Column
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", referencedColumnName = "id")
  @JsonIgnore
  private Author author;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  @JsonIgnore
  private Category category;
}
