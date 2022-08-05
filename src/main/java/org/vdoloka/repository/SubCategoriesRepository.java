package org.vdoloka.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.vdoloka.entity.SubCategoryEntity;

public interface SubCategoriesRepository extends CrudRepository<SubCategoryEntity, Long> {
    @Query(value = "SELECT subcategory_id,name  FROM subcategories " +
            "where category_id = :category_id", nativeQuery = true)
    Iterable<SubCategoryEntity> findAllByCategoryId(@Param("category_id") long category_id);
}

