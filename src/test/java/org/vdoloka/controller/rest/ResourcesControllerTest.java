package org.vdoloka.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.dto.ResourceDTO;
import org.vdoloka.entity.Resource;
import org.vdoloka.repository.ResourcesRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourcesControllerTest {
    @Mock
    private ResourcesRepository resourcesRepository;
    @InjectMocks
    private ResourcesController resourcesController;

    @Test
    void shouldGetSubcategories() {
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