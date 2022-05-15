package com.jamieelliott.reccit.service;

import com.jamieelliott.reccit.dto.PostRequest;
import com.jamieelliott.reccit.dto.PostResponse;
import com.jamieelliott.reccit.exceptions.PostNotFoundException;
import com.jamieelliott.reccit.exceptions.SubreccitNotFoundException;
import com.jamieelliott.reccit.mapper.PostMapper;
import com.jamieelliott.reccit.model.Post;
import com.jamieelliott.reccit.model.Subreccit;
import com.jamieelliott.reccit.model.User;
import com.jamieelliott.reccit.repository.PostRepository;
import com.jamieelliott.reccit.repository.SubreccitRepository;
import com.jamieelliott.reccit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class PostService {

    private final SubreccitRepository subreccitRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public void save(PostRequest postRequest){
        Subreccit subreccit = subreccitRepository.findByName(postRequest.getSubreccitName())
                .orElseThrow(()-> new SubreccitNotFoundException(postRequest.getSubreccitName()));
        postRepository.save(postMapper.map(postRequest, subreccit, authService.getCurrentUser()));


    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreccit(Long subreccitId) {
        Subreccit subreccit = subreccitRepository.findById(subreccitId)
                .orElseThrow(() -> new SubreccitNotFoundException(subreccitId.toString()));
        List<Post> posts = postRepository.findAllBySubreccit(subreccit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
