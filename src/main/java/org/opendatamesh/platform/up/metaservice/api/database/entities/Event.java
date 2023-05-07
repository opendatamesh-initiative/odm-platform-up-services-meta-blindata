package org.opendatamesh.platform.up.metaservice.api.database.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
@Embeddable
public class Event {
    @Column(name="EVENT_ID")
    Long id;

    @Column(name="EVENT_TYPE")
    private String type;

    @Column(name="EVENT_ENTITY_ID")
    private String entityId;

    @Column(name="EVENT_BEFORE_STATE")
    private String beforeState;

    @Column(name="EVENT_AFTER_STATE")
    private String afterState;
   
    @Column(name="EVENT_TIME")
    private Date time;
}