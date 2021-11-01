package com.alexvanbeekum.LedController.repository;

import com.alexvanbeekum.LedController.entity.Boxes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoxesRepository extends CrudRepository<Boxes, Integer> {

    @Query("select b from Boxes b where b.set_id like ?1")
    List<Boxes> findAllSetId(Integer set_id);

    @Modifying
    @Query("update Boxes b set b.r = ?3, b.g = ?4, b.b= ?5 where b.set_id = ?1 and b.box_id = ?2")
    void updateBox(Integer set_id, Integer box_id, Integer r, Integer g, Integer b);
}
