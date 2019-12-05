package io.handyprojects.schedulerservice.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.handyprojects.schedulerservice.config.constraint.CronString;
import io.handyprojects.schedulerservice.config.serializer.PlanSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "plan", schema = "scheduler")
@JsonSerialize(using = PlanSerializer.class)
public class Plan extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String cronString;
    private boolean active;
    private List<Job> jobs = new ArrayList<>();

    protected Plan() {
    }

    public Plan(String name, String cronString, boolean active) {
        setName(name);
        setCronString(cronString);
        setActive(active);
    }

    public Plan(String name, String cronString, boolean active, List<Job> jobs) {
        setName(name);
        setCronString(cronString);
        setActive(active);
        setJobs(jobs);
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CronString
    @Column(name = "cron_string", nullable = false)
    public String getCronString() {
        return cronString;
    }

    public void setCronString(String cronString) {
        this.cronString = cronString;
    }

    @Column(name = "active", nullable = false)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany(mappedBy = "plan")
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return name.equals(plan.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cronExpression='" + cronString + '\'' +
                ", active=" + active +
                '}';
    }
}
