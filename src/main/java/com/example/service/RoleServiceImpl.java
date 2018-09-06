package com.example.service;

import com.example.dto.PermissionDto;
import com.example.dto.RoleDto;
import com.example.model.Permission;
import com.example.model.Role;
import com.example.repository.PermissionRepository;
import com.example.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public Role saveRole(RoleDto roleDto) {
		Role role = new Role();
		String roleName = roleDto.getName();
		role.setRole(roleName);
		List<PermissionDto> permissionDtos = roleDto.getPermissions();

        Set<Permission> permissions = new HashSet<>();

		for(PermissionDto permissionDto : permissionDtos)
		{
			permissions.add(permissionRepository.findPermissionByName(permissionDto.getName()));
		}

		role.setPermissions(permissions);
		roleRepository.save(role);

		return role;
//		role.setRole("USER");
//		Permission permission = permissionRepository.findPermissionByName("ADD");
//		role.setPermissions(new HashSet<>(Arrays.asList("")));
	}
}
