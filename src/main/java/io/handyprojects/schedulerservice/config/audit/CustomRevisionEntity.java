package io.handyprojects.schedulerservice.config.audit;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;


@Entity
@Table(name = "REVISION_INFORMATION", schema = "scheduler")
@RevisionEntity(CustomRevisionListener.class)
public class CustomRevisionEntity {

    private Long id;
    private long timestamp;

    @Id
    @GeneratedValue
    @RevisionNumber
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public Date getRevisionDate() {
        return new Date( timestamp );
    }

    @RevisionTimestamp
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( !(o instanceof CustomRevisionEntity) ) {
            return false;
        }

        final CustomRevisionEntity that = (CustomRevisionEntity) o;
        return id == that.id
                && timestamp == that.timestamp;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "CustomRevisionEntity(id = " + id
                + ", revisionDate = " + DateFormat.getDateTimeInstance().format( getRevisionDate() ) + ")";
    }

}
