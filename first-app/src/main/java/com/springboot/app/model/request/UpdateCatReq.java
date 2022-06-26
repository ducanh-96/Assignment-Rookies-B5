package com.springboot.app.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCatReq {
	@NotNull(message = "cat name is required")
    @NotEmpty(message = "cat name is required")
    @ApiModelProperty(
            example="đồng hồ",
            notes="cat name cannot be empty",
            required=true
    )
    @JsonProperty("cat_name")
    private String name;
	
	@NotNull(message = "cat slug is required")
    @NotEmpty(message = "cat slug is required")
    @ApiModelProperty(
            example="dong-ho",
            notes="cat slug cannot be empty",
            required=true
    )
    @JsonProperty("cat_slug")
    private String slug;
}
