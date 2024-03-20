package dev.kuz.demo.service;
/*
  @author   yaroslavkuzenko
  @project   demo
  @class  ParcelService
  @version  1.0.0 
  @since 15.02.2024 - 16.06
*/

import dev.kuz.demo.repository.ParcelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import dev.kuz.demo.model.Parcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParcelService {

    private final ParcelRepository repository;
    private List<Parcel> parcels = List.of(
            new Parcel("1", "20 4508 6899 9786", "чохол для ноутбука", 0.7F, 1.2F),
            new Parcel("2", "20 4508 1112 2003", "продукти харчування", 12.2F, 7.3F),
            new Parcel("3", "20 4508 1489 8814", "шеврони", 0.2F, 0.5F),
            new Parcel("4", "20 4508 7050 1971", "картина", 2F, 4F)
    );

    @PostConstruct
    void init() {
        repository.deleteAll();
        repository.saveAll(parcels);
    }


    //CRUD
    @Cacheable("parcels")
    public List<Parcel> getAll(){
        log.info("--------- GET ALL ----------");
        return repository.findAll();
    }

    @Cacheable("parcels")
    public Parcel getById(String id){
        log.info("--------- GET BY ID " + id + " ----------");
        return repository.findById(id).orElse(null);
    }

    @CachePut(value = "parcels", key = "#parcel.id")
    public Parcel create(Parcel parcel){
        log.info("--------- CREATE NEW PARCEL ----------");
        return repository.save(parcel);
    }

    @CacheEvict("parcels")
    public String delete(String parcelId){
        log.info("--------- DELETE PARCEL ----------");
        repository.deleteById(parcelId);
        return "посилку з id " + parcelId + ", було капітально знищено";
    }

    public Parcel update(Parcel parcel) {
        Parcel existingParcel = repository.findById(parcel.getId()).get();
        existingParcel.setNumber(parcel.getNumber());
        existingParcel.setDescription(parcel.getDescription());
        existingParcel.setWeight(parcel.getWeight());
        existingParcel.setVolume(parcel.getVolume());

        return repository.save(existingParcel);
    }
}

