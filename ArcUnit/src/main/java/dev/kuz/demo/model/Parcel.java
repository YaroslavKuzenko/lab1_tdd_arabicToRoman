package dev.kuz.demo.model;
/*
  @author   yaroslavkuzenko
  @project   demo
  @class  Parcel
  @version  1.0.0 
  @since 15.02.2024 - 15.35
*/

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Parcel {
    @Id
    private String id;
    private String number;
    private String description;
    private float weight;
    private float volume;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return id.equals(parcel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
