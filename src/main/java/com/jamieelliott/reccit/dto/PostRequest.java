package com.jamieelliott.reccit.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private Long postId;
    private String subreccitName;
    private String postName;
    private String url;
    private String description;
}
