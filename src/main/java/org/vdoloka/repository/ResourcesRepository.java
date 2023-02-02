package org.vdoloka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vdoloka.entity.Resource;

import java.util.List;

public interface ResourcesRepository extends JpaRepository<Resource, Long> {
    @Query(value = "SELECT id, name,subcategorie_id FROM resources " +
            "where subcategorie_id = :subCategoryId", nativeQuery = true)
    List<Resource> findAllBySubcategoryId(@Param("subCategoryId") long subCategoryId);
}