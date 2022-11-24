package com.george.orca.service;

import com.george.orca.domain.PromiseEntity;
import com.george.orca.repository.PromiseRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromiseServiceBean implements PromiseService {

    private final PromiseRepository promiseRepository;


    @Override
    public PromiseEntity get(Long id) {
        Optional<PromiseEntity> optionalPromiseEntity = promiseRepository.findById(id);
        return new TemplateUtil<PromiseEntity>().get(optionalPromiseEntity);
    }

    @Override
    public PromiseEntity edit(PromiseEntity entity) {
        return promiseRepository.save(entity);
    }

    @Override
    public List<PromiseEntity> list(Long id) {
        return promiseRepository.findAllByLoanId(id);
    }

    @Override
    public void delete(Long id) {
        PromiseEntity promiseEntity = get(id);
        promiseRepository.delete(promiseEntity);
    }

}


