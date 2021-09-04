
package acme.features.administrator.spam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.SpamConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamRepository extends AbstractRepository {

	@Query("SELECT spamConfiguration FROM SpamConfiguration spamConfiguration")
	SpamConfiguration getSpamConfiguration();
}
