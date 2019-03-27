import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public abstract class Boss{
	protected BufferedImage desenho;
	protected float x, y;
	protected float velocidade;
	protected int direcao;
	
	public void atualizar () {
		y += velocidade * direcao;
	}
	
	public void trocaDirecao() {
		direcao = direcao * -1;
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public void pintar(Graphics2D g) {
		
		g.drawImage(desenho, (int)x, (int)y, (int)x+100, (int)y+500, 0, 0, desenho.getWidth(), desenho.getHeight(), null);
		
	}

	public int getTam() {
		return 75;
	}
	
	public abstract void morrer();

}

