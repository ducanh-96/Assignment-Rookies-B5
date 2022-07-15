package com.springboot.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.app.entity.Categories;
import com.springboot.app.exception.NotFoundException;
import com.springboot.app.model.dto.CatDto;
import com.springboot.app.model.mapper.CatMapper;
import com.springboot.app.model.request.CreateCatReq;
import com.springboot.app.model.request.UpdateCatReq;

@Component
public class CategoriesServiceImpl implements CategoriesService{
	private static ArrayList<Categories> categories = new ArrayList<Categories>();
	
	static {
		categories.add(new Categories(1, "trang sức cưới", "trang-suc-cuoi"));
		categories.add(new Categories(2, "trang sức vàng", "trang-suc-vang"));
		categories.add(new Categories(3, "trang sức bạc", "trang-suc-bac"));
    }
	
	@Override
    public List<CatDto> getListCat() {
        ArrayList<CatDto> result = new ArrayList<CatDto>();

        // Convert users -> result
        for (Categories cat : categories) {
            result.add(CatMapper.toCatDto(cat));
        }

        return result;
    }
	
	@Override
    public CatDto getCatById(int id) {
        for (Categories cat : categories) {
            if (cat.getId() == id) {
                return CatMapper.toCatDto(cat);
            }
        }

        throw new NotFoundException("No category found");
    }
	
	@Override
    public CatDto createCat(CreateCatReq req) {
        
        // Convert CreateUserReq -> User
		Categories cat = CatMapper.toCat(req);
		cat.setId(categories.size()+1);

        // Insert user
		categories.add(cat);

        return CatMapper.toCatDto(cat);
    }
	
	@Override
    public CatDto updateCat(UpdateCatReq req, int id) {
        for (Categories cat : categories) {
            if (cat.getId() == id) {
            	cat.setName(req.getName());
            	cat.setSlug(req.getSlug());
                return CatMapper.toCatDto(cat);
            }
        }
        throw new NotFoundException("No category found");
    }

    @Override
    public boolean deleteCat(int id) {
        for (Categories cat : categories) {
            if (cat.getId() == id) {
            	categories.remove(cat);
                return true;
            }
        }
        throw new NotFoundException("No category found");
    }

}
