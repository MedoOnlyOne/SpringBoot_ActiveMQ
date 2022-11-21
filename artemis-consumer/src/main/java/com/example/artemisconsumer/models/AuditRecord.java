package com.example.artemisconsumer.models;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public class AuditRecord implements Serializable {
    @JacksonXmlProperty(isAttribute = true)
    APIDetails APIDetails;
    @JacksonXmlProperty(isAttribute = true)
    AuditVars AuditVars;

    public APIDetails getAPIDetails() {
        return APIDetails;
    }

    public void setAPIDetails(APIDetails APIDetails) {
        this.APIDetails = APIDetails;
    }

    @Override
    public String toString() {
        return "AuditRecord{" +
                "APIDetails=" + APIDetails +
                ", AuditVars=" + AuditVars +
                '}';
    }

    public AuditVars getAuditVars() {
        return AuditVars;
    }

    public void setAuditVars (AuditVars auditVars) {
        AuditVars = auditVars;
    }
}
