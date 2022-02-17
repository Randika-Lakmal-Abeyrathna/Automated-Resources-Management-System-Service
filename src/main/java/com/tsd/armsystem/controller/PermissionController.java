package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.Permission;
import com.tsd.armsystem.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/permission")
@AllArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/all")
    public ResponseEntity<List<Permission>> getAllPermission(){
        List<Permission> permissionList = permissionService.getAllPermissions();
        return new ResponseEntity<>(permissionList, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<Permission> getPermissionByPermissionName(@PathVariable("name") String permissionName){
        Permission permission = permissionService.getPermissionByPermissionName(permissionName);
        return new ResponseEntity<>(permission,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Permission> addPermission(@RequestBody Permission permission){
        Permission addedPermission = permissionService.addPermission(permission);
        return new ResponseEntity<>(addedPermission,HttpStatus.CREATED);
    }

}
