package org.codeacademy.siuntupristatymas.repository;

import org.codeacademy.siuntupristatymas.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
}
