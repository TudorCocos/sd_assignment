package utcn.labs.sd.bankingservice.domain.data.converter;

import utcn.labs.sd.bankingservice.domain.data.entity.Activity;
import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;

import java.util.ArrayList;
import java.util.List;

public class ActivityConverter {

    private ActivityConverter(){

    }

    public static ActivityDTO toDto(Activity model){
        ActivityDTO dto = null;
        if(model != null){
            dto = new ActivityDTO(model.getId(),model.getId_employee(),model.getActivity(),
                    model.getDate());
        }
        return dto;
    }

    public static List<ActivityDTO> toDto(List<Activity> models){
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        if((models != null) && (!models.isEmpty())){
            for(Activity activity : models){
                activityDTOS.add(toDto(activity));
            }
        }
        return activityDTOS;
    }

}
