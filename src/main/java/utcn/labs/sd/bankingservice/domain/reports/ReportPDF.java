package utcn.labs.sd.bankingservice.domain.reports;

import com.itextpdf.text.*;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfWriter;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import utcn.labs.sd.bankingservice.domain.data.entity.Activity;
import utcn.labs.sd.bankingservice.domain.data.entity.Employee;
import utcn.labs.sd.bankingservice.domain.data.repository.ActivityRepository;
import utcn.labs.sd.bankingservice.domain.service.ActivityService;
import utcn.labs.sd.bankingservice.domain.service.EmployeeService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReportPDF implements ReportInterface {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ActivityRepository activityRepository;

    public void generateReport(String fromDate, String toDate, Employee employee, ActivityRepository activityRepository) throws Exception{

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date from = dateFormat.parse(fromDate);
        Date to = dateFormat.parse(toDate);
        Timestamp fromStamp = new Timestamp(from.getTime());
        Timestamp toStamp = new Timestamp(to.getTime());

        Document document = new Document();
        List list = new List(List.ORDERED);
        list.setAutoindent(false);
        list.setSymbolIndent(42);
        for(Activity a: activityRepository.findAll()){
            if(a.getId_employee()==employee.getId()) {
                Date aDate = dateFormat.parse(a.getDate());
                Timestamp aStamp = new Timestamp(aDate.getTime());
                if(aStamp.after(fromStamp) && aStamp.before(toStamp)){
                    list.add(new ListItem("employeeUsername: "+employee.getUsername()+" did: "+a.getActivity()+" at: "+a.getDate()));
                }
            }
        }

        PdfWriter.getInstance(document, new FileOutputStream("EmployeeReport.pdf"));
        document.open();
        document.add(new ListItem("This is the report from: "+fromDate+" to: "+toDate));
        document.add(list);
        document.close();

    }
}
