package utcn.labs.sd.bankingservice.domain.reports;

import utcn.labs.sd.bankingservice.domain.data.entity.Employee;
import utcn.labs.sd.bankingservice.domain.data.repository.ActivityRepository;

public interface ReportInterface {
    public void generateReport(String fromDate, String toDate, Employee employee, ActivityRepository activityRepository) throws Exception;
}
