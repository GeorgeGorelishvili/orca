package com.george.orca.repository.custom;

import com.george.orca.domain.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {

    private final EntityManager em;

    @Override
    public List<EmployeeEntity> search(EmployeeEntity fields, Integer limit, Integer offset) {
        StringBuilder sb = new StringBuilder("SELECT t FROM EmployeeEntity t WHERE 1=1 ");

        if (Objects.nonNull(fields)) {
            if (StringUtils.isNotBlank(fields.getFirstName())) {
                sb.append("AND t.firstName like '%" + fields.getFirstName() + "%' ");
            }

            if (StringUtils.isNotBlank(fields.getLastName())) {
                sb.append("AND t.lastName like '%" + fields.getLastName() + "%' ");
            }
            if (StringUtils.isNotBlank(fields.getPersonalNumber())) {
                sb.append("AND t.personalNumber = '" + fields.getPersonalNumber() + "' ");
            }

        }

        TypedQuery<EmployeeEntity> query = em.createQuery(sb.toString(), EmployeeEntity.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<EmployeeEntity> employees = query.getResultList();
        return employees;
    }
}
