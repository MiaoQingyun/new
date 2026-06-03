package org.example.Mapper;

import org.apache.ibatis.annotations.Param;
import org.example.Pojo.BuMen;

public interface BuMenMapper {
    BuMen getBumen(@Param("id")Integer id);
}
