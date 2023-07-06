package com.epam.api.model;

import java.util.ArrayList;
import java.util.List;

public class Pet {
    private Integer id;
    private Category category;
    private String name;
    private List<String> photoUrls = new ArrayList<>();
    private List<TagDto> tags = new ArrayList<>();
    private String status;

    public Pet(PetBuilder builder) {
        this.id = builder.id;
        this.category = builder.category;
        this.name = builder.name;
        this.photoUrls = builder.photoUrls;
        this.tags = builder.tags;
        this.status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public Category getCategory() {
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


    public static class PetBuilder{
        private Integer id;
        private Category category;
        private String name;
        private List<String> photoUrls = new ArrayList<>();
        private List<TagDto> tags = new ArrayList<>();
        private String status;
        public PetBuilder(Integer id, Category category, List<String> photoUrls, List<TagDto> tags, String status) {
            this.id = id;
            this.category = category;
            this.photoUrls = photoUrls;
            this.tags = tags;
            this.status = status;
        }

        public PetBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public PetBuilder setCategory(Category category) {
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
            return new Pet(this);
        }
    }

}
