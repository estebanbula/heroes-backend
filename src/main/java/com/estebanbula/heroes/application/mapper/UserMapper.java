package com.estebanbula.heroes.application.mapper;

import com.estebanbula.heroes.domain.model.User;
import com.estebanbula.heroes.infraestructure.persistence.document.UserDocument;

public class UserMapper {

    private UserMapper() {}

    public static User toModel(UserDocument document) {
        return User.builder()
                .id(document.getId())
                .user(document.getUser())
                .email(document.getEmail())
                .build();
    }

    public static UserDocument toDocument(User model) {
        return UserDocument.builder()
                .id(model.getId())
                .user(model.getUser())
                .email(model.getEmail())
                .build();
    }
}
