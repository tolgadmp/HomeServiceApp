package com.finalproject.homeservice.AttributeTest;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.repository.AttributeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class AttributeRepoTest {

    @Autowired
    private AttributeRepository attributeRepository;

    @Test
    public void getAttributesByChoiceIdTest(){



    }
}
