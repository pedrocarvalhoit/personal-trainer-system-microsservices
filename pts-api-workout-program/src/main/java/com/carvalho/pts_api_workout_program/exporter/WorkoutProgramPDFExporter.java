package com.carvalho.pts_api_workout_program.exporter;

import com.carvalho.pts_api_workout_program.AbstractExporter;
import com.carvalho.pts_api_workout_program.entity.WorkoutProgramEntity;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class WorkoutProgramPDFExporter extends AbstractExporter {

    public void export(WorkoutProgramEntity program, HttpServletResponse response, String moduleName) throws IOException {
        super.setResponseHeader(response, "application/pdf", ".pdf", moduleName);

        String mainTitle = "Workout Program (" + program.getTitle() + ")";

        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph(mainTitle).setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph(program.getTitle()).setBold().setFontSize(12));

        document.add(new Paragraph("Start Date").setBold().setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
        document.add(new Paragraph(String.valueOf(program.getStartDate())).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));

        document.add(new Paragraph("End Date").setBold().setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
        document.add(new Paragraph(String.valueOf(program.getEndDate())).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));

        document.add(new Paragraph("Notes").setBold().setFontSize(12).setTextAlignment(TextAlignment.LEFT));
        document.add(new Paragraph(program.getNote()).setFontSize(12));

        document.add(new Paragraph("Program Content").setBold().setFontSize(12).setTextAlignment(TextAlignment.LEFT));
        addHtmlContent(program.getTrainingSessionContent(), document);

        document.close();
    }

    private void addHtmlContent(String html, Document document) throws IOException {
        ConverterProperties converterProperties = new ConverterProperties();
        List<IElement> elements = HtmlConverter.convertToElements(html, converterProperties);

        for (IElement element : elements) {
            document.add((com.itextpdf.layout.element.IBlockElement) element);
        }
    }
}
