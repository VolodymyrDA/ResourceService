package org.vdoloka.repository.impl;

import org.vdoloka.entity.CategoryEntity;
import org.vdoloka.entity.ResourceEntity;
import org.vdoloka.entity.SubCategoryEntity;
import org.vdoloka.repository.ResourcesRepository;
import org.vdoloka.repository.mapper.CategorieRowMapper;
import org.vdoloka.repository.mapper.ResourceRowMapper;
import org.vdoloka.repository.mapper.SubCategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourcesRepositoryImpl implements ResourcesRepository {


    private final NamedParameterJdbcTemplate namedjdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ResourcesRepositoryImpl(NamedParameterJdbcTemplate namedjdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedjdbcTemplate = namedjdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<CategoryEntity> getCategories() {
        String sql = "SELECT categorie_id,name  FROM categories";
        return namedjdbcTemplate.query(sql, new CategorieRowMapper());
    }

    @Override
    public List<SubCategoryEntity> getSubCategoriesByCategorie(int categoryId) {
        String sql = "SELECT subcategory_id,name  FROM subcategories where category_id = " + categoryId;
        return namedjdbcTemplate.query(sql, new SubCategoryRowMapper());
    }

    @Override
    public List<ResourceEntity> getResourcesBySubcategory(int subCategoryId) {
        String sql = "SELECT id,name  FROM resources where subcategorie_id = " + subCategoryId;
        return namedjdbcTemplate.query(sql, new ResourceRowMapper());
    }

}