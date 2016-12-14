/**
 * Copyright (C) 2004-2016, GoodData(R) Corporation. All rights reserved.
 * This source code is licensed under the BSD-style license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */
package com.gooddata.dataload;

import static com.gooddata.util.Validate.notNullState;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gooddata.util.GoodDataToStringBuilder;
import org.springframework.web.util.UriTemplate;

import java.util.Map;

/**
 * Output stage.
 * For each project there is always one output stage, which always exists.
 */
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("outputStage")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutputStage {

    public static final String URI = "/gdc/dataload/projects/{id}/outputStage";
    public static final UriTemplate TEMPLATE = new UriTemplate(URI);

    private static final String SELF_LINK = "self";
    private static final String OUTPUT_STAGE_DIFF = "outputStageDiff";
    private static final String DATALOAD_PROCESS = "dataloadProcess";

    private String schema;
    private String clientId;
    private String outputStagePrefix;
    private final Map<String,String> links;

    @JsonCreator
    private OutputStage(@JsonProperty("schema") final String schema,
                        @JsonProperty("clientId") final String clientId,
                        @JsonProperty("outputStagePrefix") final String outputStagePrefix,
                        @JsonProperty("links") final Map<String, String> links) {
        this.schema = schema;
        this.clientId = clientId;
        this.outputStagePrefix = outputStagePrefix;
        this.links = links;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    /**
     * get datawarehouse schema uri {@link com.gooddata.warehouse.WarehouseSchema}
     *
     * @return warehouse schema, can be null.
     */
    @JsonProperty("schema")
    public String getSchemaUri() {
        return schema;
    }

    @JsonProperty("schema")
    public void setSchemaUri(final String schemaUri) {
        this.schema = schemaUri;
    }

    /**
     * check if there is associated schema {@link com.gooddata.warehouse.WarehouseSchema} with this output stage
     *
     * @return true if there is associated schema, else false
     */
    public boolean hasSchemaUri() {
        return schema != null;
    }

    /**
     * get client ID
     *
     * @return client ID, can be null.
     */
    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    /**
     * check if there is associated client id with this output stage
     *
     * @return true if there is associated client id, else false
     */
    public boolean hasClientId() {
        return clientId != null;
    }

    /**
     * get output stage prefix
     *
     * @return output stage prefix, can be null.
     */
    public String getOutputStagePrefix() {
        return outputStagePrefix;
    }

    public void setOutputStagePrefix(final String outputStagePrefix) {
        this.outputStagePrefix = outputStagePrefix;
    }

    /**
     * check if there is associated output stage prefix with this output stage
     *
     * @return true if there is associated output stage prefix, else false
     */
    public boolean hasOutputStagePrefix() {
        return outputStagePrefix != null;
    }

    @JsonIgnore
    public String getUri() {
        return notNullState(links, "links").get(SELF_LINK);
    }

    @JsonIgnore
    public String getOutputStageDiffUri() {
        return notNullState(links, "links").get(OUTPUT_STAGE_DIFF);
    }

    @JsonIgnore
    public String getDataloadProcessUri() {
        return notNullState(links, "links").get(DATALOAD_PROCESS);
    }

    @Override
    public String toString() {
        return GoodDataToStringBuilder.defaultToString(this);
    }
}
