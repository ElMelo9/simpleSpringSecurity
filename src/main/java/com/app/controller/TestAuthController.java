package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//se necesita tener el @EnableMethodSecurity en la clase que manera la seguridad
@PreAuthorize("denyAll()")
public class TestAuthController {

//    @GetMapping("/hello")
//    @PreAuthorize("permitAll()")
//    public String hello(){
//        return "Hello word";
//    }
//
//    @GetMapping("/hello-security")
//    @PreAuthorize("hasAuthority('CREATE')")
//    public String helloSecurity(){
//        return "Hello word security";
//    }
//
//    @GetMapping("/hello-security2")
//    @PreAuthorize("hasAuthority('READ')")
//    public String helloSecurity2(){
//        return "Hello word security 2";
//    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public String helloGet(){
        return "Hello World - GET";
    }

    @PostMapping("/post")
    public String helloPost(){
        return "Hello World - POST";
    }

    @PutMapping("/put")
    public String helloPut(){
        return "Hello World - PUT";
    }

    @DeleteMapping("/delete")
    public String helloDelete(){
        return "Hello World - DELETE";
    }

    @PatchMapping("/patch")
    @PreAuthorize("hasAuthority('REFACTOR') or hasAuthority('READ')")
    public String helloPatch(){
        return "Hello World - PATCH";
    }
}
