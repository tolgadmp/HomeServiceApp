package com.finalproject.homeservice.payload;

import com.finalproject.homeservice.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {


    private Long id;
    private String name;
    private String description;

    public static CategoryDto mapEntityToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getCategoryName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }
}
