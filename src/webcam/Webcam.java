package webcam;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import deteccao.Utilitarios;

public class Webcam extends JFrame {
	private static final long serialVersionUID = 1L;

	JPanel panel;

	public Webcam() {
		panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE));
		getContentPane().setLayout(groupLayout);
	}

	public static void main(String[] args) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Webcam janela = new Webcam();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(600, 500);
		janela.setLocationRelativeTo(janela);
		janela.setVisible(true);
		janela.mostrarVideo();

	}

	public void mostrarVideo() {

		Mat video = new Mat();
		VideoCapture captura = new VideoCapture(0);
		if (captura.isOpened()) {
			while (true) {
				captura.read(video);
				if (!video.empty()) {
					setSize(video.width() + 50, video.height() + 70);
					BufferedImage imagem = new Utilitarios().convertMatToImage(video);
					Graphics g = panel.getGraphics();
					g.drawImage(imagem, 10, 10, imagem.getWidth(), imagem.getHeight(), null);
				}
			}

		}

	}

}
