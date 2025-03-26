package org.codeacademy.siuntupristatymas.repository;

import org.codeacademy.siuntupristatymas.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
}
