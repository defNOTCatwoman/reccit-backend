package com.jamieelliott.reccit.mapper;


import com.jamieelliott.reccit.dto.SubreccitDto;
import com.jamieelliott.reccit.model.Post;
import com.jamieelliott.reccit.model.Subreccit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubreccitMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreccit.getPosts()))")
    SubreccitDto mapSubreccitToDto(Subreccit subreccit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreccit mapDtoToSubreccit(SubreccitDto subreccitDto);

}
