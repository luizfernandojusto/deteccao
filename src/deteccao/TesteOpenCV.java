package deteccao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class TesteOpenCV {

	public static void main(String[] args) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println(Core.VERSION);

		Mat imagemColoriada = Imgcodecs.imread("src\\deteccao\\opencv_java.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);

		Utilitarios ut = new Utilitarios();
		ut.mostraImagem(ut.convertMatToImage(imagemColoriada));
		
		Mat imagemCinza = new Mat();
		Imgproc.cvtColor(imagemColoriada, imagemCinza, Imgproc.COLOR_BGR2GRAY);
		ut.mostraImagem(ut.convertMatToImage(imagemCinza));
		
		
		

	}

}
