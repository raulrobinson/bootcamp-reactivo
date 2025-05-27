package com.bootcamp.ws.domain.dto.request;


import java.util.List;

public class ExistsTechnologiesRequestDto {

    private List<Long> technologiesIds;

    // Constructor privado para forzar el uso del builder
    private ExistsTechnologiesRequestDto(Builder builder) {
        this.technologiesIds = builder.technologiesIds;
    }

    // Getter
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

        public ExistsTechnologiesRequestDto build() {
            return new ExistsTechnologiesRequestDto(this);
        }
    }

}
