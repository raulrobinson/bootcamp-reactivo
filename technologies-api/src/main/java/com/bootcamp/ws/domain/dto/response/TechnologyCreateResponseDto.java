package com.bootcamp.ws.domain.dto.response;

public class TechnologyCreateResponseDto {
    private Long id;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public TechnologyCreateResponseDto build() {
            TechnologyCreateResponseDto responseDto = new TechnologyCreateResponseDto();
            responseDto.id = this.id;
            responseDto.name = this.name;
            responseDto.description = this.description;
            return responseDto;
        }
    }
}
