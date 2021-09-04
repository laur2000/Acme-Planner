package acme.features.manager.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.forms.HoursAndMinutes;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ManagerTaskListService implements AbstractListService<Manager, Task> {

	@Autowired
	ManagerTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		try {
			entity.setWorkload(HoursAndMinutes.fromDecimalTime(entity.getWorkload()).getFormattedTime());
		} catch (final Exception e) {}
		
		request.unbind(entity, model, "title", "description", "workload", "link", "startPeriod", "endPeriod",
				"visibility");
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		final Integer managerId = request.getPrincipal().getActiveRoleId();
		return this.repository.findManagerTasks(managerId);
	}
}
