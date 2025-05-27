package com.bootcamp.ws.domain.dto.request;

public class TechnologyCreateRequestDto {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Static inner Builder class
    public static class Builder {
        private String name;
        private String description;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public TechnologyCreateRequestDto build() {
            TechnologyCreateRequestDto requestDto = new TechnologyCreateRequestDto();
            requestDto.name = this.name;
            requestDto.description = this.description;
            return requestDto;
        }
    }
}
