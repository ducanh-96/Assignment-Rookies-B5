package com.springboot.app.model.mapper;

import com.springboot.app.entity.Categories;
import com.springboot.app.model.dto.CatDto;
import com.springboot.app.model.request.CreateCatReq;

public class CatMapper {

	public static CatDto toCatDto(Categories categories) {
		CatDto tmp = new CatDto();
		tmp.setId(categories.getId());
        tmp.setName(categories.getName());
        tmp.setSlug(categories.getSlug());
		
		return tmp;
	}
	public static Categories toCat(CreateCatReq req) {
		Categories categories = new Categories();
		categories.setName(req.getName());
		categories.setSlug(req.getSlug());

        return categories;
    }
}
