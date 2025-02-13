package patientsServs.patientServicesEnrolment.excelGenerator;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.*;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import patientsServs.patientServicesEnrolment.DTO.EmployeeRegisterDTO;


@Component
public class EmployeeExcelGenerator {
	
	private List<EmployeeRegisterDTO> employeesList ;
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    
    
    
	public EmployeeExcelGenerator() {
		super();
	}

	public EmployeeExcelGenerator(List<EmployeeRegisterDTO> employeesList) {
		this.employeesList = employeesList;
		workbook = new XSSFWorkbook();
	}
	
	private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        
        if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof Double) {
            cell.setCellValue((Double) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell); 
        }
        
        
        
        cell.setCellStyle(style);
	}
	
	
	
	private void createHeaders() {
		sheet = workbook.createSheet("EMPLOYEES_LIST");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Employee Name", style);
        createCell(row, 2, "Mobile No", style);
        createCell(row, 3, "Mail ID", style);
        createCell(row, 4, "Address", style);
        createCell(row, 5, "Salary", style);
        createCell(row, 6, "Hospital ID", style);
        createCell(row, 7, "Department ID", style);
	}
	
	private void createRow() {
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        
        for (EmployeeRegisterDTO employeeRegisterDTO : employeesList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, employeeRegisterDTO.getEmployeeId(), style);
			createCell(row, columnCount++, employeeRegisterDTO.getName(), style);
			createCell(row, columnCount++, employeeRegisterDTO.getMobileNo(), style);
			createCell(row, columnCount++, employeeRegisterDTO.geteMailId(), style);
			createCell(row, columnCount++, employeeRegisterDTO.getAddress(), style);
			createCell(row, columnCount++, employeeRegisterDTO.getSalary(), style);
			createCell(row, columnCount++, employeeRegisterDTO.getHospitalId(), style);
			createCell(row, columnCount++, employeeRegisterDTO.getDepartmentId(), style);
        	
		}
	}
    
    
    public void generateExcel(HttpServletResponse httpResponse) throws IOException {
    	
    	createHeaders();
    	createRow();
    	ServletOutputStream outputStream = httpResponse.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    	
    }

}
