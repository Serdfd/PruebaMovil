package integration.Util;

import integration.models.Movie;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public final class ExcelHelper {

    public static void export(List<Movie> list){
        try (OutputStream os = new FileOutputStream(Constants.OUTPUT_FILE)){

            Workbook wb = new Workbook(os, "CineColombia", "1.0");
            Worksheet ws = wb.newWorksheet("Obras");

            //Add Headers
            ws.value(0, 0, "Nombre");
            ws.value(0, 1, "Fecha");
            ws.value(0, 2, "Género");
            ws.value(0, 3, "Duración");

            int r=1;

            for (Movie movie: list) {
                ws.value(r, 0, movie.getMovieName());
                ws.value(r, 1, movie.getReleaseDate());
                ws.value(r, 2, movie.getGenres());
                ws.value(r, 3, movie.getDuration());
                r++;
            }

            wb.finish();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
