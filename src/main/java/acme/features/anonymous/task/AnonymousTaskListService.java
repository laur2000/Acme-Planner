package acme.features.anonymous.task;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousTaskListService implements AbstractListService<Anonymous, Task> {
	@Autowired
	AnonymousTaskRepository anonymousTaskRepository;

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
		return this.anonymousTaskRepository.findManyUnfinished(Date.from(Instant.now()));
	}
}
