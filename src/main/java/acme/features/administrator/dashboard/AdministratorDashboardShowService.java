
package acme.features.administrator.dashboard;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.burse.Burse;
import acme.entities.task.Task;
import acme.forms.Dashboard;
import acme.forms.HoursAndMinutes;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	static final Double							MILISECONDS_TO_HOURS	= 1.0 / (60 * 60 * 1000);

	@Autowired
	protected AdministratorDashboardRepository	repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		request.unbind(entity, model, "totalNumberOfPublicTasks", "totalNumberOfPrivateTasks", "totalNumberOfFinishedTasks", "totalNumberOfNonFinishedTasks", "averageNumberOfTaskExecutionPeriods", "deviationNumberOfTaskExecutionPeriods",
			"minimumNumberOfTaskExecutionPeriods", "maximumNumberOfTaskExecutionPeriods", "averageNumberOfTaskWorkloads", "deviationNumberOfTaskWorkloads", "minimumNumberOfTaskWorkloads", "maximumNumberOfTaskWorkloads", "ratioBurseImportant",
			"ratioBurseBudgetZero", "averageBurseEurBudget", "averageBurseUsdBudget", "averageBurseGbpBudget", "deviationBurseEurBudget", "deviationBurseUsdBudget", "deviationBurseGbpBudget");
	}

	public Double getAverageNumberOfTaskWorkloads(final Request<Dashboard> request) {
		final Set<Task> tasks = this.repository.findAllTasks();

		if (tasks.isEmpty()) {
			return 0.0;
		} else {
			Double averageWorkloads = 0.0;

			for (final Task task : tasks) {
				final Double decimalWorkLoad = task.getWorkload();
				averageWorkloads += decimalWorkLoad;
			}

			averageWorkloads /= tasks.size();
			return averageWorkloads;
		}
	}

	public Double getRatioBurseImportant(final Request<Dashboard> request) {
		final Integer totalBurse = this.repository.totalNumberOfBurse();
		final Integer totalBurseImportant = this.repository.totalNumberOfBurseImportant();

		return ((double) totalBurseImportant) / totalBurse;
	}

	public Double getAverageBurseCurrencyBudget(final String currency) {
		final Double avg = this.repository.avgBurseCurrencyBudget(currency);

		return avg == null ? 0.0 : avg;
	}

	public Double getBurseCurrencyDeviation(final String currency) {
		final List<Burse> xxxs = this.repository.getBurseByCurrency(currency);

		if (xxxs.isEmpty()) {
			return 0.0;
		}
		final Double avg = this.getAverageBurseCurrencyBudget(currency);

		Double deviation = 0.0;

		for (final Burse xxx : xxxs) {
			final Double budget = xxx.getBudget().getAmount();
			final Double individualDeviation = Math.pow(budget - avg, 2);
			deviation += individualDeviation;
		}

		deviation = deviation / xxxs.size();
		return Math.sqrt(deviation);

	}

	public Double getRatioBurseBudgetZero(final Request<Dashboard> request) {
		final Integer totalBurse = this.repository.totalNumberOfBurse();
		final Integer totalBurseBudgetZero = this.repository.totalNumberOfBurseBudgetZero();

		return ((double) totalBurseBudgetZero) / totalBurse;
	}

	public Double getDeviationNumberOfTaskWorkloads(final Request<Dashboard> request) {
		final Set<Task> tasks = this.repository.findAllTasks();

		if (tasks.isEmpty()) {
			return 0.0;
		} else {
			Double deviationWorkloads = 0.0;
			final Double averageWorkloads = this.getAverageNumberOfTaskWorkloads(request);

			for (final Task task : tasks) {
				final Double decimalWorkLoad = task.getWorkload();
				final Double individualDeviation = Math.pow(decimalWorkLoad - averageWorkloads, 2);
				deviationWorkloads += individualDeviation;
			}

			deviationWorkloads = averageWorkloads / tasks.size();
			return Math.sqrt(deviationWorkloads);
		}
	}

	public Double getMinimumNumberOfTaskWorkloads(final Request<Dashboard> request) {
		final Set<Task> tasks = this.repository.findAllTasks();

		if (tasks.isEmpty()) {
			return 0.0;
		} else {
			Double minTimeWorkload = this.getMaximumNumberOfTaskWorkloads(request);

			for (final Task task : tasks) {
				final Double decimalWorkLoad = task.getWorkload();
				if (minTimeWorkload > decimalWorkLoad) {
					minTimeWorkload = decimalWorkLoad;
				}
			}

			return minTimeWorkload;
		}
	}

	public Double getMaximumNumberOfTaskWorkloads(final Request<Dashboard> request) {
		final Set<Task> tasks = this.repository.findAllTasks();

		if (tasks.isEmpty()) {
			return 0.0;
		} else {
			Double maxTimeWorkload = 0.0;

			for (final Task task : tasks) {
				final Double decimalWorkLoad = task.getWorkload();
				if (maxTimeWorkload < decimalWorkLoad) {
					maxTimeWorkload = decimalWorkLoad;
				}
			}
			return maxTimeWorkload;
		}
	}

	public Double getAverageNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		final Set<Task> tasks = this.repository.findAllTasks();

		if (tasks.isEmpty()) {
			return 0.0;
		} else {
			Long averageTimeExecutionPeriods = 0L;

			for (final Task task : tasks) {
				final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
				averageTimeExecutionPeriods += executionPeriodDiff;
			}

			averageTimeExecutionPeriods = averageTimeExecutionPeriods / tasks.size();
			return averageTimeExecutionPeriods.doubleValue() * AdministratorDashboardShowService.MILISECONDS_TO_HOURS;
		}
	}

	public Double getDeviationNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		final Set<Task> tasks = this.repository.findAllTasks();

		if (tasks.isEmpty()) {
			return 0.0;
		} else {
			final Double averageTaskExecutionPeriods = this.getAverageNumberOfTaskExecutionPeriods(request) / AdministratorDashboardShowService.MILISECONDS_TO_HOURS;
			Double deviationTimeExecutionPeriods = 0.0;

			for (final Task task : tasks) {
				final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
				final Double individualDeviation = Math.pow(executionPeriodDiff - averageTaskExecutionPeriods, 2);
				deviationTimeExecutionPeriods += individualDeviation;
			}

			deviationTimeExecutionPeriods = Math.sqrt(deviationTimeExecutionPeriods / tasks.size());
			return deviationTimeExecutionPeriods * AdministratorDashboardShowService.MILISECONDS_TO_HOURS;
		}
	}

	public Double getMinNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		final Set<Task> tasks = this.repository.findAllTasks();

		if (tasks.isEmpty()) {
			return 0.0;
		} else {
			final Double maxTimeExecutionPeriod = this.getMaxNumberOfTaskExecutionPeriods(request) / AdministratorDashboardShowService.MILISECONDS_TO_HOURS;
			Long minTimeExecutionPeriod = maxTimeExecutionPeriod.longValue();

			for (final Task task : tasks) {
				final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
				if (minTimeExecutionPeriod > executionPeriodDiff) {
					minTimeExecutionPeriod = executionPeriodDiff;
				}
			}
			return minTimeExecutionPeriod.doubleValue() * AdministratorDashboardShowService.MILISECONDS_TO_HOURS;
		}
	}

	public Double getMaxNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		final Set<Task> tasks = this.repository.findAllTasks();
		if (tasks.isEmpty()) {
			return 0.0;
		} else {
			Long maxTimeExecutionPeriod = 0L;

			for (final Task task : tasks) {
				final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
				if (maxTimeExecutionPeriod < executionPeriodDiff) {
					maxTimeExecutionPeriod = executionPeriodDiff;
				}
			}
			return maxTimeExecutionPeriod.doubleValue() * AdministratorDashboardShowService.MILISECONDS_TO_HOURS;
		}
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		final Dashboard result = new Dashboard();

		result.setTotalNumberOfPublicTasks(this.repository.totalNumberOfPublicTasks());
		result.setTotalNumberOfPrivateTasks(this.repository.totalNumberOfPrivateTasks());
		result.setTotalNumberOfFinishedTasks(this.repository.totalNumberOfFinishedTasks());
		result.setTotalNumberOfNonFinishedTasks(this.repository.totalNumberOfNonFinishedTasks());

		result.setRatioBurseImportant(String.format("%.2f", this.getRatioBurseImportant(request)));
		result.setRatioBurseBudgetZero(String.format("%.2f", this.getRatioBurseBudgetZero(request)));

		result.setAverageBurseEurBudget(String.format("%.2f", this.getAverageBurseCurrencyBudget("EUR")));
		result.setDeviationBurseEurBudget(String.format("%.2f", this.getBurseCurrencyDeviation("EUR")));

		result.setAverageBurseUsdBudget(String.format("%.2f", this.getAverageBurseCurrencyBudget("USD")));
		result.setDeviationBurseUsdBudget(String.format("%.2f", this.getBurseCurrencyDeviation("USD")));

		result.setAverageBurseGbpBudget(String.format("%.2f", this.getAverageBurseCurrencyBudget("GBP")));
		result.setDeviationBurseGbpBudget(String.format("%.2f", this.getBurseCurrencyDeviation("GBP")));

		result.setAverageNumberOfTaskExecutionPeriods(HoursAndMinutes.fromDecimalTime(this.getAverageNumberOfTaskExecutionPeriods(request)).toString());
		result.setDeviationNumberOfTaskExecutionPeriods(HoursAndMinutes.fromDecimalTime(this.getDeviationNumberOfTaskExecutionPeriods(request)).toString());
		result.setMinimumNumberOfTaskExecutionPeriods(HoursAndMinutes.fromDecimalTime(this.getMinNumberOfTaskExecutionPeriods(request)).toString());
		result.setMaximumNumberOfTaskExecutionPeriods(HoursAndMinutes.fromDecimalTime(this.getMaxNumberOfTaskExecutionPeriods(request)).toString());
		result.setAverageNumberOfTaskWorkloads(HoursAndMinutes.fromDecimalTime(this.getAverageNumberOfTaskWorkloads(request)).toString());
		result.setDeviationNumberOfTaskWorkloads(HoursAndMinutes.fromDecimalTime(this.getDeviationNumberOfTaskWorkloads(request)).toString());
		result.setMinimumNumberOfTaskWorkloads(HoursAndMinutes.fromDecimalTime(this.getMinimumNumberOfTaskWorkloads(request)).toString());
		result.setMaximumNumberOfTaskWorkloads(HoursAndMinutes.fromDecimalTime(this.getMaximumNumberOfTaskWorkloads(request)).toString());

		return result;
	}
}
