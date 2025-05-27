package com.bootcamp.ws.domain.dto.response;

import java.util.List;

public class CapabilityWithTechnologiesResponseDto {

    private Long capabilityId;
    private List<TechnologyResponseDto> technologiesIds;

    public Long getCapabilityId() {
        return capabilityId;
    }

    public List<TechnologyResponseDto> getTechnologiesIds() {
        return technologiesIds;
    }

    // Static inner Builder class
    public static class Builder {

        private Long capabilityId;
        private List<TechnologyResponseDto> technologiesIds;

        public Builder capabilityId(Long capabilityId) {
            this.capabilityId = capabilityId;
            return this;
        }

        public Builder technologiesIds(List<TechnologyResponseDto> technologiesIds) {
            this.technologiesIds = technologiesIds;
            return this;
        }

        public CapabilityWithTechnologiesResponseDto build() {
            CapabilityWithTechnologiesResponseDto responseDto = new CapabilityWithTechnologiesResponseDto();
            responseDto.capabilityId = this.capabilityId;
            responseDto.technologiesIds = this.technologiesIds;
            return responseDto;
        }
    }
}
