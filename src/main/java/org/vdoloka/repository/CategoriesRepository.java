package org.vdoloka.repository;

import org.springframework.data.repository.CrudRepository;
import org.vdoloka.entity.CategoryEntity;

public interface CategoriesRepository extends CrudRepository<CategoryEntity, Long> {
}

