package com.bootcamp.ws.domain.usecase;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.exception.BusinessException;
import com.bootcamp.ws.domain.exception.DuplicateResourceException;
import com.bootcamp.ws.domain.exception.TechnicalMessage;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.domain.spi.TechnologyServicePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TechnologyUseCase implements TechnologyServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public TechnologyUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

    @Override
    public Mono<Technology> createTechnology(Technology request) {
        return technologyAdapterPort.existsByName(request.getName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new DuplicateResourceException(
                                TechnicalMessage.ALREADY_EXISTS,
                                "TECH_DUPLICATE",
                                request.getName()
                        ));
                    }
                    return technologyAdapterPort.createTechnology(request);
                });
    }

    @Override
    public Flux<Technology> findTechnologiesByIdIn(List<Long> technologiesIds) {
        return technologyAdapterPort.findTechnologiesByIdIn(technologiesIds)
                .flatMap(technologies -> {
                    if (technologies == null) {
                        return Mono.error(new BusinessException(
                                "No technologies found for the provided IDs",
                                "TECHNOLOGIES_NOT_FOUND",
                                technologiesIds.toString()));
                    }
                    return Flux.just(technologies);
                });
    }

    @Override
    public Flux<Technology> findTechnologiesByIds(List<Long> technologiesIds) {
        return technologyAdapterPort.findTechnologiesByIds(technologiesIds)
                .flatMap(technologies -> {
                    if (technologies == null) {
                        return Mono.error(new BusinessException(
                                "No technologies found for the provided IDs",
                                "TECHNOLOGIES_NOT_FOUND",
                                technologiesIds.toString()));
                    }
                    return Flux.just(technologies);
                });
    }

    @Override
    public Mono<Map<Object, Object>> findAssociatesTechsByCapId(Long capabilityId) {
        return technologyAdapterPort.findAllByCapabilityId(capabilityId)
                .collectList()
                .flatMap(techCaps -> {
                    if (techCaps.isEmpty()) {
                        return Mono.error(new BusinessException(
                                "No technology capabilities found for the provided capability ID",
                                "TECH_CAPABILITIES_NOT_FOUND",
                                capabilityId.toString()));
                    }

                    List<Long> technologyIds = techCaps.stream()
                            .map(TechnologyCapability::getTechnologyId)
                            .distinct()
                            .toList();

                    return technologyAdapterPort.findTechnologiesByIds(technologyIds)
                            .collectList()
                            .map(technologies -> {
                                List<Map<String, Object>> techList = technologies.stream()
                                        .map(tech -> {
                                            Map<String, Object> map = new HashMap<>();
                                            map.put("id", tech.getId());
                                            map.put("name", tech.getName());
                                            return map;
                                        })
                                        .toList();

                                Map<Object, Object> result = new HashMap<>();
                                result.put("capability", capabilityId);
                                result.put("technologies", techList);
                                return result;
                            });
                });
    }


    @Override
    public Flux<TechnologyCapability> associateTechnologies(Long capabilityId, List<Long> technologiesIds) {
        return technologyAdapterPort.existsByCapabilityId(capabilityId)
                .flatMapMany(exists -> {
                    if (exists) {
                        return Flux.error(new BusinessException(
                                "Capability ID already exists",
                                "CAPABILITY_DUPLICATED",
                                capabilityId.toString()));
                    }
                    return technologyAdapterPort.associateTechnologies(capabilityId, technologiesIds);
                });
    }
}
