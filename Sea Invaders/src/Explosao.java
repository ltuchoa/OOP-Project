import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Explosao {
	
	private BufferedImage imagem;
	
	private int x, y;
	private int duracao;
	private int animacaoTotal;
	private int linha;
	private int coluna;
	
	
	public Explosao(BufferedImage imagem, int x, int y) {
		
		this.imagem = imagem;
		this.x = x;
		this.y = y;
		
		animacaoTotal = 30;
		duracao = 0;
		
		linha = 1;
		coluna = 1;
	}
	
	public void atualizar() {
		
		duracao += 2;
		
		linha = duracao / 6;
		coluna = duracao % 5;
		
		
	}
	
	public void pintar(Graphics2D g) {
		
		g.drawImage(imagem, x, y, x + 80, y + 70, 192 * coluna, 192 * linha, 192 * coluna + 192, 192 * linha + 192, null);
		
	}
	
	public boolean acabou() {
		if(duracao >= animacaoTotal) {
			return true;
		}
		
		return false;
	}
}
