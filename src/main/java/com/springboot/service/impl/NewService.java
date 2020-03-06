package com.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.converter.NewConverter;
import com.springboot.dto.NewDTO;
import com.springboot.entity.CategoryEntity;
import com.springboot.entity.NewEntity;
import com.springboot.repository.CategoryRepository;
import com.springboot.repository.NewRepository;
import com.springboot.service.INewService;

@Service
public class NewService implements INewService {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewConverter newConverter;

    @Override
    public NewDTO save(NewDTO newDTO) {
        NewEntity newEntity = new NewEntity();
        if (newDTO.getId() != null) {
            // du kieu cu
            NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
            // du lieu moi(newDTO) do vao du lieu cu(oldNewEntity
            newEntity = newConverter.toEntity(newDTO, oldNewEntity);
        } else {
            newEntity = newConverter.toEntity(newDTO);
        }

        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
        newEntity.setCateory(categoryEntity);
        newEntity = newRepository.save(newEntity);
        return newConverter.toDTO(newEntity);
    }

    @Override
    public void delete(long[] ids) {
        for (long item : ids) {
            newRepository.delete(item);
        }
    }


    @Override
    public int totalItem() {
        return (int) newRepository.count();
    }

    @Override
    public List<NewDTO> findAll(Pageable pageable) {
        List<NewDTO> results = new ArrayList<>();
        List<NewEntity> entities = newRepository.findAll(pageable).getContent();
        for (NewEntity item : entities) {
            NewDTO newDTO = newConverter.toDTO(item);
            results.add(newDTO);
        }
        return results;
    }

    @Override
    public List<NewDTO> finAll() {
        List<NewDTO> results = new ArrayList<>();
        List<NewEntity> entities = newRepository.findAll();
        for (NewEntity item : entities) {
            NewDTO newDTO = newConverter.toDTO(item);
            results.add(newDTO);
        }
        return results;
    }


//	@Override
//	public NewDTO update(NewDTO newDTO) {
//		NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
//		NewEntity newEntity = newConverter.toEntity(newDTO, oldNewEntity);
//		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
//		newEntity.setCateory(categoryEntity);
//		newEntity = newRepository.save(newEntity);
//		return newConverter.toEntity(newEntity);
//	}

}
