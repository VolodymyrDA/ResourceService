package org.vdoloka.repository;

import org.springframework.data.repository.CrudRepository;
import org.vdoloka.entity.Category;

public interface CategoriesRepository extends CrudRepository<Category, Long> {
}

