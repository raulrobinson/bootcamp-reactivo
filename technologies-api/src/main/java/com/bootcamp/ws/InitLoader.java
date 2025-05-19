package com.bootcamp.ws;

import com.bootcamp.ws.entity.TechnologyEntity;
import com.bootcamp.ws.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class InitLoader implements CommandLineRunner {

    private final TechnologyRepository repository;

    @Override
    public void run(String... args) {
        TechnologyEntity tech1 = new TechnologyEntity();
        tech1.setName("java");
        tech1.setDescription("Lenguaje de programación orientado a objetos.");

        TechnologyEntity tech2 = new TechnologyEntity();
        tech2.setName("python");
        tech2.setDescription("Lenguaje de programación interpretado y de alto nivel.");

        TechnologyEntity tech3 = new TechnologyEntity();
        tech3.setName("c#");
        tech3.setDescription("Lenguaje de programación desarrollado por Microsoft.");

        TechnologyEntity tech4 = new TechnologyEntity();
        tech4.setName("javascript");
        tech4.setDescription("Lenguaje de programación interpretado y orientado a objetos.");

        TechnologyEntity tech5 = new TechnologyEntity();
        tech5.setName("typescript");
        tech5.setDescription("Lenguaje de programación desarrollado por Microsoft que es un superconjunto de JavaScript.");

        TechnologyEntity tech6 = new TechnologyEntity();
        tech6.setName("go");
        tech6.setDescription("Lenguaje de programación desarrollado por Google que es compilado y concurrente.");

        TechnologyEntity tech7 = new TechnologyEntity();
        tech7.setName("kotlin");
        tech7.setDescription("Lenguaje de programación desarrollado por JetBrains que es interoperable con Java.");

        TechnologyEntity tech8 = new TechnologyEntity();
        tech8.setName("swift");
        tech8.setDescription("Lenguaje de programación desarrollado por Apple para iOS y macOS.");

        TechnologyEntity tech9 = new TechnologyEntity();
        tech9.setName("ruby");
        tech9.setDescription("Lenguaje de programación interpretado y orientado a objetos.");

        TechnologyEntity tech10 = new TechnologyEntity();
        tech10.setName("php");
        tech10.setDescription("Lenguaje de programación interpretado y de código abierto.");

        TechnologyEntity tech11 = new TechnologyEntity();
        tech11.setName("react");
        tech11.setDescription("Framework de desarrollo para el Front-End.");

        TechnologyEntity tech12 = new TechnologyEntity();
        tech12.setName("angular");
        tech12.setDescription("Framework de desarrollo para el Front-End.");

        TechnologyEntity tech13 = new TechnologyEntity();
        tech13.setName("vue");
        tech13.setDescription("Framework de desarrollo para el Front-End.");

        TechnologyEntity tech14 = new TechnologyEntity();
        tech14.setName("bash");
        tech14.setDescription("Lenguaje de programación de comandos para sistemas Unix.");

        TechnologyEntity tech15 = new TechnologyEntity();
        tech15.setName("powershell");
        tech15.setDescription("Lenguaje de programación de comandos para sistemas Windows.");
        
        repository.count()
                .filter(count -> count == 0)
                .flatMapMany(count -> repository.saveAll(Flux.just(
                        tech1, tech2, tech3, tech4, tech5,
                        tech6, tech7, tech8, tech9, tech10,
                        tech11, tech12, tech13, tech14, tech15
                )))
                .subscribe(tech -> System.out.println("Tecnología creada: " + tech.getName()));
    }
}
