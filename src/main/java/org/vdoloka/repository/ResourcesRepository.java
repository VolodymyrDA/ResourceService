package org.vdoloka.repository;

import org.vdoloka.entity.CategoryEntity;
import org.vdoloka.entity.ResourceEntity;
import org.vdoloka.entity.SubCategoryEntity;

import java.util.List;

public interface ResourcesRepository {

    List<CategoryEntity> getCategories();

    List<SubCategoryEntity> getSubCategoriesByCategorie(int categorieId);

    List<ResourceEntity> getResourcesBySubcategory(int subCategoryId);
}