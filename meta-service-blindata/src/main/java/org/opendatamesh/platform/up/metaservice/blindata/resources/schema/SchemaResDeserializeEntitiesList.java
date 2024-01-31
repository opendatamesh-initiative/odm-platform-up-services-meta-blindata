package org.opendatamesh.platform.up.metaservice.blindata.resources.schema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SchemaResDeserializeEntitiesList {


    private int id;
    private String name;
    private String version;
    private String mediaType;
    private SchemaContent content;

    @JsonCreator
    public SchemaResDeserializeEntitiesList(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("version") String version,
            @JsonProperty("mediaType") String mediaType,
            @JsonProperty("content") SchemaContent content) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.mediaType = mediaType;
        this.content = content;
    }

}


