package org.vdoloka.service;

import org.vdoloka.entity.Category;
import org.vdoloka.entity.Location;
import org.vdoloka.entity.Resource;
import org.vdoloka.entity.SubCategory;

import java.util.List;

public interface NomenclatureService {
    List<Resource> getResources(int subcategoryId);

    List<Category> getCategories();

    List<Location> getLocations();

    List<SubCategory> getSubcategories(long categoryId);
}
