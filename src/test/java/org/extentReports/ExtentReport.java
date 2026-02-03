package org.extentReports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class ExtentReport {
    private static ExtentReports extent;
    private static final String REPORT_DIR = "C:\\Users\\2457257\\IdeaProjects\\Hackathon-project-cts\\target\\extent";
    private static final String REPORT_FILE = "ExtentReport.html";
    private static String reportPath = Paths.get(REPORT_DIR, REPORT_FILE).toString();
    public static ExtentReports getInstance() {
        if (extent == null) {
            try {
                Path outDir = Paths.get(REPORT_DIR);
                Files.createDirectories(outDir);
                ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
                spark.config().setDocumentTitle("Display Bookshelves Automation");
                spark.config().setReportName("Display Bookshelves");
                spark.config().setTheme(Theme.STANDARD);
                spark.config().thumbnailForBase64(true);
                extent = new ExtentReports();
                extent.attachReporter(spark);

            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize ExtentReports", e);
            }
        }
        return extent;
    }
}

