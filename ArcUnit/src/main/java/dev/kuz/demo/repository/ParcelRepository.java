package dev.kuz.demo.repository;
/*
  @author   yaroslavkuzenko
  @project   demo
  @class  ItemRepository
  @version  1.0.0 
  @since 15.02.2024 - 15.55
*/

import dev.kuz.demo.model.Parcel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepository extends MongoRepository<Parcel, String> {
}
