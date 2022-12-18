package org.vdoloka.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.vdoloka.entity.ResourceEntity;

public interface ResourcesRepository extends CrudRepository<ResourceEntity, Long> {
    @Query(value = "SELECT id, name  FROM resources " +
            "where subcategorie_id = :subCategoryId", nativeQuery = true)
    Iterable<ResourceEntity> findAllBySubcategoryId(@Param("subCategoryId") long subCategoryId);
}