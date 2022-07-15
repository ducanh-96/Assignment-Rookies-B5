package com.springboot.app.service;

import com.springboot.app.model.dto.CatDto;
import com.springboot.app.model.request.CreateCatReq;
import com.springboot.app.model.request.UpdateCatReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriesService {
	public List<CatDto> getListCat();

    public CatDto getCatById(int id);

    public CatDto createCat(CreateCatReq req);

    public CatDto updateCat(UpdateCatReq req, int id);

    public boolean deleteCat(int id);
}
