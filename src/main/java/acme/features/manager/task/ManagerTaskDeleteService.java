package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerTaskDeleteService implements AbstractDeleteService<Manager, Task> {
	@Autowired
	ManagerTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		final Integer managerId = request.getPrincipal().getActiveRoleId();
		final Integer taskId = request.getModel().getInteger("id");
		final Task task = this.repository.findTaskById(taskId);
		return task.getManager().getId() == managerId.intValue();
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		//Not used
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		//Not used
	}

	@Override
	public Task findOne(final Request<Task> request) {
		final Integer taskId = request.getModel().getInteger("id");
		return this.repository.findTaskById(taskId);
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		//Not used
	}

	@Override
	public void delete(final Request<Task> request, final Task entity) {
		this.repository.delete(entity);
	}

}
