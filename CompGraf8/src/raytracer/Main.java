package raytracer;

import java.io.File;
import java.io.IOException;

public class Main {

    public static boolean DEBUG = false;
    public static boolean ANTI_ALIAS = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        File inFile = new File("scene-config.txt");
        File outFile = new File("result.png");
        int cols = 500;
        int rows = 500;
        RayTracer rayTracer = new RayTracer(cols, rows);
        rayTracer.readScene(inFile);
        rayTracer.draw(outFile);
    }
}
