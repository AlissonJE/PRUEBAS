import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestFuncion {

    private static final String ARCHIVO_PRUEBAS = "pruebas.txt";

    public static void main(String[] args) {
        String rutaArchivo = args.length > 0 ? args[0] : ARCHIVO_PRUEBAS;

        List<String> lineas = leerLineas(rutaArchivo);
        if (lineas == null) {
            return; // El error ya fue informado por leerLineas
        }

        int pruebasEjecutadas = 0;
        int pruebasExitosas = 0;
        int pruebasFallidas = 0;
        int ultimaLineaProcesada = 0;
        int numeroPrueba = 0;

        for (int i = 0; i + 1 < lineas.size(); i += 2) {
            numeroPrueba++;
            String entradaCruda = lineas.get(i);
            String esperadoCruda = lineas.get(i + 1);

            String entrada = desescapar(entradaCruda);
            String esperado = desescapar(esperadoCruda);
            String obtenido = LimpiaTexto.limpiaTexto(entrada);

            pruebasEjecutadas++;
            ultimaLineaProcesada = i + 2; // número de línea (1-indexado) tras procesar el par

            boolean paso = obtenido.equals(esperado);
            if (paso) {
                pruebasExitosas++;
                System.out.println("Prueba " + numeroPrueba + ": Paso");
            } else {
                pruebasFallidas++;
                System.out.println("Prueba " + numeroPrueba + ": Falló"
                        + "  (entrada=\"" + entradaCruda + "\","
                        + " esperado=\"" + esperadoCruda + "\","
                        + " obtenido=\"" + obtenido + "\")");
            }
        }

        if (lineas.size() % 2 != 0) {
            System.out.println("Advertencia: la última línea del archivo (línea "
                    + lineas.size() + ") no tiene un resultado esperado asociado y fue ignorada.");
        }

        double porcentajeExito = pruebasEjecutadas > 0
                ? (pruebasExitosas * 100.0 / pruebasEjecutadas)
                : 0.0;

        System.out.println("-----------------------------");
        System.out.println("Pruebas ejecutadas: " + pruebasEjecutadas);
        System.out.println("Pruebas exitosas: " + pruebasExitosas);
        System.out.println("Pruebas fallidas: " + pruebasFallidas);
        System.out.println("Última línea procesada: " + ultimaLineaProcesada);
        System.out.printf("Porcentaje de éxito: %.0f%%%n", porcentajeExito);
    }

    private static List<String> leerLineas(String ruta) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo \"" + ruta + "\": " + e.getMessage());
            return null;
        }
        return lineas;
    }

    private static String desescapar(String texto) {
        return texto.replace("\\t", "\t")
                .replace("\\n", "\n")
                .replace("\\r", "\r");
    }
}