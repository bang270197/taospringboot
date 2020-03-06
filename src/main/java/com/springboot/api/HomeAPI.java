package com.springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.NewOutput.NewOutput;
import com.springboot.dto.NewDTO;
import com.springboot.service.INewService;

import java.util.List;

@CrossOrigin
@RestController
public class HomeAPI {

    @Autowired
    private INewService INewService;

    @GetMapping(value = "/new")
    public NewOutput showNew(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "limit", required = false) Integer limit) {
        NewOutput result = new NewOutput();
        if (page != null & limit != null) {
            result.setPage(page);
            Pageable pageable = new PageRequest(page - 1, limit);
            result.setListResult(INewService.findAll(pageable));
            result.setTotalPage((int) Math.ceil((double) (INewService.totalItem()) / limit));
        } else {
            result.setListResult(INewService.finAll());
        }
        return result;
    }


//	@GetMapping(value = "/neww")
//	public List<NewDTO> showNeww(){
//
//	}

    @PutMapping("/new/{id}")
    public NewDTO updateNewDTO(@RequestBody NewDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return INewService.save(model);
    }

    @PostMapping("/new")
    public NewDTO createNewDTO(@RequestBody NewDTO model) {
        return INewService.save(model);
    }

    @DeleteMapping("/new")
    public void editNewDTO(@RequestBody long[] ids) {
        INewService.delete(ids);
    }
}
