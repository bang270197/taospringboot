package com.springboot.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.springboot.dto.NewDTO;

public interface INewService {

    NewDTO save(NewDTO newDTO);

    void delete(long[] ids);

    //	NewDTO update(NewDTO newDTO);
    List<NewDTO> findAll(Pageable pageable);

    List<NewDTO> finAll();

    int totalItem();
}
