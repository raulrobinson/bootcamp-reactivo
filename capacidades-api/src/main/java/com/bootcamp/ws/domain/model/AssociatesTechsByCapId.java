package com.bootcamp.ws.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssociatesTechsByCapId {
    private Long capabilityId;
    private List<Technology> technologiesIds;

//    public AssociatesTechsByCapId() {
//    }
//
//    public AssociatesTechsByCapId(Long capabilityId, List<Technology> technologiesIds) {
//        this.capabilityId = capabilityId;
//        this.technologiesIds = technologiesIds;
//    }
//
//    public Long getCapabilityId() {
//        return capabilityId;
//    }
//
//    public void setCapabilityId(Long capabilityId) {
//        this.capabilityId = capabilityId;
//    }
//
//    public List<Technology> getTechnologiesIds() {
//        return technologiesIds;
//    }
//
//    public void setTechnologiesIds(List<Technology> technologiesIds) {
//        this.technologiesIds = technologiesIds;
//    }
//
//    public static class Builder {
//        private Long capabilityId;
//        private List<Technology> technologiesIds;
//
//        public Builder withCapabilityId(Long capabilityId) {
//            this.capabilityId = capabilityId;
//            return this;
//        }
//
//        public Builder withTechnologiesIds(List<Technology> technologiesIds) {
//            this.technologiesIds = technologiesIds;
//            return this;
//        }
//
//        public AssociatesTechsByCapId build() {
//            return new AssociatesTechsByCapId(capabilityId, technologiesIds);
//        }
//    }
}
