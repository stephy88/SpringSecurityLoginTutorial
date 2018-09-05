package com.example.repository;

import com.example.model.Permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("permissionRepository")
public interface PermissionRepository extends JpaRepository<Permission, String> {
	public Permission findPermissionByName(String name);
}
