package com.carvalho.pts_api_workout_program.exporter;

import com.carvalho.pts_api_workout_program.AbstractExporter;
import com.carvalho.pts_api_workout_program.entity.WorkoutProgramEntity;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.IOException;

public class WorkoutProgramWordExporter extends AbstractExporter {

    public void export(WorkoutProgramEntity program, HttpServletResponse response, String moduleName) throws IOException {
        super.setResponseHeader(response,
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                ".docx",
                moduleName);

        String mainTitle = "Workout Program (" + program.getTitle() + ")";

        XWPFDocument document = new XWPFDocument();

        XWPFParagraph titleParagraph = document.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setBold(true);
        titleRun.setFontSize(14);
        titleRun.setText(mainTitle);
        titleRun.setTextPosition(20);

        XWPFParagraph title = document.createParagraph();
        XWPFRun runTitle = title.createRun();
        runTitle.setBold(true);
        runTitle.setFontSize(12);
        runTitle.setText(program.getTitle());

        XWPFParagraph startDate = document.createParagraph();
        startDate.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun runStartDate = startDate.createRun();
        runStartDate.setBold(true);
        runStartDate.setFontSize(12);
        runStartDate.setText("Start Date:");
        startDate.createRun().setText(" " + program.getStartDate());

        XWPFParagraph endDate = document.createParagraph();
        endDate.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun runEndDate = endDate.createRun();
        runEndDate.setBold(true);
        runEndDate.setFontSize(12);
        runEndDate.setText("Start Date:");
        endDate.createRun().setText(" " + program.getEndDate());

        XWPFParagraph notes = document.createParagraph();
        notes.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun runNotes = notes.createRun();
        runNotes.setBold(true);
        runNotes.setFontSize(12);
        runNotes.setText("Notes:");
        notes.createRun().setText(" " + program.getNote());

        XWPFParagraph contentTitle = document.createParagraph();
        contentTitle.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun runContentTitle = contentTitle.createRun();
        runContentTitle.setBold(true);
        runContentTitle.setFontSize(12);
        runContentTitle.setText("Program Content:");

        XWPFParagraph htmlContent = document.createParagraph();
        htmlContent.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun htmlRunContent = htmlContent.createRun();
        htmlRunContent.setText(program.getTrainingSessionContent());

        document.write(response.getOutputStream());
        document.close();
    }

}
