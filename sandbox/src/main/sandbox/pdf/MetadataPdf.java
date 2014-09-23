package sandbox.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class MetadataPdf {
    /** The resulting PDF file. */
    public static final String RESULT1 = "pdf_metadata.pdf";
    /** The resulting PDF file. */
    public static final String RESULT2 = "pdf_metadata_changed.pdf";

    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws DocumentException
     * @throws IOException
     */
    public void createPdf(final String filename) throws IOException, DocumentException {
        // step 1
        final Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.addTitle("Hello World example");
        document.addAuthor("Bruno Lowagie");
        document.addSubject("This example shows how to add metadata");
        document.addKeywords("Metadata, iText, PDF");
        document.addCreator("My program using iText");
        document.open();
        // step 4
        document.add(new Paragraph("Hello World"));
        // step 5
        document.close();
    }

    /**
     * Manipulates a PDF file src with the file dest as result
     * @param src the original PDF
     * @param dest the resulting PDF
     * @throws IOException
     * @throws DocumentException
     */
    public void manipulatePdf(final String src, final String dest) throws IOException, DocumentException {
        final PdfReader reader = new PdfReader(src);
        final PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        final Map<String, String> info = reader.getInfo();
        info.put("Title", "Hello World stamped");
        info.put("Subject", "Hello World with changed metadata");
        info.put("Keywords", "iText in Action, PdfStamper");
        info.put("Creator", "Silly standalone example");
        info.put("Author", "Also Bruno Lowagie");
        stamper.setMoreInfo(info);
        stamper.close();
        reader.close();
    }

    /**
     * Main method.
     *
     * @param    args    no arguments needed
     * @throws DocumentException
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException, DocumentException {
        final MetadataPdf metadata = new MetadataPdf();
        metadata.createPdf(RESULT1);
        metadata.manipulatePdf(RESULT1, RESULT2);
    }
}