package org.vdoloka.repository.row_mapper;

import org.vdoloka.dto.HubOrderDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HubOrderRowMapper implements RowMapper<HubOrderDTO> {
    @Override
    public HubOrderDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        HubOrderDTO hubOrderDTO = new HubOrderDTO(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("phone"),
                rs.getInt("resource_id"),
                rs.getInt("o.quantity"),
                rs.getInt("h.quantity")
        );
        return hubOrderDTO;
    }
}
