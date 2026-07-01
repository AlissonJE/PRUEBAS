import java.util.Locale;
import java.util.Scanner;

public class LimpiaiTexto {

    private static final Locale LOCALE_ES = Locale.forLanguageTag("es-ES");

    public static String limpiaTexto(String texto) {
        if (texto == null) {
            return "";
        }

        String sinControles = texto.replace('\t', ' ')
                .replace('\r', ' ')
                .replace('\n', ' ');

        String normalizado = sinControles.trim().replaceAll(" +", " ");

        if (normalizado.isEmpty()) {
            return "";
        }

        String[] palabras = normalizado.split(" ");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            if (palabra.isEmpty()) {
                continue;
            }
            if (resultado.length() > 0) {
                resultado.append(' ');
            }
            String primeraLetra = palabra.substring(0, 1).toUpperCase(LOCALE_ES);
            String resto = palabra.length() > 1
                    ? palabra.substring(1).toLowerCase(LOCALE_ES)
                    : "";
            resultado.append(primeraLetra).append(resto);
        }

        return resultado.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Normalizador de texto (limpiaTexto) ===");
        System.out.println("Escribe el texto a normalizar y presiona Enter.");
        System.out.println("(Puedes usar \\t para tabulación y \\n para salto de línea dentro del texto).");
        System.out.print("Entrada: ");

        String entrada = scanner.nextLine();

        String entradaProcesada = entrada.replace("\\t", "\t").replace("\\n", "\n");

        String salida = limpiaTexto(entradaProcesada);

        System.out.println();
        System.out.println("Salida  : \"" + salida + "\"");

        scanner.close();
    }
}