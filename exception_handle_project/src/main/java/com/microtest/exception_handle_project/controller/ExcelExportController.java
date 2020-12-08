package com.microtest.exception_handle_project.controller;

import com.microtest.exception_handle_project.entity.ExceptionHandleList;
import com.microtest.exception_handle_project.mapper.ExceptionInfoListMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@Controller
public class ExcelExportController {
    @Resource
    ExceptionInfoListMapper exceptionInfoListMapper;
    List<ExceptionHandleList> exceptionHandleLists = null;

    @RequestMapping("/export")
    public String excelExport(MultipartFile excel, Model model) throws IOException {
        XSSFWorkbook xssfWorkbook = null;
        List<String> ls= exceptionInfoListMapper.selectExceptionInfo();
        for (String S:
             ls) {
            System.out.println(S);
        }
        exceptionHandleLists = new ArrayList<>();
        if (!excel.isEmpty()) {
            InputStream inputStream = excel.getInputStream();
            xssfWorkbook = new XSSFWorkbook(inputStream);
            for (int k = 0; k < xssfWorkbook.getNumberOfSheets(); k++) {
                XSSFSheet Sheet = xssfWorkbook.getSheetAt(k);
                for (int i = 0; i <= Sheet.getLastRowNum() - 1; i++) {
                    if (i <= 1) {
                        continue;
                    }
                    Row row = Sheet.getRow(i);
                    if (row.getCell(0) == null || row.getCell(0).toString() == "") {
                        break;
                    }
                    ExceptionHandleList exceptionHandleList = new ExceptionHandleList();
                    exceptionHandleList.setDate(row.getCell(0).toString());
                    exceptionHandleList.setCustomer(row.getCell(1).toString());
                    exceptionHandleList.setProjectNumber(row.getCell(2).toString());
                    exceptionHandleList.setProjectName(row.getCell(3).toString());
                    exceptionHandleList.setStage(row.getCell(4).toString());
                    exceptionHandleList.setNumber((int) Math.ceil(Double.valueOf(row.getCell(5).toString())));
                    exceptionHandleList.setCharge(row.getCell(6).toString());
                    switch (row.getCell(7).toString()) {
                        case "生产问题":
                            exceptionHandleList.setExceptionRank(1);
                            break;
                        case "品质问题":
                            exceptionHandleList.setExceptionRank(2);
                            break;
                        case "工程问题":
                            exceptionHandleList.setExceptionRank(3);
                            break;
                    }
                    switch (row.getCell(8).toString()) {
                        case "一般问题":
                            exceptionHandleList.setExceptionClassify(1);
                            break;
                        case "一般异常":
                            exceptionHandleList.setExceptionClassify(2);
                            break;
                        case "重大异常":
                            exceptionHandleList.setExceptionClassify(3);
                            break;
                    }
                    exceptionHandleList.setException(row.getCell(9).toString());
                    if (row.getCell(10)!=null){
                        exceptionHandleList.setReason(row.getCell(10).toString());
                    }
                    if (row.getCell(11)!=null){
                        exceptionHandleList.setSolve(row.getCell(11).toString());
                    }
                    exceptionHandleList.setDRI(row.getCell(12).toString());
                    switch (row.getCell(13).toString()) {
                        case "open":
                            exceptionHandleList.setState(1);
                            break;
                        case "ongoing":
                            exceptionHandleList.setState(2);
                            break;
                        case "closed":
                            exceptionHandleList.setState(3);
                            break;
                    }
                    if (row.getCell(14)!=null){
                        exceptionHandleList.setRemarks(row.getCell(14).toString());
                    }
                    exceptionHandleLists.add(exceptionHandleList);
                }
            }
            inputStream.close();
            if (exceptionHandleLists!= null) {
                exceptionInfoListMapper.excelExport(exceptionHandleLists);
            }
            model.addAttribute("ExceptionHandleListNum",exceptionHandleLists.size());
            model.addAttribute("ExceptionHandleList",exceptionHandleLists);
            return "main";
        }

            return "main1";
    }
}

