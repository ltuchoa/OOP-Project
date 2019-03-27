import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Inimigo {

	private BufferedImage desenho;
	private int x, y;
	private float velocidade;
	private int direcao;
	
	public Inimigo (BufferedImage imagem, int inicioX,int inicioY, int direcao) {
		
		this.desenho = imagem;
		
		this.x = inicioX;
		this.y = inicioY;
		this.direcao = direcao;
		
		this.velocidade = 4;
	}
	
	public void atualizar () {
		
		y += velocidade * direcao;
		x -= 1;
	}
	
	public void trocaDirecao() {
		
		direcao = direcao * -1;
		if(direcao == 1 && velocidade <= 4.5) {
			velocidade+=0.5;
			//System.out.println(velocidade);
		}
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void pintar(Graphics2D g) {
		
		g.drawImage(desenho, x, y, x+100, y+100, 0, 0, desenho.getWidth(), desenho.getHeight(), null);
		
	}

	public int getTam() {
		return 75;
	}

}
