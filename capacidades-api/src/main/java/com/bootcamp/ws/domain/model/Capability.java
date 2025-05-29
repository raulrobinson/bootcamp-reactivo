package com.bootcamp.ws.domain.model;

import java.util.List;

public class Capability {
    private Long id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    private List<TechnologyDto> technologies;

    public static class TechnologyDto {
        private Long id;
        private String name;
    }

    public Capability() {
    }

    public Capability(Long id, String name, String description, String createdAt, String updatedAt, List<TechnologyDto> technologies) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.technologies = technologies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<TechnologyDto> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<TechnologyDto> technologies) {
        this.technologies = technologies;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private String createdAt;
        private String updatedAt;
        private List<TechnologyDto> technologies;

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

        public Builder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder technologies(List<TechnologyDto> technologies) {
            this.technologies = technologies;
            return this;
        }

        public Capability build() {
            return new Capability(id, name, description, createdAt, updatedAt, technologies);
        }
    }
}

