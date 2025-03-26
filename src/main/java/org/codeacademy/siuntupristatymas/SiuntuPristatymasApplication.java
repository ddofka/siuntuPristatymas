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
public class SiuntuPristatymasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiuntuPristatymasApplication.class, args);
    }

}
