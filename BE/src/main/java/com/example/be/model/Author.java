package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String authorName;

  @JsonManagedReference
  @OneToMany(
          mappedBy = "author",
          cascade = CascadeType.ALL,
          fetch = FetchType.LAZY)
  private Set<Book> books;
}
