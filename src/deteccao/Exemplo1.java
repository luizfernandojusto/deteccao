package deteccao;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Exemplo1 {

	public static void main(String[] args) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat imagemColoriada = Imgcodecs.imread("src\\outros\\banana1.jpg");

		Mat imagemCinza = new Mat();
		Imgproc.cvtColor(imagemColoriada, imagemCinza, Imgproc.COLOR_BGR2GRAY);

		CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\banana_classifier.xml");

		MatOfRect facesDetectadas = new MatOfRect();
		
		classificador.detectMultiScale(imagemCinza, facesDetectadas,
				1.1,//scale
				3,//minNeighbors
				0,//flags
				new Size(30,30),//minSize
				new Size(500,500));//maxSize
		
		
		
		System.out.println(facesDetectadas.toArray().length);

		for (Rect rect : facesDetectadas.toArray()) {
			System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
			
			if(rect.width> 50)
				System.out.println("fora do padrao");
			
			Imgproc.rectangle(imagemColoriada, new Point(rect.x, rect.y),
					new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);

		}
		Utilitarios ut = new Utilitarios();
		ut.mostraImagem(ut.convertMatToImage(imagemColoriada));

	}

}
