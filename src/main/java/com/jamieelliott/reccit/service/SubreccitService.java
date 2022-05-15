package com.jamieelliott.reccit.service;

import com.jamieelliott.reccit.dto.SubreccitDto;
import com.jamieelliott.reccit.exceptions.ReccitException;
import com.jamieelliott.reccit.mapper.SubreccitMapper;
import com.jamieelliott.reccit.model.Subreccit;
import com.jamieelliott.reccit.repository.PostRepository;
import com.jamieelliott.reccit.repository.SubreccitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubreccitService {


    private final SubreccitRepository subreccitRepository;
    private final SubreccitMapper subreccitMapper;
    private final PostRepository postRepository;

    public SubreccitDto save(SubreccitDto subreccitDto){
        Subreccit save = subreccitRepository.save(subreccitMapper.mapDtoToSubreccit(subreccitDto));
        subreccitDto.setId(save.getId());
        return subreccitDto;
    }


    @Transactional(readOnly = true)
    public List<SubreccitDto> getAll() {
        return subreccitRepository.findAll()
                .stream()
                .map(subreccitMapper::mapSubreccitToDto)
                .collect(toList());
    }

    public SubreccitDto getSubreccit(Long id) {
        Subreccit subreccit = subreccitRepository.findById(id)
                .orElseThrow(() -> new ReccitException("No subreccit found with ID - " + id));
        return subreccitMapper.mapSubreccitToDto(subreccit);
    }


}
