package com.example.service;

import com.example.model.Permission;
import com.example.repository.PermissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public Permission findPermissionByName(String name) {
		return permissionRepository.findPermissionByName(name);
	}
}
