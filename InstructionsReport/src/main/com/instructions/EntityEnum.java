package com.instructions;

/**
 * Enum to represent available Entities for which stocks can be bought or sold.
 * This allows us to reject instructions for invalid Entities.
 * 
 * @author Mark Johnstone
 *
 */
public enum EntityEnum {
	FOO("foo"), BAR("bar");

	private String entity;

	private EntityEnum(String entity) {
		this.entity = entity;
	}

	public String getEntity() {
		return entity;
	}

}
