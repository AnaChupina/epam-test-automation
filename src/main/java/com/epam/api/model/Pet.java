package com.epam.api.model;

import java.util.ArrayList;
import java.util.List;

public class Pet {
    private final Integer id;
    private final CategoryDTO category;
    private final String name;
    private final List<String> photoUrls;
    private final List<TagDto> tags ;
    private final String status;

    public Pet(Integer id, CategoryDTO category, String name, List<String> photoUrls, List<TagDto> tags, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }
    public Integer getId() {
        return id;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

}
