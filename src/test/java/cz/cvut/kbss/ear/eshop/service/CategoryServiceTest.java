package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.CategoryDao;
import cz.cvut.kbss.ear.eshop.model.Category;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest() {
        // Arrange
        int expectedResult = 2;
        when(categoryDao.findAll()).thenReturn(List.of(new Category(), new Category()));

        // Act
        List<Category> result = categoryService.findAll();

        // Assert
        Assertions.assertEquals(expectedResult, result.size());
        verify(categoryDao).findAll();
    }

    @Test
    public void findTest() {
        // Arrange
        int id = 1;
        Category category = new Category();
        when(categoryDao.find(id)).thenReturn(category);

        // Act
        Category result = categoryService.find(id);

        // Assert
        Assertions.assertEquals(category, result);
        verify(categoryDao).find(id);
    }

    @Test
    public void findByNameTest() {
        // Arrange
        String name = "TestCategory";
        int expectedResult = 2;
        when(categoryDao.findByName(name)).thenReturn(List.of(new Category(), new Category()));

        // Act
        List<Category> result = categoryService.findByName(name);

        // Assert
        Assertions.assertEquals(expectedResult, result.size());
        verify(categoryDao).findByName(name);
    }

    @Test
    public void persistTest() {
        // Arrange
        Category category = new Category();

        // Act
        categoryService.persist(category);

        // Assert
        verify(categoryDao).persist(category);
    }

    @Test
    public void updateTest() {
        // Arrange
        Category category = new Category();

        // Act
        categoryService.update(category);

        // Assert
        verify(categoryDao).update(category);
    }

    @Test
    public void removeTest() {
        // Arrange
        Category category = new Category();

        // Act
        categoryService.remove(category);

        // Assert
        verify(categoryDao).remove(category);
    }
}
