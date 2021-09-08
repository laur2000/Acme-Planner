package acme.features.authenticated.task;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedTaskListService implements AbstractListService<Authenticated, Task> {
	@Autowired
	AuthenticatedTaskRepository authenticatedTaskRepository;

	@Override
	public boolean authorise(final Request<Task> request) {
		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		request.unbind(entity, model, "title", "description", "workload", "link", "startPeriod", "endPeriod",
				"visibility");
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		return this.authenticatedTaskRepository.findManyFinished(Date.from(Instant.now()));
	}
}
