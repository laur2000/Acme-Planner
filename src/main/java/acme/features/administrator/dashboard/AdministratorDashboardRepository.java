package acme.features.administrator.dashboard;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.task.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select t from Task t ")
	Set<Task> findAllTasks();

	@Query("select count(t) from Task t where t.visibility = 'Public'")
	Integer totalNumberOfPublicTasks();

	@Query("select count(t) from Task t where t.visibility = 'Private'")
	Integer totalNumberOfPrivateTasks();

	@Query("select count(t) from Task t where t.endPeriod <= current_timestamp()")
	Integer totalNumberOfFinishedTasks();

	@Query("select count(t) from Task t where t.endPeriod > current_timestamp()")
	Integer totalNumberOfNonFinishedTasks();

}
