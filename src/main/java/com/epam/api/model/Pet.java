package com.epam.api.model;

import java.util.ArrayList;
import java.util.List;

public class Pet {
    private Integer id;
    private CategoryDTO category;
    private String name;
    private List<String> photoUrls = new ArrayList<>();
    private List<TagDto> tags = new ArrayList<>();
    private String status;

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
