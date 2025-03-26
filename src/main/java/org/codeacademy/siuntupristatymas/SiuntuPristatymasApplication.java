package org.codeacademy.siuntupristatymas;

import lombok.RequiredArgsConstructor;
import org.codeacademy.siuntupristatymas.repository.ParcelRepository;
import org.codeacademy.siuntupristatymas.service.CourierService;
import org.codeacademy.siuntupristatymas.service.ParcelService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class SiuntuPristatymasApplication {

    private final CourierService courierService;
    private final ParcelService parcelService;

    public static void main(String[] args) {
        SpringApplication.run(SiuntuPristatymasApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test(){
        courierService.addTestData();
        parcelService.addTestData();
        courierService.getAllCouriers().forEach(System.out::println);
        parcelService.getAllParcels().forEach(System.out::println);
    }

}
