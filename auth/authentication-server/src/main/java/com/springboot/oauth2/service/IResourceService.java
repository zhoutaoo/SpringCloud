package com.springboot.oauth2.service;

import com.springboot.oauth2.entity.Resource;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface IResourceService {

    Set<Resource> findAll();
}
