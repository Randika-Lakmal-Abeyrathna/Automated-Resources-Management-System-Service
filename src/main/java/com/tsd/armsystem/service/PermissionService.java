package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.PermissionException;
import com.tsd.armsystem.model.Permission;
import com.tsd.armsystem.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public List<Permission> getAllPermissions(){
        return  permissionRepository.findAll();
    }

    public Permission getPermissionByPermissionName(String permissionName){
        return permissionRepository.findByName(permissionName)
                .orElseThrow(()-> new PermissionException("Permission " +permissionName+" was not found"));
    }


    public Permission addPermission(Permission permission){
        permissionAddValidation(permission);
        return permissionRepository.save(permission);
    }

    private int getCountByPermission(String permissionName){
        return permissionRepository.countByName(permissionName);
    }

    private void permissionAddValidation(Permission permission) {
        String permissionName = permission.getName();

        if (permissionName.isEmpty() || permissionName.isBlank()){
            throw new PermissionException("permission cannot be empty or blank");
        }
        if (getCountByPermission(permissionName)>0){
            throw new PermissionException("Permission "+permissionName+" already exist !");
        }

    }
}
