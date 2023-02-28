package org.vdoloka.controller.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vdoloka.dto.ResourceDTO;
import org.vdoloka.entity.Resource;
import org.vdoloka.repository.ResourcesRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ResourcesControllerTest {

    private AutoCloseable closeable;
    @Mock
    private ResourcesRepository resourcesRepository;

    @InjectMocks
    private ResourcesController resourcesController;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void testGetSubcategories() {
        int subCategoryId = 1;
        List<Resource> resources = List.of(new Resource(1, "Resource 1", 1),
                new Resource(2, "Resource 2", 1));
        when(resourcesRepository.findAllBySubcategoryId(subCategoryId)).thenReturn(resources);

        List<ResourceDTO> result = resourcesController.getResources(subCategoryId);

        assertThat(result)
                .isNotNull()
                .hasSize(resources.size())
                .extracting(ResourceDTO::getName)
                .containsExactly(resources.get(0).getName(), resources.get(1).getName());
    }
}