package com.bootcamp.ws.domain.model;

public class TechnologyCapability {
    private Long technologyId;
    private Long capabilityId;

    public TechnologyCapability() {
    }

    public TechnologyCapability(Long technologyId, Long capabilityId) {
        this.technologyId = technologyId;
        this.capabilityId = capabilityId;
    }

    public Long getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(Long technologyId) {
        this.technologyId = technologyId;
    }

    public Long getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(Long capabilityId) {
        this.capabilityId = capabilityId;
    }

    public static class Builder {
        private Long technologyId;
        private Long capabilityId;

        public Builder technologyId(Long technologyId) {
            this.technologyId = technologyId;
            return this;
        }

        public Builder capabilityId(Long capabilityId) {
            this.capabilityId = capabilityId;
            return this;
        }

        public TechnologyCapability build() {
            return new TechnologyCapability(technologyId, capabilityId);
        }
    }
}
