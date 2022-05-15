package com.jamieelliott.reccit.repository;

import com.jamieelliott.reccit.model.Comment;
import com.jamieelliott.reccit.model.Post;
import com.jamieelliott.reccit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost (Post post);

    List<Comment> findAllByUser(User user);
}
