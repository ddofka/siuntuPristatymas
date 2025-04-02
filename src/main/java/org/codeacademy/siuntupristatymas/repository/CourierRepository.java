package org.codeacademy.siuntupristatymas.repository;

import org.codeacademy.siuntupristatymas.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    List<Courier> findAllByName(String name);
}
