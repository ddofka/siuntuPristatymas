package org.codeacademy.siuntupristatymas.repository;

import org.codeacademy.siuntupristatymas.entity.Courier;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourierPageRepository extends PagingAndSortingRepository<Courier, Long> {
}
