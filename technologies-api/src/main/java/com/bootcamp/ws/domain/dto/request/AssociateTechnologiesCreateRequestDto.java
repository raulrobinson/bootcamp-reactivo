package com.bootcamp.ws.domain.dto.request;

import java.util.List;

public class AssociateTechnologiesCreateRequestDto {

    private Long capabilityId;
    private List<Long> technologiesIds;

    public Long getCapabilityId() {
        return capabilityId;
    }

    public List<Long> getTechnologiesIds() {
        return technologiesIds;
    }

    // Static inner Builder class
    public static class Builder {

        private Long capabilityId;
        private List<Long> technologiesIds;

        public Builder capabilityId(Long capabilityId) {
            this.capabilityId = capabilityId;
            return this;
        }

        public Builder technologiesIds(List<Long> technologiesIds) {
            this.technologiesIds = technologiesIds;
            return this;
        }

        public AssociateTechnologiesCreateRequestDto build() {
            AssociateTechnologiesCreateRequestDto requestDto = new AssociateTechnologiesCreateRequestDto();
            requestDto.capabilityId = this.capabilityId;
            requestDto.technologiesIds = this.technologiesIds;
            return requestDto;
        }
    }
}
