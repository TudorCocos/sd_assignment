package utcn.labs.sd.bankingservice.domain.reports;

import com.itextpdf.text.ListItem;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import utcn.labs.sd.bankingservice.domain.data.entity.Activity;
import utcn.labs.sd.bankingservice.domain.data.entity.Employee;
import utcn.labs.sd.bankingservice.domain.data.repository.ActivityRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportCSV implements ReportInterface {
    public void generateReport(String fromDate, String toDate, Employee employee, ActivityRepository activityRepository) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date from = dateFormat.parse(fromDate);
        Date to = dateFormat.parse(toDate);
        Timestamp fromStamp = new Timestamp(from.getTime());
        Timestamp toStamp = new Timestamp(to.getTime());

        try {
            Writer writer = new FileWriter("EmployeeReport.csv");
            StatefulBeanToCsvBuilder<Activity> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<Activity> beanWriter = builder.build();

            List<Activity> activities = new ArrayList<>();
            for (Activity a : activityRepository.findAll()) {
                if (a.getId_employee() == employee.getId()) {
                    Date aDate = dateFormat.parse(a.getDate());
                    Timestamp aStamp = new Timestamp(aDate.getTime());
                    if (aStamp.after(fromStamp) && aStamp.before(toStamp)) {
                        activities.add(a);
                    }
                }
            }

            beanWriter.write(activities);
            writer.close();

        } catch (IOException | ParseException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }
}
