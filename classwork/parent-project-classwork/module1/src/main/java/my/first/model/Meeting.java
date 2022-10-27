package my.first.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "T_MEETING")
public class Meeting {

    @Id
    @Column(name = "F_MEETING_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meeting_seq")
    @SequenceGenerator(name = "meeting_seq", sequenceName = "t_meeting_seq")
    private long id;

    @Column(name = "F_MEETINGDATE")
    private Date meetingDate;

    @Column(name = "F_SUBJECT")
    private String subject;

    @ManyToMany(mappedBy = "meetings", fetch = FetchType.EAGER)
    private Set<Employee> employees;
}
