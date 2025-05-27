package com.bootcamp.ws.domain.dto.request;

import java.util.List;

public class TechnologiesByIdsRequestDto {
    private List<Long> technologiesIds;

    public List<Long> getTechnologiesIds() {
        return technologiesIds;
    }

    // Static inner Builder class
    public static class Builder {
        private List<Long> technologiesIds;

        public Builder technologiesIds(List<Long> technologiesIds) {
            this.technologiesIds = technologiesIds;
            return this;
        }

        public TechnologiesByIdsRequestDto build() {
            TechnologiesByIdsRequestDto requestDto = new TechnologiesByIdsRequestDto();
            requestDto.technologiesIds = this.technologiesIds;
            return requestDto;
        }
    }
}
