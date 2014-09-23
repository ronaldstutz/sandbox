package sandbox.pdf;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfMetaTagTest {

    public static void main(final String[] args) {
        // --- Basically from http://thinktibits.blogspot.ch/2011/05/java-itext-add-pdf-metadata-tutorial.html
        try {
            final Document doc = new Document();
            // -- Step 1
            PdfWriter.getInstance(doc, new FileOutputStream("Sample.pdf"));
            doc.open();
            doc.add(new Paragraph("@DOC@Adding Metadata to PDF PDF_Meta_Datas using iText Example"));

            // -- Step 2
            /* Add PDF Document Title through Code */
            doc.addTitle("@TAG@Append Metadata to PDF Example");
            /* Add PDF Author Information */
            doc.addAuthor("@TAG@Thinktibits as Author");
            /* Add the Subject for the file */
            doc.addSubject("@TAG@Metadata Addition to PDF Tutorial");
            /* Included Keyword information */
            doc.addKeywords("@TAG@Metadata, iText, PDF");
            /* Insert Creator Information and Creation Date to the Document */
            doc.addCreator("@TAG@Thinktibits as Creator");
            doc.addCreationDate();
            //            doc.addProducer();
            doc.addHeader("@TAG@Key 1", "@TAG@Value 1");
            doc.addHeader("@TAG@Key 2", "@TAG@Value 2");
            doc.addHeader("@TAG@Key 3", "@TAG@Value 3");
            doc.close();

        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
