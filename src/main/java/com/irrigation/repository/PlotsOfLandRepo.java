package com.irrigation.repository;

import com.irrigation.model.PlotsOfLand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotsOfLandRepo extends JpaRepository<PlotsOfLand,Long> {


}
