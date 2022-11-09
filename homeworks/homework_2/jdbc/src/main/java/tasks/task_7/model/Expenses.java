package tasks.task_7.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Expenses {

    private int num;

    private Date payDate;

    private int receiver;

    private double value;
}
