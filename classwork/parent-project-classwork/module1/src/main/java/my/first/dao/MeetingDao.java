package my.first.dao;

import my.first.model.Meeting;

import java.util.List;

public interface MeetingDao {

    void create(Meeting met);

    List<Meeting> findAll();

    void update(Meeting met);

    void delete(Meeting met);
}
