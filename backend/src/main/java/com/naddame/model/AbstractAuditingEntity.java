package com.naddame.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

/**
 * Base abstract class for entities which will hold definitions for created, last modified by and created,
 * last modified by date.
 */
public abstract class AbstractAuditingEntity {

	public static final String CREATED = "created";
	@CreatedBy
    @Field("creator")
    //@JsonIgnore
    private String creator;

    @CreatedDate
	@Field(CREATED)
    //@JsonIgnore
    private ZonedDateTime created = ZonedDateTime.now();

    @LastModifiedBy
    @Field("modifier")
    //@JsonIgnore
    private String modifier;

    @LastModifiedDate
    @Field("modified")
    //@JsonIgnore
    private ZonedDateTime modified = ZonedDateTime.now();

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public ZonedDateTime getCreated() {
		return created;
	}

	public void setCreated(ZonedDateTime created) {
		this.created = created;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public ZonedDateTime getModified() {
		return modified;
	}

	public void setModified(ZonedDateTime modified) {
		this.modified = modified;
	}

    

}
