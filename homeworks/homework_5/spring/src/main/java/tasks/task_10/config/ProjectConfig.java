package tasks.task_10.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import tasks.task_10.annotations.Animal;
import tasks.task_10.annotations.Device;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(includeFilters = @Filter(type = FilterType.ANNOTATION,
				classes = Animal.class),
				excludeFilters = @Filter(type = FilterType.ANNOTATION,
				classes = Device.class),
				basePackages = "tasks.task_10.beans")
public class ProjectConfig { }
