package org.vdoloka.dto.mapper;

import org.junit.jupiter.api.Test;
import org.vdoloka.dto.ResourceDTO;
import org.vdoloka.entity.Resource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceMapperTest {

    @Test
    void shouldMapToDto() {
        Resource resource = new Resource();
        resource.setId(1);
        resource.setName("Resource 1");
        resource.setSubcategorieId(1);

        ResourceDTO dto = ResourceMapper.INSTANCE.toDto(resource);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1);
        assertThat(dto.getName()).isEqualTo("Resource 1");
    }

    @Test
    void shouldMapToDtoList() {
        Resource resource1 = new Resource();
        resource1.setId(1);
        resource1.setName("Resource 1");
        resource1.setSubcategorieId(1);

        Resource resource2 = new Resource();
        resource2.setId(2);
        resource2.setName("Resource 2");
        resource1.setSubcategorieId(1);
        List<Resource> resources = List.of(resource1, resource2);

        List<ResourceDTO> dtos = ResourceMapper.INSTANCE.toDtoList(resources);

        assertThat(dtos)
                .isNotNull()
                .hasSize(2);

        assertThat(dtos.get(0))
                .extracting(ResourceDTO::getId, ResourceDTO::getName)
                .containsExactly(1, "Resource 1");

        assertThat(dtos.get(1))
                .extracting(ResourceDTO::getId, ResourceDTO::getName)
                .containsExactly(2, "Resource 2");
    }
}