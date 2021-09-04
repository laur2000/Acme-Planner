package acme.features.manager.task;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {
	@Query("SELECT manager FROM Manager manager WHERE manager.id = :managerId")
	Manager findManagerById(@Param("managerId") Integer managerId);

	@Query("SELECT task FROM Task task WHERE task.id = :taskId")
	Task findTaskById(@Param("taskId") Integer taskId);
	
	@Query("SELECT task FROM Manager manager JOIN manager.tasks task WHERE manager.id = :managerId")
	List<Task> findManagerTasks(@Param("managerId") Integer managerId);
}
