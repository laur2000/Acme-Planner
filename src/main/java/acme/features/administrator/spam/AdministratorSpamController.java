
package acme.features.administrator.spam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spam.SpamConfiguration;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spam-configuration/")
public class AdministratorSpamController extends AbstractController<Administrator, SpamConfiguration> {

	@Autowired
	private AdministratorSpamConfigurationShowService	showService;

	@Autowired
	private AdministratorSpamConfigurationUpdateService	updateService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
