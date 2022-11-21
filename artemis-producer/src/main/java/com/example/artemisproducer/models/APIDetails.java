package com.example.artemisproducer.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public class APIDetails implements Serializable {
     @JacksonXmlProperty(isAttribute = true)
     String APIName;
     @JacksonXmlProperty(isAttribute = true)
     String APIVersion;
     @JacksonXmlProperty(isAttribute = true)
     String APIType;
     @JacksonXmlProperty(isAttribute = true)
     String HTTPMethod;
     @JacksonXmlProperty(isAttribute = true)
     String Protocol;
     @JacksonXmlProperty(isAttribute = true)
     String APIRoot;
     @JacksonXmlProperty(isAttribute = true)
     String OperationID;
     @JacksonXmlProperty(isAttribute = true)
     String APIPath;
     @JacksonXmlProperty(isAttribute = true)
     String CatalogID;
     @JacksonXmlProperty(isAttribute = true)
     String CatalogName;
     @JacksonXmlProperty(isAttribute = true)
     String ClientOrgID;
     @JacksonXmlProperty(isAttribute = true)
     String ClientOrgName;
     @JacksonXmlProperty(isAttribute = true)
     String ClientAppID;
     @JacksonXmlProperty(isAttribute = true)
     String ClientAppName;

     public String getAPIName() {
          return APIName;
     }

     public void setAPIName(String APIName) {
          this.APIName = APIName;
     }

     public String getAPIVersion() {
          return APIVersion;
     }

     public void setAPIVersion(String APIVersion) {
          this.APIVersion = APIVersion;
     }

     public String getAPIType() {
          return APIType;
     }

     public void setAPIType(String APIType) {
          this.APIType = APIType;
     }

     public String getHTTPMethod() {
          return HTTPMethod;
     }

     public void setHTTPMethod(String HTTPMethod) {
          this.HTTPMethod = HTTPMethod;
     }

     public String getProtocol() {
          return Protocol;
     }

     public void setProtocol(String protocol) {
          Protocol = protocol;
     }

     public String getAPIRoot() {
          return APIRoot;
     }

     public void setAPIRoot(String APIRoot) {
          this.APIRoot = APIRoot;
     }

     public String getOperationID() {
          return OperationID;
     }

     public void setOperationID(String operationID) {
          OperationID = operationID;
     }

     public String getAPIPath() {
          return APIPath;
     }

     public void setAPIPath(String APIPath) {
          this.APIPath = APIPath;
     }

     public String getCatalogID() {
          return CatalogID;
     }

     public void setCatalogID(String catalogID) {
          CatalogID = catalogID;
     }

     public String getCatalogName() {
          return CatalogName;
     }

     public void setCatalogName(String catalogName) {
          CatalogName = catalogName;
     }

     public String getClientOrgID() {
          return ClientOrgID;
     }

     public void setClientOrgID(String clientOrgID) {
          ClientOrgID = clientOrgID;
     }

     public String getClientOrgName() {
          return ClientOrgName;
     }

     public void setClientOrgName(String clientOrgName) {
          ClientOrgName = clientOrgName;
     }

     public String getClientAppID() {
          return ClientAppID;
     }

     public void setClientAppID(String clientAppID) {
          ClientAppID = clientAppID;
     }

     public String getClientAppName() {
          return ClientAppName;
     }

     public void setClientAppName(String clientAppName) {
          ClientAppName = clientAppName;
     }

     @Override
     public String toString() {
          return "APIDetails{" +
                  "APIName='" + APIName + '\'' +
                  ", APIVersion='" + APIVersion + '\'' +
                  ", APIType='" + APIType + '\'' +
                  ", HTTPMethod='" + HTTPMethod + '\'' +
                  ", Protocol='" + Protocol + '\'' +
                  ", APIRoot='" + APIRoot + '\'' +
                  ", OperationID='" + OperationID + '\'' +
                  ", APIPath='" + APIPath + '\'' +
                  ", CatalogID='" + CatalogID + '\'' +
                  ", CatalogName='" + CatalogName + '\'' +
                  ", ClientOrgID='" + ClientOrgID + '\'' +
                  ", ClientOrgName='" + ClientOrgName + '\'' +
                  ", ClientAppID='" + ClientAppID + '\'' +
                  ", ClientAppName='" + ClientAppName + '\'' +
                  '}';
     }
}