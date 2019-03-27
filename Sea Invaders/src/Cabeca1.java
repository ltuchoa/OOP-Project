import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Cabeca1 extends Boss{
	private int tempo;
	public Cabeca1(BufferedImage imagem, float inicioX,float inicioY, int direcao) {
		this.desenho = imagem;
		this.x = inicioX;
		this.y = inicioY;
		this.direcao = direcao;
		this.velocidade = 4;
		this.tempo=0;
	}
	public void morrer(){
		
	
	}
	
	public void atualizar () {
		this.y += velocidade * direcao;
		tempo++;
		x-=0.25;
	}
	public float getX(){
		return this.x;
	}
	public float getY(){
		return this.y;
	}

}
