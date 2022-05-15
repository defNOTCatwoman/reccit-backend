package com.jamieelliott.reccit.repository;

import com.jamieelliott.reccit.model.Subreccit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubreccitRepository extends JpaRepository<Subreccit, Long> {

    Optional<Subreccit> findByName (String subreccitName);
}
