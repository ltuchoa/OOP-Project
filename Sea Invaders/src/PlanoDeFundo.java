import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlanoDeFundo {

	private BufferedImage imagem;
	
	public PlanoDeFundo() {
		
		try {
			imagem = ImageIO.read(new File("imagens/fundo2.png"));
		} catch (IOException e) {
			System.out.println("Nao foi possivel carregar o fundo");
			e.printStackTrace();
		}
	}
	
	public void pinta(Graphics2D g) {
		
		g.drawImage(imagem, 0, 0, 1366, 768, null);

		g.setColor(Color.black);
		g.fillRect(5, 5, 100, 20);
		g.setColor(Color.white);
		g.drawString("Score: ", 20, 20);
	}
	
}

