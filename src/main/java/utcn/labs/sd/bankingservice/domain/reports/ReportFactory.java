package utcn.labs.sd.bankingservice.domain.reports;

import utcn.labs.sd.bankingservice.domain.data.entity.enums.ReportType;

public abstract class ReportFactory {
    public static ReportInterface getReport(ReportType type){
        ReportInterface reportInterface = null;
        switch (type){
            case PDF:   reportInterface = new ReportPDF();
                        break;
            case CSV:   reportInterface = new ReportCSV();
                        break;
        }
        return reportInterface;
    }
}
