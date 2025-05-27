package com.bootcamp.ws.domain.dto.response;

public class TechnologyResponseDto {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private Long id;
        private String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public TechnologyResponseDto build() {
            TechnologyResponseDto responseDto = new TechnologyResponseDto();
            responseDto.id = this.id;
            responseDto.name = this.name;
            return responseDto;
        }
    }
}
