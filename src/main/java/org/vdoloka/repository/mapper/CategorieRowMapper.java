package org.vdoloka.repository.mapper;

import org.vdoloka.entity.CategoryEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategorieRowMapper implements RowMapper<CategoryEntity> {
    @Override
    public CategoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoryEntity categoryEntity = new CategoryEntity(rs.getInt("categorie_id"),
                rs.getString("name"));
        return categoryEntity;
    }
}
