package com.irrigation.repository;


import com.irrigation.model.SlotsOfTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotsOfTimeRepo extends JpaRepository<SlotsOfTime,Long> {

    List<SlotsOfTime> findByPlotsOfLandId(Long plotOfLandId);

}
