package io.handyprojects.schedulerservice.repository.specification;

import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.domain.Plan_;
import io.handyprojects.schedulerservice.util.StringUtil;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class PlanSpecification implements Specification<Plan> {

    private Long id;
    private String name;
    private String cronString;
    private Boolean active;

    @Override
    public Predicate toPredicate(Root<Plan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate> predicates = new LinkedList<>();
        if (Objects.nonNull(id))
            predicates.add(cb.equal(root.get(Plan_.id), id));
        if (StringUtil.nonEmpty(name))
            predicates.add(cb.equal(root.get(Plan_.name), name));
        if (StringUtil.nonEmpty(cronString))
            predicates.add(cb.equal(root.get(Plan_.cronString), cronString));
        if (Objects.nonNull(active))
            predicates.add(cb.equal(root.get(Plan_.active), active));

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCronString() {
        return cronString;
    }

    public void setCronString(String cronString) {
        this.cronString = cronString;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
