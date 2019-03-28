package utcn.labs.sd.bankingservice.domain.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Report {

    private static ArrayList<String> employeeReport = new ArrayList<>();
    private static boolean isDocumentEmpty = true;

    private Report() {

    }

    public static void addReport(String report) {
        employeeReport.add(report);
    }

    public static void createPDF() {
        Document document = new Document();
        List list = new List(List.ORDERED);
        list.setAutoindent(false);
        list.setSymbolIndent(42);
        for (String s : employeeReport) {
            list.add(new ListItem(s));
        }
        try {
            PdfWriter.getInstance(document, new FileOutputStream("EmployeeReport.pdf"));
            document.open();
            if (isDocumentEmpty) {
                document.add(new Paragraph("The Report for the Employee: empty for now...."));
                isDocumentEmpty = false;
            }
            document.add(list);
            document.close();
            employeeReport.clear();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
