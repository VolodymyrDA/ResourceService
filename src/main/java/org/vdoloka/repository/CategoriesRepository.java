package org.vdoloka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vdoloka.entity.Category;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
}

