package com.jamieelliott.reccit.repository;

import com.jamieelliott.reccit.model.Post;
import com.jamieelliott.reccit.model.User;
import com.jamieelliott.reccit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

}
