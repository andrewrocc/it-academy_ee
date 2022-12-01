package tasks.task_10.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tasks.task_10.annotations.Device;

@Getter
@Setter
@ToString
@Device
public class MobilePhone {

	private String name = "iphone";

	private String model = "x";
}
