
package acme.features.anonymous.shout;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.burse.Burse;
import acme.entities.shout.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {

	@Query("select s from Shout s")
	Collection<Shout> findMany();

	@Query("select s from Shout s where s.moment >= :date order by s.moment desc")
	List<Shout> findShoutsNotOlderThanDateAndSortedByDate(@Param("date") Date date);
	
	@Query("select x from Burse x where x.mark = :mark")
	List<Burse> findMarks(@Param("mark") String mark);

}
