package com.finalproject.homeservice.categoryTest;

import com.finalproject.homeservice.entity.Category;
import com.finalproject.homeservice.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void getAllCategoriesTest(){
        List<Category> categoryList = categoryRepository.findAll();
        categoryList.forEach(System.out::println);
    }

    @Test
    public void  getCategoryById(){
        Long id = Long.valueOf(1);
        Category temizlik = categoryRepository.findById(id).get();
        System.out.println(temizlik);
        assertThat(temizlik).isNotNull();
    }
}
