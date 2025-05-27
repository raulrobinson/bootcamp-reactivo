package com.bootcamp.ws.domain.dto.response;

public class AssociateTechnologiesResponseDto {
    private Long capabilityId;
    private Long technologyId;

    public Long getCapabilityId() {
        return capabilityId;
    }

    public Long getTechnologyId() {
        return technologyId;
    }

    // Static inner Builder class
    public static class Builder {
        private Long capabilityId;
        private Long technologyId;

        public Builder capabilityId(Long capabilityId) {
            this.capabilityId = capabilityId;
            return this;
        }

        public Builder technologyId(Long technologyId) {
            this.technologyId = technologyId;
            return this;
        }

        public AssociateTechnologiesResponseDto build() {
            AssociateTechnologiesResponseDto responseDto = new AssociateTechnologiesResponseDto();
            responseDto.capabilityId = this.capabilityId;
            responseDto.technologyId = this.technologyId;
            return responseDto;
        }
    }
}
