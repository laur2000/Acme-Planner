
package acme.features.anonymous.task;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousTaskShowService implements AbstractShowService<Anonymous, Task> {

	@Autowired
	protected AnonymousTaskRepository repository;

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
	public Task findOne(final Request<Task> request) {
		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskUnfinishedById(id, Date.from(Instant.now()));
		return result;
	}
}
