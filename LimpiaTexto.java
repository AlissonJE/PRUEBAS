import java.util.Locale;

public class LimpiaTexto {

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
        String entrada = "  jUaN     pÉrez\t\nGARCIA  ";
        String salidaEsperada = "Juan Pérez Garcia";
        String salidaObtenida = limpiaTexto(entrada);

        System.out.println("=== Demostración de limpiaTexto ===");
        System.out.println("Entrada        : \"" + entrada + "\"");
        System.out.println("Salida esperada: \"" + salidaEsperada + "\"");
        System.out.println("Salida obtenida: \"" + salidaObtenida + "\"");
        System.out.println(salidaObtenida.equals(salidaEsperada)
                ? "Resultado: Coincide correctamente."
                : "Resultado: NO coincide.");
    }
}