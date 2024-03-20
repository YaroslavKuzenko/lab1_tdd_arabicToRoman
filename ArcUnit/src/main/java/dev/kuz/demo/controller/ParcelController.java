package dev.kuz.demo.controller;
/*
  @author   yaroslavkuzenko
  @project   demo
  @class  ParcelController
  @version  1.0.0 
  @since 15.02.2024 - 17.32
*/

import lombok.RequiredArgsConstructor;
import dev.kuz.demo.model.Parcel;
import org.springframework.web.bind.annotation.*;
import dev.kuz.demo.service.ParcelService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parcels")
@RequiredArgsConstructor
public class ParcelController {
    private final ParcelService service;

    @GetMapping("")
    public List<Parcel> fetchAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Parcel fetchById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    public Parcel insert(@RequestBody Parcel parcel){
        return service.create(parcel);
    }

    @PutMapping()
    public Parcel update(@RequestBody Parcel parcel){
        return service.update(parcel);
    }

    @DeleteMapping({"/{id}"})
    public String deleteById(@PathVariable String id){
        return service.delete(id);
    }

}
