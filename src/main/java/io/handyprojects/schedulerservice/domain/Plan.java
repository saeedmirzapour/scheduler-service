package io.handyprojects.schedulerservice.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "plan", schema = "scheduler")
public class Plan {

    private Long id;
    private String name;
    private String cronExpression;
    private boolean active;
    private List<Job> jobs = new ArrayList<>();

    protected Plan() {
    }

    public Plan(String name, String cronExpression, boolean active) {
        setName(name);
        setCronExpression(cronExpression);
        setActive(active);
    }

    public Plan(String name, String cronExpression, boolean active, List<Job> jobs) {
        setName(name);
        setCronExpression(cronExpression);
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

    @NotBlank
    @Column(name = "cron_expression", nullable = false)
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Column(name = "active", nullable = false)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany(fetch = FetchType.LAZY)
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
                ", cronExpression='" + cronExpression + '\'' +
                ", active=" + active +
                '}';
    }
}
