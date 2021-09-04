package acme.features.authenticated.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedManagerUpdateService implements AbstractUpdateService<Authenticated, Manager> {

	@Autowired
	protected AuthenticatedManagerRepository repository;

	@Override
	public boolean authorise(final Request<Manager> request) {
		return true;
	}

	@Override
	public void validate(final Request<Manager> request, final Manager entity, final Errors errors) {
		//Not used
	}

	@Override
	public void bind(final Request<Manager> request, final Manager entity, final Errors errors) {
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Manager> request, final Manager entity, final Model model) {
		request.unbind(entity, model, "company", "sector");
	}

	@Override
	public Manager findOne(final Request<Manager> request) {
		Manager result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneManagerByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Manager> request, final Manager entity) {
		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Manager> request, final Response<Manager> response) {
		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
