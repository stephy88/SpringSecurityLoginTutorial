package com.example.dto;

import com.example.model.Permission;

import java.util.List;

public class RoleDto {
	private String name;
	private List<PermissionDto> permissions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PermissionDto> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionDto> permissions) {
		this.permissions = permissions;
	}
}
