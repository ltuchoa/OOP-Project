import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Nave {

	private BufferedImage desenho;
	
	private int x;
	private int y;
	private int velocidade;
	private boolean podeAtirar;
	private int tempo;
	
	public Nave() {
		
		try {
			desenho = ImageIO.read(new File("imagens/nave.png"));
		} catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem da nave");
			e.printStackTrace();
		}
		
		x = 30;
		y = 334;
		velocidade = 3;
		podeAtirar = true;
		tempo = 0;
	}
	
	public void pinta(Graphics2D g) {
		
		g.drawImage(desenho, x, y , x+180, y + 130, 0, 0, desenho.getWidth(), desenho.getHeight(), null);
		
	}
	
	public Tiro atirar() {
		
		Tiro novoTiro = new Tiro(x+160, y+90);
		podeAtirar = false;
		tempo = 0;
		return novoTiro;
	}
	
	public void movimento(int valor) {
		
		if (valor == 1) {
			y += velocidade;
		} else if (valor == -1) {
			y -= velocidade;
		}
		
		if(tempo >= 30) {
			podeAtirar = true;
			tempo = 0;
		}
		
		tempo++;
	}
	public boolean podeAtirar() {
		return podeAtirar;
	}
	public int getY(){
		return this.y;
	}
	public int getX(){
		return this.x;
	}
}
