package utcn.labs.sd.bankingservice.domain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.labs.sd.bankingservice.core.configuration.SwaggerTags;
import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;
import utcn.labs.sd.bankingservice.domain.service.ActivityService;

import java.util.List;

@Api(tags = {SwaggerTags.BANKING_SERVICE_TAG})
@RestController
@RequestMapping("/bank/admin/activity")
@CrossOrigin
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @ApiOperation(value = "getAllActivities", tags = SwaggerTags.ACTIVITY_TAG)
    @GetMapping
    public List<ActivityDTO> getAllActivities() {
        return activityService.getAllActivities();
    }

    @ApiOperation(value = "findActivityById", tags = SwaggerTags.ACTIVITY_TAG)
    @GetMapping(value = "/{activityId}")
    public ResponseEntity<?> findEmployeeById(@PathVariable("activityId") Integer activityId) {
        try {
            ActivityDTO activityDTO = activityService.getActivityById(activityId);
            return new ResponseEntity<>(activityDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
