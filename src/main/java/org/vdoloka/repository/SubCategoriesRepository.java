package org.vdoloka.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.vdoloka.entity.SubCategory;

import java.util.List;

public interface SubCategoriesRepository extends CrudRepository<SubCategory, Long> {
    @Query(value = "SELECT subcategory_id, name, category_id FROM subcategories " +
            "where category_id = :categoryId", nativeQuery = true)
    List<SubCategory> findAllByCategoryId(@Param("categoryId") long categoryId);
}