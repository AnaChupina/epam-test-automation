package com.epam.api.builders;

import com.epam.api.model.CategoryDTO;
import com.epam.api.model.Pet;
import com.epam.api.model.TagDto;
import com.epam.api.utils.FileHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PetBuilder {
    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class);
    private Integer id;
    private CategoryDTO category;
    private String name;
    private List<String> photoUrls = new ArrayList<>();
    private List<TagDto> tags = new ArrayList<>();
    private String status;

    public PetBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public PetBuilder setCategory(CategoryDTO category) {
        this.category = category;
        return this;
    }

    public PetBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public PetBuilder setTags(List<TagDto> tags) {
        this.tags = tags;
        return this;
    }

    public PetBuilder setStatus(String status) {
        this.status = status;
        return this;
    }
    public Pet build (){
        if (id == null) {
            throw new IllegalArgumentException("ID must be set");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category must be set");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name must be set");
        }
        if (photoUrls == null) {
            throw new IllegalArgumentException("Photo urls must be set");
        }
        if (tags == null) {
            throw new IllegalArgumentException("Tags must be set");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status must be set");
        }
        return new Pet(id, category, name, photoUrls, tags, status);
    }
}
