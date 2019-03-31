package utcn.labs.sd.bankingservice.domain.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import utcn.labs.sd.bankingservice.domain.data.converter.ActivityConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Activity;
import utcn.labs.sd.bankingservice.domain.data.entity.Employee;
import utcn.labs.sd.bankingservice.domain.data.repository.ActivityRepository;
import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private EmployeeService employeeService;

    public List<ActivityDTO> getAllActivities(){
        addNewActivity("getAllActivities");
        return ActivityConverter.toDto(activityRepository.findAll());
    }

    public ActivityDTO getActivityById(Integer activityId) throws Exception {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) throw new NotFoundException("No employee found with that employeeId");
        addNewActivity("getActivityById " + activityId);
        return ActivityConverter.toDto(activity);
    }

    public void addNewActivity(String details){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByUsername(username);
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Activity activity = new Activity(employee.getId(), details, currentTimestamp.toString());
        activityRepository.save(activity);
    }

}
