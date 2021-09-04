
package acme.features.anonymous.shout;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.shout.Shout;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/shout/")
public class AnonymousShoutController extends AbstractController<Anonymous, Shout> {

	@Autowired
	protected AnonymousShoutCreateService	createService;

	@Autowired
	private AnonymousShoutListService		listService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}

}
