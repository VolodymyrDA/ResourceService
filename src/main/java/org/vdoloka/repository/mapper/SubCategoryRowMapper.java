package org.vdoloka.repository.mapper;

import org.vdoloka.entity.SubCategoryEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubCategoryRowMapper implements RowMapper<SubCategoryEntity> {
    @Override
    public SubCategoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity(rs.getInt("subcategory_id"),
                rs.getString("name"));
        return subCategoryEntity;
    }
}