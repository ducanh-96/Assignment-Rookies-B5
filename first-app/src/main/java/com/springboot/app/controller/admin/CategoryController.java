package com.springboot.app.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.entity.Categories;
import com.springboot.app.model.dto.CatDto;
import com.springboot.app.model.request.CreateCatReq;
import com.springboot.app.model.request.UpdateCatReq;
import com.springboot.app.service.CategoriesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//http://localhost:8080/api/category
@RestController
@RequestMapping("/api/category")
public class CategoryController{

	@Autowired
	private CategoriesService categoriesService;
	
	//List Cat
	@ApiOperation(value = "Get list category", response = CatDto.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code=500,message = "")
    })
	@GetMapping("")
    public ResponseEntity<?> getListUser() {
        List<CatDto> result = categoriesService.getListCat();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

	//GET by id
    @ApiOperation(value = "Get category by id", response = CatDto.class)
    @ApiResponses({
            @ApiResponse(code=404,message = "No category found"),
            @ApiResponse(code=500,message = "")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getCatById(@PathVariable int id) {
    	CatDto result = categoriesService.getCatById(id);
        return ResponseEntity.ok(result);
    }

    //POST
    @ApiOperation(value = "Create category", response = CatDto.class)
    @ApiResponses({
            @ApiResponse(code=400,message = "Category name already exists in the system"),
            @ApiResponse(code=500,message = "")
    })
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateCatReq req) {
    	CatDto result = categoriesService.createCat(req);
        return ResponseEntity.ok(result);
    }

    //PUT
    @ApiOperation(value = "Update category", response = CatDto.class)
    @ApiResponses({
            @ApiResponse(code=404,message = "No category found"),
            @ApiResponse(code=500,message = "")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCat(@Valid @RequestBody UpdateCatReq req, @PathVariable int id) {
    	CatDto result = categoriesService.updateCat(req, id);
        return ResponseEntity.ok(result);
    }

    //DELETE
    @ApiOperation(value = "Delete category by id", response = String.class)
    @ApiResponses({
            @ApiResponse(code=404,message = "No category found"),
            @ApiResponse(code=500,message = "")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
    	categoriesService.deleteCat(id);
        return ResponseEntity.ok("Delete success");
    }
}
