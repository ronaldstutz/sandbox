/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */
package sandbox.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.xml.xmp.DublinCoreSchema;
import com.itextpdf.text.xml.xmp.PdfSchema;
import com.itextpdf.text.xml.xmp.XmpArray;
import com.itextpdf.text.xml.xmp.XmpSchema;
import com.itextpdf.text.xml.xmp.XmpWriter;


public class MetadataXmp {

    /** The resulting PDF file. */
    public static final String RESULT1 = "xmp_metadata.pdf";
    /** The resulting PDF file. */
    public static final String RESULT2 = "xmp_metadata_automatic.pdf";
    /** The resulting PDF file. */
    public static final String RESULT3 = "xmp_metadata_added.pdf";
    /** An XML file containing an XMP stream. */
    public static final String RESULT4 = "xmp.xml";

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
        final PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT1));
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        final XmpWriter xmp = new XmpWriter(os);
        final XmpSchema dc = new com.itextpdf.text.xml.xmp.DublinCoreSchema();
        final XmpArray subject = new XmpArray(XmpArray.UNORDERED);
        subject.add("Hello World");
        subject.add("XMP & Metadata");
        subject.add("Metadata");
        dc.setProperty(DublinCoreSchema.SUBJECT, subject);
        xmp.addRdfDescription(dc);
        final PdfSchema pdf = new PdfSchema();
        pdf.setProperty(PdfSchema.KEYWORDS, "Hello World, XMP, Metadata");
        pdf.setProperty(PdfSchema.VERSION, "1.4");
        xmp.addRdfDescription(pdf);
        xmp.close();
        writer.setXmpMetadata(os.toByteArray());
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World"));
        // step 5
        document.close();
    }

    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws DocumentException
     * @throws IOException
     */
    public void createPdfAutomatic(final String filename) throws IOException, DocumentException {
        // step 1
        final Document document = new Document();
        // step 2
        final PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.addTitle("Hello World example");
        document.addSubject("This example shows how to add metadata & XMP");
        document.addKeywords("Metadata, iText, step 3");
        document.addCreator("My program using 'iText'");
        document.addAuthor("Bruno Lowagie & Paulo Soares");
        writer.createXmpMetadata();
        // step 3
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
        final HashMap<String, String> info = reader.getInfo();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final XmpWriter xmp = new XmpWriter(baos, info);
        xmp.close();
        stamper.setXmpMetadata(baos.toByteArray());
        stamper.close();
        reader.close();
    }

    /**
     * Reads the XML stream inside a PDF file into an XML file.
     * @param src  A PDF file containing XMP data
     * @param dest XML file containing the XMP data extracted from the PDF
     * @throws IOException
     */
    public void readXmpMetadata(final String src, final String dest) throws IOException {
        final PdfReader reader = new PdfReader(src);
        final FileOutputStream fos = new FileOutputStream(dest);
        final byte[] b = reader.getMetadata();
        fos.write(b, 0, b.length);
        fos.flush();
        fos.close();
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
        final MetadataXmp metadata = new MetadataXmp();
        metadata.createPdf(RESULT1);
        metadata.createPdfAutomatic(RESULT2);
        new MetadataPdf().createPdf(MetadataPdf.RESULT1);
        metadata.manipulatePdf(MetadataPdf.RESULT1, RESULT3);
        metadata.readXmpMetadata(RESULT3, RESULT4);
    }
}