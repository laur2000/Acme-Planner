package acme.features.manager.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/managers/task/")
public class ManagerTaskController extends AbstractController<Manager, Task> {

	@Autowired
	ManagerTaskListService listService;

	@Autowired
	ManagerTaskShowService showService;

	@Autowired
	ManagerTaskDeleteService deleteService;

	@Autowired
	ManagerTaskCreateService createService;

	@Autowired
	ManagerTaskUpdateService updateService;

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
