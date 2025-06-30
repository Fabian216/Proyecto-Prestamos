package proyecto.API_Inicio.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import proyecto.API_Inicio.dto.PrestamoResponse;

import java.io.OutputStream;
import java.util.List;

public class ReportePdfUtil {

    public static void exportarPrestamosPdf(List<PrestamoResponse> prestamos, OutputStream outputStream) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titulo = new Paragraph("Reporte de Préstamos", fontTitulo);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);

        document.add(new Paragraph(" ")); // espacio

        PdfPTable table = new PdfPTable(5); // columnas: ID, Monto, Restante, Fecha, Cliente
        table.setWidthPercentage(100);

        table.addCell("ID");
        table.addCell("Monto");
        table.addCell("Restante");
        table.addCell("Fecha");
        table.addCell("Cliente");

        for (PrestamoResponse prestamo : prestamos) {
            table.addCell(String.valueOf(prestamo.getId()));
            table.addCell(prestamo.getMonto().toString());
            table.addCell(prestamo.getRestante().toString());
            table.addCell(prestamo.getFechaPrestamo().toString());

            String clienteNombre = prestamo.getClienteNombres() + " " + prestamo.getClienteApellidos() + " (DNI: " + prestamo.getClienteDni() + ")";
            table.addCell(clienteNombre);
        }

        document.add(table);
        document.close();
    }
}
