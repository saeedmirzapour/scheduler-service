package io.handyprojects.schedulerservice.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.handyprojects.schedulerservice.config.serializer.JobSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "job", schema = "scheduler")
@JsonSerialize(using = JobSerializer.class)
public class Job extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String curlCommand;
    private int order;
    private Plan plan;
    private boolean active;

    protected Job() {
    }

    public Job(String curlCommand, int order, Plan plan, boolean active) {
        this.curlCommand = curlCommand;
        this.order = order;
        this.plan = plan;
        this.active = active;
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

    @NotEmpty
    @Column(name = "curl_command", nullable = false)
    public String getCurlCommand() {
        return curlCommand;
    }

    public void setCurlCommand(String curlCommand) {
        this.curlCommand = curlCommand;
    }

    @Column(name = "job_order", nullable = false)
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Column(nullable = false)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", curlCommand='" + curlCommand + '\'' +
                ", order=" + order +
                ", active=" + active +
                '}';
    }
}
