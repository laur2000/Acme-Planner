package acme.features.administrator.dashboard;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.burse.Burse;
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
	
	@Query("select count(x) from Burse x")
	Integer totalNumberOfBurse();
	
	@Query("select x from Burse x where x.budget.currency = :currency")
	List<Burse> getBurseByCurrency(@Param("currency") String currency);

	@Query("select count(x) from Burse x where x.important = TRUE")
	Integer totalNumberOfBurseImportant();
	
	@Query("select count(x) from Burse x where x.budget.amount = 0")
	Integer totalNumberOfBurseBudgetZero();
	
	@Query("select avg(x.budget.amount) from Burse x where x.budget.currency = :currency")
	Double avgBurseCurrencyBudget(@Param("currency") String currency);
}
