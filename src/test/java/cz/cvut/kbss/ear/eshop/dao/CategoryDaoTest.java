package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import cz.cvut.kbss.ear.eshop.model.Category;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.*;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryDaoTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CategoryDao categoryDao;

    @BeforeEach
    void setUp() {
        categoryDao.setEntityManager(entityManager);
    }

    @Test
    void findByName() {

        String categoryName = "Electronics";
        TypedQuery<Category> query = mock(TypedQuery.class);
        List<Category> expectedCategories = Collections.singletonList(new Category());

        when(entityManager.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)).thenReturn(query);
        when(query.setParameter("name", categoryName)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedCategories);

        List<Category> result = categoryDao.findByName(categoryName);

        Assertions.assertEquals(expectedCategories, result);
    }

    @Test
    void findByNameWithRuntimeException() {

        String categoryName = "Electronics";
        when(entityManager.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class))
                .thenThrow(new RuntimeException("Simulating a runtime exception"));

        Assertions.assertThrows(PersistenceException.class, () -> categoryDao.findByName(categoryName));
    }
}
