package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.District;
import com.tsd.armsystem.model.Zonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZonalRepository extends JpaRepository<Zonal,Integer> {

    Optional<Zonal> findByIdzonal(Integer id);

    List<Zonal> findByDistrict(District district);
}
