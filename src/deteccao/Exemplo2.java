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

public class Exemplo2 {

	public static void main(String[] args) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat imagemColoriada = Imgcodecs.imread("src\\pessoas\\olho.jpg");

		Mat imagemCinza = new Mat();
		Imgproc.cvtColor(imagemColoriada, imagemCinza, Imgproc.COLOR_BGR2GRAY);

		CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");

		MatOfRect facesDetectadas = new MatOfRect();

		classificador.detectMultiScale(imagemCinza, facesDetectadas);// maxSize

		System.out.println(facesDetectadas.toArray().length);

		for (Rect rect : facesDetectadas.toArray()) {
			System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
			Imgproc.rectangle(imagemColoriada, new Point(rect.x, rect.y),
					new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 255), 2);

		}

		MatOfRect olhosDetectados = new MatOfRect();
		classificador = new CascadeClassifier("src\\cascades\\haarcascade_eye.xml");

		classificador.detectMultiScale(imagemCinza, olhosDetectados, 1.1, 1, 0, new Size(5, 5), new Size(100, 100));

		for (Rect rect : olhosDetectados.toArray()) {
			Imgproc.rectangle(imagemColoriada, new Point(rect.x, rect.y),
					new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
		}

		Utilitarios ut = new Utilitarios();
		ut.mostraImagem(ut.convertMatToImage(imagemColoriada));

	}

}
