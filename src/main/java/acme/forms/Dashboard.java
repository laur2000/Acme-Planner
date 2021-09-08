package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	protected static final long serialVersionUID = 1L;

	Integer totalNumberOfPublicTasks;
	Integer totalNumberOfPrivateTasks;
	Integer totalNumberOfFinishedTasks;
	Integer totalNumberOfNonFinishedTasks;
	String averageNumberOfTaskExecutionPeriods;
	String deviationNumberOfTaskExecutionPeriods;
	String minimumNumberOfTaskExecutionPeriods;
	String maximumNumberOfTaskExecutionPeriods;
	String averageNumberOfTaskWorkloads;
	String deviationNumberOfTaskWorkloads;
	String minimumNumberOfTaskWorkloads;
	String maximumNumberOfTaskWorkloads;

}
