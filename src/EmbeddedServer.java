import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import java.io.File;

/**
 * EmbeddedServer — Lanzador de Tomcat 10 embebido.
 *
 * Ejecutar con:  mvn compile exec:java
 * Ver en:        http://localhost:8080
 */
public class EmbeddedServer {

    public static void main(String[] args) throws Exception {

        // ── 1. Crear instancia de Tomcat ──
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir("target/tomcat-work");
        tomcat.getConnector();

        // ── 2. Registrar la aplicación web ──
        String webappPath = new File("web").getAbsolutePath();
        Context ctx = tomcat.addWebapp("", webappPath);

        // ── 3. FIX CRÍTICO: Classloader ──
        // Por defecto el classloader del webapp está AISLADO del classpath de Maven,
        // por eso Tomcat no encontraba jakarta.servlet.http.HttpServlet (error 500).
        // Al asignarle el classloader del hilo actual (que tiene todas las deps de Maven),
        // el Servlet y los JSPs funcionan correctamente.
        ctx.setParentClassLoader(Thread.currentThread().getContextClassLoader());

        // ── 4. Agregar clases compiladas al contexto (WEB-INF/classes) ──
        org.apache.catalina.WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
            new DirResourceSet(
                resources,
                "/WEB-INF/classes",
                new File("target/classes").getAbsolutePath(),
                "/"
            )
        );
        ctx.setResources(resources);

        // ── 5. Iniciar ──
        tomcat.start();

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   ✅ Servidor Tomcat iniciado correctamente  ║");
        System.out.println("║                                              ║");
        System.out.println("║   🌐 http://localhost:8080                   ║");
        System.out.println("║   📋 http://localhost:8080/posts             ║");
        System.out.println("║                                              ║");
        System.out.println("║   Presiona Ctrl+C para detener               ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        tomcat.getServer().await();
    }
}
