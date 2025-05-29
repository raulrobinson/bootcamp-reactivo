package com.bootcamp.ws.domain.model;

public class CapabilityBootcamp {
    private Long capabilityId;
    private Long bootcampId;
    private String createdAt;
    private String updatedAt;

    public CapabilityBootcamp() {
    }

    public CapabilityBootcamp(Long capabilityId, Long bootcampId, String createdAt, String updatedAt) {
        this.capabilityId = capabilityId;
        this.bootcampId = bootcampId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(Long capabilityId) {
        this.capabilityId = capabilityId;
    }

    public Long getBootcampId() {
        return bootcampId;
    }

    public void setBootcampId(Long bootcampId) {
        this.bootcampId = bootcampId;
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

    public static class Builder {
        private Long capabilityId;
        private Long bootcampId;
        private String createdAt;
        private String updatedAt;

        public Builder withCapabilityId(Long capabilityId) {
            this.capabilityId = capabilityId;
            return this;
        }

        public Builder withBootcampId(Long bootcampId) {
            this.bootcampId = bootcampId;
            return this;
        }

        public Builder withCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public CapabilityBootcamp build() {
            return new CapabilityBootcamp(capabilityId, bootcampId, createdAt, updatedAt);
        }
    }
}

